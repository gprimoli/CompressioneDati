package it.unisa.di.comparator;

import it.unisa.di.wrapper.PositionedElement;

import java.util.Comparator;

public class PositionalCoparator implements Comparator<PositionedElement> {

    @Override
    public int compare(PositionedElement o1, PositionedElement o2) {
        return o1.getPos() - o2.getPos();
    }

}
