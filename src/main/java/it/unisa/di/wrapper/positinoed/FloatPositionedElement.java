package it.unisa.di.wrapper.positinoed;

import it.unisa.di.table.BaseElement;

public class FloatPositionedElement extends PositionedElement<Float> {
    public FloatPositionedElement(String content, int pos) {
        super(Float.parseFloat(content), pos);
    }

    @Override
    public void subContent(BaseElement<?> b) {
        content -= (Float) b.getContent();
    }
}
