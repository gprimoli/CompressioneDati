package it.unisa.di.comparator;
import java.util.Comparator;

import it.unisa.di.wrapper.IntPositionedElement;
import it.unisa.di.wrapper.BaseElement;

public class IntContentComparator implements Comparator<BaseElement<?>> {
    @Override
    public int compare(BaseElement o1, BaseElement o2) {
        IntPositionedElement a = (IntPositionedElement) o1;
        IntPositionedElement b = (IntPositionedElement) o2;
        return Integer.compare(a.getContent(), b.getContent());
    }
}
