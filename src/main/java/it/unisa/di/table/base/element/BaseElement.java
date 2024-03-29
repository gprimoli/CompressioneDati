package it.unisa.di.table.base.element;

import java.io.Serial;
import java.io.Serializable;

public abstract class BaseElement<T> implements Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected T content;

    public BaseElement(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    abstract public void subContent(BaseElement<?> b);
    abstract public void plusContent(BaseElement<?> b);

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
