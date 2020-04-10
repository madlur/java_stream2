package lesson_02;

public class Main {


    public static void main(String[] args) throws MyOwnNumberFormatEx {
    String string = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
    String[][] testArray = to2Darray(string);
        System.out.println("The result of calculations is: " + sum(testArray));
    }

    private static String[][] to2Darray(String string) throws MyOwnException {
        String[] arr = string.split("\n");
        if (arr.length != 4) throw new MyOwnException("It isn`t a matrix 4x4");
        String[][] arr2 = new String[4][];

        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i].split(" ");
        }
        return arr2;
    }

    private static int sum(String[][] arr) throws MyOwnNumberFormatEx {
        int sum = 0;
try {

    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length; j++) {
            sum += Integer.parseInt(arr[i][j]);
        }
    }
} catch (Exception e) {
    throw new MyOwnNumberFormatEx();
    }
        return sum / 2;
    }

}
