package it.unisa.di.comparator.Base;

import it.unisa.di.table.base.element.BaseElement;
import it.unisa.di.table.base.element.FloatBaseElement;
import it.unisa.di.table.positionend.element.FloatPositionedElement;

import java.util.Comparator;
public class FloatContentComparator implements Comparator<BaseElement<?>> {
    @Override
    public int compare(BaseElement o1, BaseElement o2) {
        FloatBaseElement a = (FloatBaseElement) o1;
        FloatBaseElement b = (FloatBaseElement) o2;
        return Float.compare(a.getContent(), b.getContent());
    }
}
