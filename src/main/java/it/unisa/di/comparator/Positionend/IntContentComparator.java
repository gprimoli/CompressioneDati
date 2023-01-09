package it.unisa.di.comparator.Positionend;
import java.util.Comparator;

import it.unisa.di.table.positionend.element.IntPositionedElement;
import it.unisa.di.table.base.element.BaseElement;

public class IntContentComparator implements Comparator<BaseElement<?>> {
    @Override
    public int compare(BaseElement o1, BaseElement o2) {
        IntPositionedElement a = (IntPositionedElement) o1;
        IntPositionedElement b = (IntPositionedElement) o2;
        return Integer.compare(a.getContent(), b.getContent());
    }
}
