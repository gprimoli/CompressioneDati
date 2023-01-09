package it.unisa.di.comparator.Positionend;

import it.unisa.di.table.positionend.element.PositionedElement;

import java.util.Comparator;

public class PositionalComparator implements Comparator<PositionedElement<?>> {

    @Override
    public int compare(PositionedElement o1, PositionedElement o2) {
        return o1.getPos() - o2.getPos();
    }

}
