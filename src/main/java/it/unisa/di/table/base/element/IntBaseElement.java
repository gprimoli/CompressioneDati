package it.unisa.di.table.base.element;


public class IntBaseElement extends BaseElement<Integer> {
    public IntBaseElement(String content) {
        super(Integer.parseInt(content));
    }

    @Override
    public void subContent(BaseElement<?> b) {
        content -= (int) b.getContent();
    }

    @Override
    public void plusContent(BaseElement<?> b)  {
        content += (int) b.getContent();
    }
}
