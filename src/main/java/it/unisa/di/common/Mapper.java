package it.unisa.di.common;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private static final List<String> map = new ArrayList<>();

    int add(String s) {
        if (!map.contains(s))
            map.add(s);
        return map.indexOf(s);
    }

    String get(int i) {
        return map.get(i);
    }
}
//    private static final HashMap<String, Integer> map = new HashMap<>();
//
//    void add(String s) {
//        if (!map.containsKey(s))
//            map.put(s, map.size());//Aritmetic code come chiave
//    }
//
//    String get(int i) {
//        return map.get(i);
//    }
