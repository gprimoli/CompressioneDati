package it.unisa.di.wrapper.positinoed;

import it.unisa.di.table.BaseElement;

public class IntPositionedElement extends PositionedElement<Integer> {
    public IntPositionedElement(String content, int pos) {
        super(Integer.parseInt(content), pos);
    }

    @Override
    public void subContent(BaseElement<?> b) {
        content -= (int) b.getContent();
    }
}
