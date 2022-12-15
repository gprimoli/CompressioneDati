package it.unisa.di.common;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private static final List<String> map = new ArrayList<>();

    public int add(String s) {
        if (!map.contains(s))
            map.add(s);
        return map.indexOf(s);
    }

    public String get(int i) {
        return map.get(i);
    }
}
