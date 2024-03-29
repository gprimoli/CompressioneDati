package it.unisa.di.table.positionend.element;

import it.unisa.di.table.base.element.BaseElement;

abstract public class PositionedElement<T> extends BaseElement<T> {
    private int pos;

    public PositionedElement(T content, int pos) {
        super(content);
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void subPos(PositionedElement<?> b) {
        this.pos -= b.pos;
    }
    public void addPos(PositionedElement<?> b) {
        this.pos += b.pos;
    }

    public void subPos(int pos) {
        this.pos -= pos;
    }
    public void addPos(int pos) {
        this.pos += pos;
    }

    @Override
    public String toString() {
        return content.toString();
    }

    @Override
    public PositionedElement<T> clone() {
        return (PositionedElement<T>) super.clone();
    }
}
