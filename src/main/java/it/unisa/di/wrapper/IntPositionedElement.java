package it.unisa.di.wrapper;

import it.unisa.di.table.BaseElement;

public class IntPositionedElement extends PositionedElement<Integer> {
    public IntPositionedElement(String content, int pos) {
        super(Integer.parseInt(content), pos);
    }

    @Override
    public void subContent(BaseElement<Integer> b) {
        content -= b.getContent();
    }
}
