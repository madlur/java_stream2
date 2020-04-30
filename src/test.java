import java.util.Arrays;
import java.util.Scanner;

public class test {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int y = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();

        int [] a = new int [] {y,m,d};
        Arrays.sort(a);
        d=a[1];
        System.out.println(d);

    }
}
