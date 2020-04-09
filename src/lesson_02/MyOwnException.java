package lesson_02;

public class MyOwnException extends IndexOutOfBoundsException{ //наследуюсь от наследника непроверяемого Runtime...

   MyOwnException(String description){
  super(description);
   }

    }

