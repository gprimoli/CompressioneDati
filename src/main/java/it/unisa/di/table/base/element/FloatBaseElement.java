package it.unisa.di.table.base.element;


public class FloatBaseElement extends BaseElement<Float> {
    public FloatBaseElement(String content) {
        super(Float.parseFloat(content));
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
