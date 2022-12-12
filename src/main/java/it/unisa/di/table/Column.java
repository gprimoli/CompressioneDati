package it.unisa.di.table;

import it.unisa.di.common.Helper;
import it.unisa.di.common.Mapper;
import it.unisa.di.common.Type;
import it.unisa.di.wrapper.FloatWrapper;
import it.unisa.di.wrapper.IntWrapper;
import it.unisa.di.wrapper.StringWrap;

import java.util.LinkedList;
import java.util.List;

class Column<T> {//L'assenza dello scoope non è un errore: Non voglio far usare questa classe al di fuori del package.
    private final String name;
    private final Type type;
    private final List<Element<T>> column;

    private final static Mapper map = new Mapper();

    private Column(String name, Type type) {
        this.name = name;
        this.type = type;
        this.column = new LinkedList<>();
    }

    public static Column init(String entry, String name){
        return new Column(name, Helper.whatIs(entry));
    }

    public void addElement(String entry, int pos) {
        Element el;
        switch (type) {
            case Integer -> el = new Element<>(new IntWrapper(entry), pos);
            case Float -> el = new Element<>(new FloatWrapper(entry), pos);
            default -> el = new Element<>(new StringWrap(entry), pos);
        }
        column.add(el);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Column Name: ").append(name).append("\nContent:\n");
        column.forEach(sb::append);
        return sb.append("\n").toString();
    }
}
