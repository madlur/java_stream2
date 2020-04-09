package lesson_02;

public class MyOwnNumberFormatEx extends Exception { //наследуюсь от класса Exception а не от непроверяемых Runtime
                                                    // поэтому в мейне метод требует обработки try/catch или проброс
    MyOwnNumberFormatEx(){
        System.out.println("Your array contains NaN");
    }
}
