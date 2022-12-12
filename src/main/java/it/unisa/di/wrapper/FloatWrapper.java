package it.unisa.di.wrapper;

import it.unisa.di.table.Subtractable;

public class FloatWrapper extends Subtractable<Float> {
    public FloatWrapper(Float wrapped) {
        super(wrapped);
    }
    public FloatWrapper(String wrapped) {
        super(Float.parseFloat(wrapped));
    }

    @Override
    public Float sub(Subtractable<Float> a) {
        return null;
    }

    @Override
    public int compareTo(Float o) {
        return 0;
    }
}
