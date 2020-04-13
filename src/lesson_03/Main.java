package lesson_03;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String [] words = new String[]{"Ночь", "улица", "фонарь", "аптека",
                "Бессмысленный", "и", "тусклый", "свет",
                "Живи", "ещё", "хоть", "четверть", "века", "—",
                "Всё", "будет", "так", "Исхода", "нет",
                "Умрёшь", "—", "начнёшь", "опять", "сначала",
                "И", "повторится", "всё", "как", "встарь",
                "Ночь", "ледяная", "рябь", "канала",
                "Аптека", "улица", "фонарь"};

        // 1.1. Найти список слов, из которых состоит текст (дубликаты не считать);
        System.out.println("До " + Arrays.toString(words) + " " + words.length);
        LinkedHashSet<String> textContains = new LinkedHashSet<>(Arrays.asList(words));
        System.out.println("После: "+textContains+" "+ textContains.size());

        // 1.2. Посчитать сколько раз встречается каждое слово (использовать HashMap). отдельный метод
        count(words);

        //2.1. Написать простой класс PhoneBook(внутри использовать HashMap):
        //  - В качестве ключа использовать фамилию
        //  - В каждой записи всего два поля: phone, e-mail
        //  - Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов), и
        //  отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть несколько
        //  записей. Итого должно получиться 3 класса Main, PhoneBook, Person.
        Phonebook pb = new Phonebook(); //сохдание экземпляра телефонной книги
        Person p1 = new Person("Ivan", "Ivanov", 79011234567l, "ivan@mail.ru");
        Person p2 = new Person("Petr", "Petrov", 79022345678l, "petrov@mail.ru");
        Person p3 = new Person("Alexey", "Ivanov", 7000010223l, "ivan@gmail.ru");
        Person p4 = new Person("Vasilij", "Petrov", 89061119878l, "v-p@rambler.ru");
        Person p5 = new Person("Sidr", "Sidorov", 79031126784l, "sidorov@.ru");
        Person p6 = new Person("Baba", "Kapustin", 879933212234l, "baba@mail.ru");
        Person p7 = new Person("Kazak", "Kazakov", 000010010102l, "@.ru");

        pb.add(p1);
        pb.add(p2);
        pb.add(p3);
        pb.add(p4);
        pb.add(p5);
        pb.add(p6);
        pb.add(p7);

        pb.showPhoneBook();

        pb.phoneBySurname("Petrov");
        pb.emailBySurname("Ivanov");

    }

    private static void count(String [] words) {
        LinkedHashMap<String, Integer> hasmap1 = new LinkedHashMap<>(words.length);

        for (String c: words) {
            if(hasmap1.containsKey(c)) hasmap1.put(c,hasmap1.get(c) + 1);
            else hasmap1.put(c,1);
        }
        Set<Map.Entry<String,Integer>> set = hasmap1.entrySet();
        for (Map.Entry<String,Integer> entry:set) {
            System.out.println("Значение " + entry.getKey() + " встречается в тексте: " + entry.getValue()+" раз");
        }
    }

    }

