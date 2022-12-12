package it.unisa.di.wrapper;

import it.unisa.di.table.Subtractable;

public class IntWrapper extends Subtractable<Integer> {
    public IntWrapper(Integer wrapped) {
        super(wrapped);
    }

    public IntWrapper(String wrapped) {
        super(Integer.parseInt(wrapped));
    }

    @Override
    public Integer sub(Subtractable<Integer> a) {
        return get() - a.get();
    }

    @Override
    public int compareTo(Integer o) {
        return 0;
    }
}
