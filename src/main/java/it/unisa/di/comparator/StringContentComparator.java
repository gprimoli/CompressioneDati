package it.unisa.di.comparator;

import it.unisa.di.wrapper.BaseElement;
import it.unisa.di.wrapper.StringPositionedElement;

import java.util.Comparator;

public class StringContentComparator implements Comparator<BaseElement<?>> {
    @Override
    public int compare(BaseElement o1, BaseElement o2) {
        StringPositionedElement a = (StringPositionedElement) o1;
        StringPositionedElement b = (StringPositionedElement) o2;
        return Integer.compare(a.getContent(), b.getContent());
    }
}
