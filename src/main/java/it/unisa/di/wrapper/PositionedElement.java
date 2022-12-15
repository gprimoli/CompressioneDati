package it.unisa.di.wrapper;

import it.unisa.di.table.BaseElement;

abstract public class PositionedElement<T> extends BaseElement<T> {
    private int pos;

    public PositionedElement(T content, int pos) {
        super(content);
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void subPos(PositionedElement<T> b) {
        this.pos -= b.pos;
    }
    public void subPos(int pos) {
        this.pos -= pos;
    }

    public int getSubPos(PositionedElement<T> b) {
        return this.pos - b.pos;
    }
    public int getSubPos(int pos) {
        return this.pos - pos;
    }

    @Override
    public String toString() {
        return "[" + pos + "] " + super.toString();
    }
}
