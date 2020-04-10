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


//        List <String> newWords1 = Arrays.asList(words);
        LinkedList<String> newWords = new LinkedList<>(Arrays.asList(words));

        LinkedHashSet<String> hashSet = new LinkedHashSet<>(newWords);
        HashSet<String> hashSet1 = new HashSet<>(newWords);


//ArrayList <String> newWords = new ArrayList<>();
//        for (int i = 0; i <words.length ; i++) {
//            newWords.add(words[i]);
////            System.out.println(Integer.toHexString(newWords.get(i).hashCode()));
//        }
        System.out.println(Arrays.toString(words)+" количество элементов = " + words.length);
//        System.out.println(newWords);
//        System.out.println(llist );
//        System.out.println(Integer.toHexString(newWords.get(5).hashCode()));
        System.out.println(hashSet + " количество элементов = " + hashSet.size());
//        System.out.println(hashSet1);


    }
}
