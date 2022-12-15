package it.unisa.di.table;
 abstract public class BaseElement<T> {
    protected T content;

    public BaseElement(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    abstract public void subContent(BaseElement<T> b);

    @Override
    public String toString() {
        return content.toString() + "\n";
    }
}
