package lesson_02;

public class myOwnException extends IndexOutOfBoundsException{

   myOwnException(String string){
   System.err.println(string);
   }

    }

