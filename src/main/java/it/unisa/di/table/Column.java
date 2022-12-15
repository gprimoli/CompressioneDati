package it.unisa.di.table;

import it.unisa.di.common.Helper;
import it.unisa.di.common.Type;
import it.unisa.di.comparator.FloatContentComparator;
import it.unisa.di.comparator.IntContentComparator;
import it.unisa.di.comparator.StringContentComparator;
import it.unisa.di.wrapper.positinoed.FloatPositionedElement;
import it.unisa.di.wrapper.positinoed.IntPositionedElement;
import it.unisa.di.wrapper.positinoed.PositionedElement;
import it.unisa.di.wrapper.positinoed.StringPositionedElement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Column implements Cloneable {//L'assenza dello scoope non è un errore: Non voglio far usare questa classe al di fuori del package.
    private final String name;
    private final Type type;
    private List<BaseElement<?>> elements;

    private Column(String name, Type type) {
        this.name = name;
        this.type = type;
        this.elements = new LinkedList<>();
    }

    public static Column init(String entry, String name) {
        return new Column(name, Helper.whatIs(entry));
    }

    public void addElement(String entry, int pos) {
        elements.add(
                switch (type) {
                    case Integer -> new IntPositionedElement(entry, pos);
                    case Float -> new FloatPositionedElement(entry, pos);
                    default -> new StringPositionedElement(entry, pos);
                }
        );
    }

    public void sort() {
        switch (type) {
            case Integer -> elements.sort(new IntContentComparator());
            case Float -> elements.sort(new FloatContentComparator());
            default -> elements.sort(new StringContentComparator());
        }
    }

    public static void SortAndFirstDifference(Column[] columns) {
        int len = columns.length;
        Column tmpA, tmpB = null, col; //A = actual; B = Back
        for (int i = 0; i < len; i++) {
            col = columns[i];
            col.sort();

            tmpA = col.clone();

            for (int y = 0; y < col.elements.size(); y++) {
                if (y > 0) {
                    col.elements.get(y).subContent(tmpA.elements.get(y - 1));
                }

                if (i == 0) {
                    ((PositionedElement<?>) col.elements.get(y)).subPos(y);
                } else {
                    ((PositionedElement<?>) col.elements.get(y)).subPos((PositionedElement<?>) tmpB.elements.get(y));
                }
            }

            tmpB = tmpA;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Column Name: ").append(name).append("\nContent:\n");
        elements.forEach(sb::append);
        return sb.toString();
    }

    @Override
    public Column clone() {
        try {
            Column clone = (Column) super.clone();
            clone.elements = new LinkedList<>();
            for (BaseElement b: elements) {
                clone.elements.add(b.clone());
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
