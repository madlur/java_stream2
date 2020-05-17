public class MainClass2 {

            public  boolean method2fast(int[] arr) {
            int temp_ones=0;
            int temp_fours=0;
            int another=0;

            for (int i = 0; i <arr.length ; i++) {
                if(arr[i]==1) temp_ones++;
                else if (arr[i]==4) temp_fours++;
                else another++;
            }
            return temp_ones != 0 && temp_fours != 0 && another==0;
        }
    }
