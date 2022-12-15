package it.unisa.di.wrapper;

import it.unisa.di.table.BaseElement;

public class FloatPositionedElement extends PositionedElement<Float> {
    public FloatPositionedElement(String content, int pos) {
        super(Float.parseFloat(content), pos);
    }

    @Override
    public void subContent(BaseElement<Float> b) {
        content -= b.getContent();
    }
}
