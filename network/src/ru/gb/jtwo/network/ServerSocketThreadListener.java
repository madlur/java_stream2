package ru.gb.jtwo.network;

import java.net.ServerSocket;
import java.net.Socket;

public interface ServerSocketThreadListener {

    void onServerStarted(ServerSocketThread thread);
    void onServerCreated(ServerSocketThread thread, ServerSocket server);
    void onServerTimeout(ServerSocketThread thread, ServerSocket server);
    void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket);
    void onServerException(ServerSocketThread thread, Throwable throwable);
    void onServerStop(ServerSocketThread thread);

}

/*
1. Правильно ли я понял, что SQLiteStudio используется для создания необходимой базы данных,
а sqlite-jdbc для использования данной базы в Java?
Правильно ли я понял, что query в методе getNickname (класс SqlClient) это SQL запрос,
реализуемый с помощью ResultSet set = statement.executeQuery(query)?
При SQL запросах имеет ли значение какие кавычки использовать
(например, select nickname from users where password = "444" или
select nickname from users where password = '444')?
Проверил и двойные, и одинарные кавычки, работает всё корректно, но, может быть,
какие-то рекомендации есть, какие кавычки использовать лучше.
В JavaFX setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE),
который мы использовали в Swing реализуется автоматически ?
Правильно ли я понял Ваше объяснение работы timeout в методе run (класс ServerSocketThread):
Например, timeout = 3 секунды.
Через каждые 3 секунды server будет выходить из метода accept, далее выполнять секцию catch
и возвращаться в метод accept. Выход server из метода accept и возврат в него проиходит очень быстро.
Всё верно?

Почему сделали обертку ClientThread над SocketThread, а не добавили поля и методы в SocketThread?
В каких ситуациях используют обертки?
Как называются такие классы, как Library с точки зрения архитектуры программы? Когда стоит их использовать?
В классе ChatServer передеаем в метод handleAuthMessage аргумент client - "handleAuthMessage(client, msg)", но не используем его в методе, лишний аргумент получается?

Совсем непонятно uncaughtException. Что это и для чего нам нужен.
Vector, если вкратце хотя бы. понял что это что то из коллекций, а что конкретно - нет.
Что происходит в методе handleNonAuthmessage.
Откуда мы берем значения переменных src и message в классе Library.
Не очень понятно про класс Library, остальные вопросы писал в телегу

1. Почему в лайбрари в делимитере используется плюс\минус а не пробел?
2. Запутался с получением никнейма пользователя. К примеру, чтоб после отправки сообщения сначала выводился ник пользователя, а после него само сообщение. У клиентТреда есть поле никнейм, и даже есть геттер, но не соображу, как к нему обратиться. Сама процедура получения никнейма из базы вроде понятна.

1) Если у нас произошло соединение, то для каждого сокета (клиент/сервер) мы создаем входной/выходной потоки. Это суммарно 2 потока или четыре, которые каким то образом соединяются в интернете?
2) А если на стороне сервер сокета создать 2 потока: вход/выход, а на стороне сокета клиента 3 потока: вход/вход/выход, как произойдет взаимодествие?
3) И вопрос из другой области: Thread.UncaughtExceptionHandler где метод UncaughtExceptionHandler описан как "функциональны интерфейс" как такое возможно? Не понял как метод класса оказался интерфесом. Если будет время на уроке - скажите пару фраз.
4) Вопросы про лайбрери "типичны", поэтоу просто еще раз пересмотрю.

При попытке распарсить первое сообщение, появляющееся у пользователя в ClientGUI в методе onSocketReady при его идентификации (/auth_request±login±password), успешно ловится EOFException. А printStackTrace ведёт к in.readUTF в SocketThread. По причине недостаточного понимания работы кода в целом, он представляется многоходовочкой из фильма "Начало" с Леонардо ДиКаприо
Возникает ощущение, будто сообщения, печатаемые в логе пользователя, участвуют в обмене системными сообщениями, то есть читаются из лога пользовательского интерфейса, предназначенного для непосредственного вывода обмена сообщениями между пользователями, и используются потом в качестве системных. Следовательно, в лог молотится всё подряд, а попытка отделить одно от другого приводит к тому, что клиентское приложение перестаёт работать.
Следует ли тогда парсить то, что приходит в onReceiveString, а не по определённому событию, ведь тогда нужно будет писать огромный и неповоротливый метод, который, к тому же, сможет быть введён в заблужение пользовательским вводом, если он частично или полностью совпадёт с ожидаемым системным сообщением? Это представляется мне излишним, но как обойти эту ситуацию я пока не знаю.

1. Можно ли пакет network использовать при написании сетевого хранилища, или в сетевом хранилище работа с сетью реализуется иначе?
2. На вебинаре "Коллекции в Java" на GeekBrains было сказано, что Vector не желателен для использования, вместо него в Collections есть методы synchronizedList() и класс opyOnWriteArrayList. Что было бы лучше использовать?
3. Вопрос по использованию внутренних и вложенных классов. Где они применяются, насколько они полезны и как понять когда нужно их применять?
4. Перечисление enum: как и где применять?

Не понимаю как происходит вхаимодействие с библиотекой.
void handleNonAuthMessage(ClientThread client, String msg) {
        String[] arr = msg.split(Library.DELIMITER);
        if (arr.length != 3 || !arr[0].equals(Library.AUTH_REQUEST)) {
            client.msgFormatError(msg);
            return;
        }
Вот пример кода. в котором мы строку проверяем на соответствие условию.
И первый элемент должен соответствовать AUTH_REQUEST, далее логин и пароль.
Нажимая же кнопку логин мы реализуем метод коннект который создает сокет и сокеттред. На сколько я понимаю в библиотеки мы генерируем одно либо другое сообщение посредством выбора конструктора,
 исходя из входных данных. Т.е. входные данные логин и пароль прикручиваем AUTH_REQUEST и возвращаем.
Но не могу понять как посредством нажатия кнопки логин мы отправляем именно эти входные данные на конструктор библиотеки.

Попробовал выполнить проект на java 14 с java fx 14. FX загрузился нормально, тест выполнился без exeptions. А при запуске проекта выдает ошибки в библиотечных классах. Основная трудность для меня - это четко определить границы объектов, что отдать им в свойства и функиции и где начинается уже другой объект. Понимание приходит постепенно.

1. Vector - что это и откуда это взялось. На java1 про такое не говорили). Я конечно прочитал что это коллекция, но почему мы используем именно её, а не допустим том же ArrayList, или HashMap? в чем её преимущества?
2. Почему и для чего мы вынесли Library в отдельный класс? Да и в общем в модуль? Можно ведь было его реализовать в ChatServer.
3. В методе onReceiveString, объясните строку ClientThread client = (ClientThread) thread; Почему такой странный синтаксис, написания.

Почему мы не удаляем клиентов(clients.remove(thread) в ChatServer при событии onSocketException()?
Не надо ли отключаться от БД при событии onServerException()?
Почему в векторе clients SocketThread, а не ClientThread?
Небольшая путаница у меня с ClientThread и SocketThread

Из конкретных вопросов:
1) как работает uncaughtExceptionHandler? В каких случаях его нужно реализовывать, в каих ситуациях вызывается?
2) может ли возникать исключение с нулевым стеком вызовов?
3) почему в методе putLog снова вызывается SwingUtilities.invokeLater(), ведь приложение уже запущено, если окно отобразилось и можно осуществлять ввод? В целом, получается SwingUtilities.invokeLater(), можно вызывать в несольких методах и это не вызовет проблем с созданием EDT?
4) Обработка ситуации когда сервер недоступен (сейчас выкидывается исключение и программа прекращает работу, во всяком случае в том варианте который у меня получился, может что-то пропустил?)

1. Как можно реализовать регистрацию пользователя? Создать отдельный метод в ClientGUI который будет писать напрямую в базу?(малобезопасно).
2. Можно ли вынуть идентификацию пользователя из SocketThread(ip) и при помощи этой инфы реализовать функцию бана(где-нибудь в AUTH_REQUEST ip!=banid[]).
3. В этой версии чата нельзя реализовать вставку картинок в чат?(строка ввода строго String и окно чата врятли покажет что-то кроме String, вопрос в реализации чата).
4. Как реализовать удаление сообщений, и собственно, модерацию?(опять же, возможно ли это в данной реализации, вроде все irq были сугубо текстовые чаты, этот чат больше
похож на irq).
5. Как реализовать личку между пользователями? Делать новые окна ClientGUI с двумя пользователями в комнате?

Вопросы по коду:
 	При нажатии кнопки  Disconnect в ClientGUI  строка 98 - socketThread.close(); должен закрыться(прерваться) поток, разорваться соединение, но  этого не происходит, поток остается «жив» (убеждаемся  socketThred.isAlive()) и в socketThred событие interrupted не происходит, производиться попытка чтения (строки 29, 30 в SocketThred.java)
29 while (!isInterrupted()) {
30	String msg = in.readUTF();

и вываливаемся в Exeption:  « java.io.EOFException».  Вопрос: «Почему так происходит? Вроде с одной стороны клиент поток закрывает и разрывает соединение но при этом поток еще какое то время «живет» (а как долго «живет» ?). С другой стороны SocketThred об этом событии как бы и не знает, выполняется условие while(!isInterrupted), мы проваливаемся в тело цикла, пытаемся прочитать строку из потока а он уже не существует и соответственно получаем Exeption. Как это исправить?

Вопрос по IDEA
 	Что (и где) нужно прописать в IDEA(13)  что бы Server на JavaFX заработал? Библиотеки скачал, прописал, IDEA(13) видит и подсвечивает их белым но при запуске вываливается в Exception in Application start method (Exception running application ru.gb.jtwo.chat.server.gui.fx.Server). Google подсказал что надо прописать пути в Run –> Edit Configuration –>  но все мои упражнения закончились ничем. Как побороть IDEA(13)?

1. Есть ли такая практика помещать свои написанные классы Exception в отдельную папку в проекте?
Например, мы написали какие-то свои классы Exception для Chat-Client. Стоит ли выделять Exception в отдельную папку внутри Chat-Client?
2.
3. Вы рисовали схему взаимодействия клиента и сервера через интернет. В этой схеме было создание сокета у клиента, создание сокета у сервера. Но после коннект ок клиента и сервера создавался еще один сокет, который тоже оборачивался в поток, который затем взаимодействовал с клиентом (din, dout) и т.д. Зачем нужен этот еще один новый сокет, непонятно

1-Почему мы пишем именно на свинге и ФХ, если они почти не используются? Хорошая база для следующих фрэймворков?
2-На чем мы вообще пишем server.fxml шта это?) это другой язык , как я понял? стоит ли бросаться его учить , пока будет перерыв, или он только в ФХ используется?
3- Parent root = FXMLoader.load(getClass.getResource(server.fxml)) - что тут произошло и кто в этом виноват?=)

1) Почему в library нету методв для возврата самих операций ? (чтобы их обработать на клиенте и выводить красивые сообщение)
2) Зачем в методе getTypeBroadcast указывается время System.currentTimeMillis() а не сразу в красисовом формате даты, к примеру 15:01:25 ?
3) ChatServer, void handleAuthMessage походу надо его переписать еще было, чтобы было не просто сообщение а от кого и в какое время?
4) SqlClient synchronized static String getNickname, можно же в запросе написать чтобы возвращалось только 1 значение, так неоптимальней будет ? (на уровне БД должна быть уникальность никнеймов).
5) JavaFX не взлетел (не удалось понять как за 5 минут установить его в новую версию SDK java).

не очень понятен класс library, и зачем он нужен.

1. Library .... плохо понятен! )))
2. Метод handleNonAuthMessage в классе ChatServer с начала был не понятен. В итоге разобрался, но с трудом,
если можно еще раз кратко и простыми словами.

Хотелось бы схему процедуры авторизации. У вас получается доходчиво рисовать схемы.

было бы здорово если бы Вы вслед за схемой строения приложения дали бы схему его функционирования (если таковая может быть построена).

Можно какой-то краткий алгоритм как пользоваться JDBC. Типа установить соединение при помощи DriverManager, создать выражения (бывают с параметрами, без параметров) и т.д.?
* */
