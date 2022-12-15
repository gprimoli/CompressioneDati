package it.unisa.di.comparator;
import java.util.Comparator;

import it.unisa.di.wrapper.positinoed.FloatPositionedElement;
import it.unisa.di.table.BaseElement;
public class FloatContentComparator implements Comparator<BaseElement<?>> {
    @Override
    public int compare(BaseElement o1, BaseElement o2) {
        FloatPositionedElement a = (FloatPositionedElement) o1;
        FloatPositionedElement b = (FloatPositionedElement) o2;
        return Float.compare(a.getContent(), b.getContent());
    }
}
