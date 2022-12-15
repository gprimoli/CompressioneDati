//package it.unisa.di.wrapper.base;
//
//import it.unisa.di.common.Mapper;
//import it.unisa.di.table.BaseElement;
//
//public class StringElement extends BaseElement<Integer> {
//    private final static Mapper map = new Mapper();
//
//    public StringElement(String content) {
//        super(map.add(content));
//    }
//
//    @Override
//    public void subContent(BaseElement<Integer> b) {
//        content -= b.getContent();
//    }
//
//}
