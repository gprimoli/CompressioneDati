package it.unisa.di.wrapper;

import it.unisa.di.table.Subtractable;

public class StringWrap extends Subtractable<String> {
    public StringWrap(String wrapped) {
        super(wrapped);
    }
    @Override
    public String sub(Subtractable<String> a) {
        return "";
    }

    @Override
    public int compareTo(String o) {
        return super.get().compareTo(o);
    }
}
