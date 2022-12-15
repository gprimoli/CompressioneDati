package it.unisa.di.table;

public abstract class BaseElement<T> implements Cloneable {
    protected T content;

    public BaseElement(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    abstract public void subContent(BaseElement<?> b);


    @Override
    public String toString() {
        return content.toString() + "\n";
    }

    @Override
    public BaseElement<T> clone() {
        try {
            return (BaseElement<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
