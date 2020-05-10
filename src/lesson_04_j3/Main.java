package lesson_04_j3;

public class Main {

    private volatile String currentLetter = "A";
    Object mon = new Object();

    public static void main(String[] args) {
        Main main = new Main();
        Thread tr1 = new Thread(() -> main.printA());
        Thread tr2 = new Thread(() -> main.printB());
        Thread tr3 = new Thread(() -> main.printC());
        tr1.start();
        tr2.start();
        tr3.start();

    }

    private void printA() {
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != "A") {
                        mon.wait();
                    }
                    System.out.print("A");
                    currentLetter = "B";
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void printB() {
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != "B") {
                        mon.wait();
                    }
                    System.out.print("B");
                    currentLetter = "C";
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printC() {
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != "C") {
                        mon.wait();
                    }
                    System.out.print("C ");
                    currentLetter = "A";
                    mon.notifyAll();
                    // notifyAll.
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}


