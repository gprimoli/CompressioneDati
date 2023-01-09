package it.unisa.di.comparator.Base;

import it.unisa.di.table.base.element.BaseElement;
import it.unisa.di.table.base.element.IntBaseElement;
import it.unisa.di.table.positionend.element.IntPositionedElement;

import java.util.Comparator;

public class IntContentComparator implements Comparator<BaseElement<?>> {
    @Override
    public int compare(BaseElement o1, BaseElement o2) {
        IntBaseElement a = (IntBaseElement) o1;
        IntBaseElement b = (IntBaseElement) o2;
        return Integer.compare(a.getContent(), b.getContent());
    }
}
