package lesson_02;

public class myOwnException extends IndexOutOfBoundsException{ //наследуюсь от наследника непроверяемого Runtime...

   myOwnException(String description){
  super(description);
   }

    }

