package lesson_05;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        method1();
        method2();

    }

    private static void method1() {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Working time of method1 is: " + (System.currentTimeMillis() - a) + " milliseconds");
    }

    static void method2() throws InterruptedException {

        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);


        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }

            }
        };
        Thread tr1 = new Thread(r, "Thread#1");
        tr1.start();
        Thread tr2 = new Thread(r2, "Thread#2");
        tr2.start();

        tr1.join();
        tr2.join();

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        System.out.println("Working time of method2 is: " + (System.currentTimeMillis() - a) + " milliseconds");
    }
}
