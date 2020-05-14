package ru.gb.jtwo.chat.server.core;

import ru.gb.jtwo.chat.common.Library;
import ru.gb.jtwo.network.ServerSocketThread;
import ru.gb.jtwo.network.ServerSocketThreadListener;
import ru.gb.jtwo.network.SocketThread;
import ru.gb.jtwo.network.SocketThreadListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {

    ServerSocketThread server;
    ChatServerListener listener;
    Vector<SocketThread> clients = new Vector<>();
    ExecutorService es = Executors.newFixedThreadPool(500);
    Logger logger = Logger.getLogger("ChatServerLog");
    FileHandler fh;

    public ChatServer(ChatServerListener listener) {
        this.listener = listener;
        loggerInit();
    }

    public void start(int port) {
        if (server != null && server.isAlive()) {
            putLog("Already running");
            logger.info("Already running"); }
        else
            server = new ServerSocketThread(this, "Server", port, 2000);
    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            putLog("Nothing to stop");
            logger.info("Nothing to stop");
        } else {
            server.interrupt();
        }
    }

    private void putLog(String msg) {
        listener.onChatServerMessage(msg);
    }

    public void loggerInit(){
       try{
           fh = new FileHandler("ChatServerLog.txt");
           logger.addHandler(fh);
           SimpleFormatter formatter = new SimpleFormatter();
           fh.setFormatter(formatter);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    /**
     * Server Socket Thread Listener methods
     * */

    @Override
    public void onServerStarted(ServerSocketThread thread) {
        putLog("Server thread started");
        logger.info("Server thread started");
        SqlClient.connect();
    }

    @Override
    public void onServerCreated(ServerSocketThread thread, ServerSocket server) {
        putLog("Server socket started");
        logger.info("Server socket started");
    }

    @Override
    public void onServerTimeout(ServerSocketThread thread, ServerSocket server) {
        //putLog("Server timeout");
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket) {
        putLog("Client connected");
        logger.info("Client connected");
        String name = "SocketThread " + socket.getInetAddress() + ":" + socket.getPort();
        es.execute(new Runnable() {
            @Override
            public void run() {
                new ClientThread(ChatServer.this, name, socket);
            }
        });
    }

    @Override
    public void onServerException(ServerSocketThread thread, Throwable throwable) {
        putLog("Server exception");
        logger.warning("Server exception ");
        throwable.printStackTrace();
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server thread stopped");
        logger.info("Server thread stopped");
        dropAllClients();
        SqlClient.disconnect();
        es.shutdown();
    }

    /**
     * Socket Thread Listener methods
     * */

    @Override
    public synchronized void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Socket started");
        logger.info("Socket started");
    }

    @Override
    public synchronized void onSocketStop(SocketThread thread) {
        ClientThread client = (ClientThread) thread;
        clients.remove(thread);
        if (client.isAuthorized() && !client.isReconnecting()) {
            sendToAllAuthorizedClients(Library.getTypeBroadcast("Server",
                    client.getNickname() + " disconnected"));
        }
        sendToAllAuthorizedClients(Library.getUserList(getUsers()));
        logger.info("Server: "+ client.getNickname() + " disconnected");
    }

    @Override
    public synchronized void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Socket ready");
        logger.info("Socket ready");
        clients.add(thread);
    }

    @Override
    public synchronized void onReceiveString(SocketThread thread, Socket socket, String msg) {
        ClientThread client = (ClientThread) thread;
        if (client.isAuthorized()) {
            handleAuthMessage(client, msg);
        } else
            handleNonAuthMessage(client, msg);
        logger.info(client.getNickname() + " send message: " + msg);
    }

    @Override
    public synchronized void onSocketException(SocketThread thread, Throwable throwable) {
        throwable.printStackTrace();
        logger.warning("Socket exception " + thread.getName().toString());
    }

    void handleAuthMessage(ClientThread client, String msg) {
        String[] arr = msg.split(Library.DELIMITER);
        String msgType = arr[0];
        switch (msgType) {
            case Library.TYPE_BCAST_CLIENT:
                sendToAllAuthorizedClients(Library.getTypeBroadcast(
                        client.getNickname(), arr[1]));
                logger.info(client.getNickname() + " send handle_Aut_message " + msg);
                break;
            case Library.TYPE_CHANGE_NICKNAME_CLIENT:
                SqlClient.changeNickName(arr[1]);
                logger.info(client.getNickname() + " send handle_Aut_message " + msg);

                break;
            case Library.newNick_ACCEPT:
                sendToAllAuthorizedClients(Library.getTypeBroadcast(client.getNickname(), " changed his nickname to " + arr[1]));
                client.setNickname(arr[1]);
                sendToAllAuthorizedClients(Library.getUserList(getUsers()));
                logger.info(client.getNickname() + " send handle_Aut_message " + msg);

                break;
            default:
                client.sendMessage(Library.getMsgFormatError(msg));
                logger.warning(client.getNickname() + " send handle_Aut_message " + msg);
        }

    }

    void handleNonAuthMessage(ClientThread client, String msg) {
        String[] arr = msg.split(Library.DELIMITER);
        if (arr.length != 3 || !arr[0].equals(Library.AUTH_REQUEST)) {
            client.msgFormatError(msg);
            logger.warning(client.getNickname() + " send NonHandle_Aut_message " + msg);
            return;
        }
        String login = arr[1];
        String password = arr[2];
        String nickname = SqlClient.getNickname(login, password);
        if (nickname == null) {
            SqlClient.addClientToDB(login,password);
//            putLog("Invalid login attempt: " + login);
//            client.authFail();
            nickname = SqlClient.getNickname(login,password);
            client.authAccept(nickname);
            sendToAllAuthorizedClients(Library.getTypeBroadcast("Server: new user", nickname + " join us"));
            logger.info("Server: new user" + nickname + " join us");
//            return;
        }


        else {
            ClientThread oldClient = findClientByNickname(nickname);
            client.authAccept(nickname);

            if (oldClient == null) {
                sendToAllAuthorizedClients(Library.getTypeBroadcast("Server", nickname + " connected"));
                logger.info("Server: old user" + nickname + " connected");
            } else {
                oldClient.reconnect();
                clients.remove(oldClient);
                logger.info(oldClient.getNickname() + "was removed from ClientsList");
            }
        }
        sendToAllAuthorizedClients(Library.getUserList(getUsers()));
    }

    private void sendToAllAuthorizedClients(String msg) {
        for (int i = 0; i < clients.size(); i++) {
            ClientThread client = (ClientThread) clients.get(i);
            if (!client.isAuthorized()) continue;
            client.sendMessage(msg);
// можно было прописать логирование здесь, но в других методах будет лучше, так как можно разделить логи по уровням
        }
    }

    public void dropAllClients() {
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).close();
        }
        logger.info("all clients were dropped by server");
    }


    private synchronized String getUsers() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < clients.size(); i++) {
            ClientThread client = (ClientThread) clients.get(i);
            if (!client.isAuthorized()) continue;
            sb.append(client.getNickname()).append(Library.DELIMITER);
        }
        return sb.toString();
    }


    private synchronized ClientThread findClientByNickname(String nickname) {
        for (int i = 0; i < clients.size(); i++) {
            ClientThread client = (ClientThread) clients.get(i);
            if (!client.isAuthorized()) continue;
            if (client.getNickname().equals(nickname))
                return client;
        }
        return null;
    }
}
