package it.unisa.di.wrapper;

import it.unisa.di.common.Mapper;
import it.unisa.di.table.BaseElement;

public class StringPositionedElement extends PositionedElement<Integer> {
    private final static Mapper map = new Mapper();
    public StringPositionedElement(String content, int pos) {
        super(map.add(content), pos);
    }

    @Override
    public void subContent(BaseElement<Integer> b) {
        content -= b.getContent();
    }

    @Override
    public String toString() {
        return map.get(content) + "\n";
    }
}
