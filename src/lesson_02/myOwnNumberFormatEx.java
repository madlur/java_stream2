package lesson_02;

public class myOwnNumberFormatEx extends Exception { //наследуюсь от класса Exception а не от непроверяемых Runtime
                                                    // поэтому в мейне метод требует обработки try/catch или проброс
    myOwnNumberFormatEx(){
        System.out.println("Your array contains NaN");
    }
}
