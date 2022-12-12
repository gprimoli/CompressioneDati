package it.unisa.di.table;

import java.util.Comparator;

class Element<T> {//L'assenza dello scoope non è un errore: Non voglio far usare questa classe al di fuori del package.
    private final Subtractable<T> content;
    private int pos;
    public Element(Subtractable<T> content, int pos) {
        this.content = content;
        this.pos = pos;
    }

    public T getContent() {
        return content.get();
    }
    public int getPos() {
        return pos;
    }

    public void subPos(Element<T> b){
        this.pos = b.pos;
    }
    public void subContent(Element<T> b){
        this.content.sub(b.content);
    }

    @Override
    public String toString() {
        return content + " [" + pos + "]\n";
    }


    public Comparator<Element<T>> compareByContent() {
        return (o1, o2) -> o1.content.compareTo(o2.content.get());
    }

    public Comparator<Element<T>> compareByPos() {
        return Comparator.comparingInt(o -> o.pos);
    }
}
