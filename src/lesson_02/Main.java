package lesson_02;

public class Main {


    public static void main(String[] args) {
    String string = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
    try {
    String[][] testArray = to2Darray(string);
    sum(testArray);
    } catch (myOwnException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
    System.out.println(e);
        }
    }

    private static String[][] to2Darray(String string) throws myOwnException, ArrayIndexOutOfBoundsException {
        String[] arr = string.split("\n");
        if (arr.length != 4) throw new myOwnException("It isn`t a matrix 4x4");
        String[][] arr2 = new String[4][4];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                String[] temparr = arr[i].split(" ");
                if (temparr.length != arr2[i].length)
                    throw new ArrayIndexOutOfBoundsException("Your column doesn`t contain 4 symbols");
                arr2[i][j] = temparr[j];
            }
        }
        return arr2;
    }

    private static int sum(String[][] arr) {
        int sum = 0;
try {


    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length; j++) {
                sum += Integer.parseInt(arr[i][j]);
        }
    }
} catch (NumberFormatException e) {
    System.out.println("Please, make sure that you have digits in array");
}
        return sum / 2;
    }

}
