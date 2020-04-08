package lesson_02;

public class myOwnNumberFormatEx extends RuntimeException {
    myOwnNumberFormatEx(){
        System.out.println("Your array contains NaN");
    }
}
