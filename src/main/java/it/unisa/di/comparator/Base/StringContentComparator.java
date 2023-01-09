package it.unisa.di.comparator.Base;

import it.unisa.di.table.base.element.BaseElement;
import it.unisa.di.table.base.element.StringBaseElement;
import it.unisa.di.table.positionend.element.StringPositionedElement;

import java.util.Comparator;

public class StringContentComparator implements Comparator<BaseElement<?>> {
    @Override
    public int compare(BaseElement o1, BaseElement o2) {
        StringBaseElement a = (StringBaseElement) o1;
        StringBaseElement b = (StringBaseElement) o2;
        return Integer.compare(a.getContent(), b.getContent());
    }
}
