package lesson_03;

import java.util.*;

public class Phonebook {
    private static HashMap<String, ArrayList<Object>> phoneBook;

    Phonebook() {
        phoneBook = new HashMap<>();
    }

    public void add(Person p) {
        ArrayList<Object> list = phoneBook.get(p.getSurname());
        if (list == null)
            list = new ArrayList<>();
        list.add(p.getPhone());
        list.add(p.getEmail());
        phoneBook.put(p.getSurname(), list);
    }

    public ArrayList<Object> get(String surname) {
        return phoneBook.get(surname);
    }

    public void showPhoneBook() {
        System.out.println("The phone book: ");
        for (Map.Entry<String, ArrayList<Object>> pb : phoneBook.entrySet()) {
            System.out.println(pb.getKey() + " " + pb.getValue());
        }
        System.out.println("--------------------the end-------------------");
    }

    public void phoneBySurname(String surname) {

        for (Map.Entry<String, ArrayList<Object>> pb : phoneBook.entrySet()) {
            if (pb.getKey().equals(surname)) {
                ArrayList<Object> list = pb.getValue();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Long) System.out.println("Phone number of "+ pb.getKey() +" is: " + list.get(i));
                }

            }

        }
    }

    public void emailBySurname(String surname) {

        for (Map.Entry<String, ArrayList<Object>> pb : phoneBook.entrySet()) {
            if (pb.getKey().equals(surname)) {
                ArrayList<Object> list = pb.getValue();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof String) System.out.println("Email of " + pb.getKey() + " is :" + list.get(i));
                }

            }

        }
    }

}
