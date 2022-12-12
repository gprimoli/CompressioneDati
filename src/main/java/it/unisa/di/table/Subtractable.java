package it.unisa.di.table;

public abstract class Subtractable<T> implements Comparable<T>{
    private final T wrapped;
    public Subtractable(T wrapped) {
        this.wrapped = wrapped;
    }
    public T get() {
        return wrapped;
    }
    public abstract T sub(Subtractable<T> a);

    @Override
    public String toString() {
        return wrapped.toString();
    }
}
