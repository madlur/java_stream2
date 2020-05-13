
public class MainClass {

    private static final int targetValue = 4;

     static int[] method1(int[] arr) throws RuntimeException {
        int tempIndex=0;
        boolean value=false;
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]==targetValue) {
                tempIndex = i;
                value=true;
            }
        }

            if (tempIndex == 0&& !value)
                throw new RuntimeException("There is no '4' in array or your array consist of only one value");
            int [] arrNew = new int[arr.length-tempIndex-1];
            tempIndex++;
            for (int j = 0; j <arrNew.length ; j++) {
                arrNew[j] = arr[tempIndex];
                tempIndex++;
            }
            return arrNew;
        }
    }



