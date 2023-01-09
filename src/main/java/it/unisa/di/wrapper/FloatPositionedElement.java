package it.unisa.di.wrapper;

public class FloatPositionedElement extends PositionedElement<Float> {
    public FloatPositionedElement(String content, int pos) {
        super(Float.parseFloat(content), pos);
    }
    @Override
    public void subContent(BaseElement<?> b) {
        content -= (Float) b.getContent();
    }

    @Override
    public void plusContent(BaseElement<?> b)  {
        content += (Float) b.getContent();
    }
}
