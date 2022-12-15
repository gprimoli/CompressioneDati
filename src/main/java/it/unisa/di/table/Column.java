package it.unisa.di.table;

import it.unisa.di.common.Helper;
import it.unisa.di.common.Mapper;
import it.unisa.di.common.Type;
import it.unisa.di.comparator.FloatContentComparator;
import it.unisa.di.comparator.IntContentComparator;
import it.unisa.di.comparator.StringContentComparator;
import it.unisa.di.wrapper.FloatPositionedElement;
import it.unisa.di.wrapper.IntPositionedElement;
import it.unisa.di.wrapper.StringPositionedElement;

import java.util.LinkedList;
import java.util.List;

class Column {//L'assenza dello scoope non è un errore: Non voglio far usare questa classe al di fuori del package.
    private final String name;
    private final Type type;
    private final List<BaseElement> elements;

    private Column(String name, Type type) {
        this.name = name;
        this.type = type;
        this.elements = new LinkedList<>();
    }

    public static Column init(String entry, String name) {
        return new Column(name, Helper.whatIs(entry));
    }

    public void addElement(String entry, int pos) {
        BaseElement el;
        switch (type) {
            case Integer -> el = new IntPositionedElement(entry, pos);
            case Float -> el = new FloatPositionedElement(entry, pos);
            default -> el = new StringPositionedElement(entry, pos);
        }
        elements.add(el);
    }

    public void sort() {
        switch (type) {
            case Integer -> elements.sort(new IntContentComparator());
            case Float -> elements.sort(new FloatContentComparator());
            default -> elements.sort(new StringContentComparator());
        }
    }

//    public static void subIntraColumn(Column c) {
//        for (int i = 1; i < c.elements.size(); i++) {
//            c.elements.get(i).subContent(c.elements.get(i - 1));
//        }
//    }
//
//    public static void subInterColumn(Column a, Column b) {
//        int sizeA = a.elements.size();
//        int sizeB = b.elements.size();
//        for (int i = 0; i < sizeA; i++) {
//            if (i >= sizeB) break;
//            a.elements.get(i).subPos(b.elements.get(i));
//        }
//    }
//
//    public static void subInterColumn(Column a) {
//        for (int i = 0; i < a.elements.size(); i++) {
//            a.elements.get(i).subPos(i);
//        }
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Column Name: ").append(name).append("\nContent:\n");
        elements.forEach(sb::append);
        return sb.toString();
    }
}
