package j3_lesson6_hw;

import java.sql.Time;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class MainClass {

    private static int targetValue = 4;
    private static int tempIndex=0;

    public static void main(String[] args) {

        Random random = new Random();
//        int [] arr = new int[]{1, 4, 4, 2, 3, 4, 1, 7};
//        int [] arr = new int[]{1, 2, 3, 2, 3, 5, 4, 7};
//        int [] arr = new int[]{1, 7, 2, 8, 4, 5, 2, 2};
//        int [] arr = new int[random.nextInt(10)+1];
//        for (int i = 0; i <arr.length ; i++) {
//            arr[i]=random.nextInt(8)+1;
//        }
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(method1(arr)));
        int [] arr = new int[]{1, 1, 4, 4, 1, 4, 1, 1};
        int [] arr1 = new int[]{1, 1, 1, 1};
        int [] arr2 = new int[]{4, 4, 4, 4};
        long time = System.currentTimeMillis();
        System.out.println(method2fast(arr));
        System.out.println(method2fast(arr1));
        System.out.println(method2fast(arr2));
        System.out.println("Time for fast method is: " + (System.currentTimeMillis()-time));
        long time1 = System.currentTimeMillis();
        System.out.println(method2long(arr));
        System.out.println(method2long(arr1));
        System.out.println(method2long(arr2));
        System.out.println("Time for long method is: " + (System.currentTimeMillis()-time1));

    }



    private static int[] method1(int[] arr) {

        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]==targetValue) {
                tempIndex = i;
            }
        }
        if(tempIndex ==0||arr.length==0) throw new RuntimeException("There is no '4' in array or your array consist of only one value");
            int [] arrNew = new int[arr.length-tempIndex-1];
            tempIndex++;
            for (int j = 0; j <arrNew.length ; j++) {
                arrNew[j] = arr[tempIndex];
                tempIndex++;
            }
            return arrNew;
        }

    private static boolean method2long(int[] arr) {
        return IntStream.of(arr).anyMatch(x -> x == 4) && IntStream.of(arr).anyMatch(x -> x == 1);
    }
    private static boolean method2fast(int[] arr) {
        int temp_ones=0;
        int temp_fours=0;

        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]==1) temp_ones++;
            else if (arr[i]==4) temp_fours++;
        }
        return temp_ones != 0 && temp_fours != 0;
    }

    }



