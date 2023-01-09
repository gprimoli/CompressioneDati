package it.unisa.di.table.base.element;

import it.unisa.di.common.Mapper;

public class StringBaseElement extends BaseElement<Integer> {
    private final static Mapper map = new Mapper();

    public StringBaseElement(String content) {
        super(map.add(content));
    }

    @Override
    public void subContent(BaseElement<?> b) {
        content -= (int) b.getContent();
    }

    @Override
    public void plusContent(BaseElement<?> b) {
        content += (int) b.getContent();
    }

    @Override
    public String toString() {
        return map.get(content);//"[" + getPos() + "] " + map.get(content) + "\n"
    }
}
