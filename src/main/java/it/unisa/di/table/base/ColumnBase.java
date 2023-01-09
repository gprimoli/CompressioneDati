package it.unisa.di.table.base;

import it.unisa.di.common.Type;
import it.unisa.di.comparator.Base.FloatContentComparator;
import it.unisa.di.comparator.Base.IntContentComparator;
import it.unisa.di.comparator.Base.StringContentComparator;
import it.unisa.di.table.base.element.BaseElement;
import it.unisa.di.table.base.element.FloatBaseElement;
import it.unisa.di.table.base.element.IntBaseElement;
import it.unisa.di.table.base.element.StringBaseElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

class ColumnBase implements Serializable {//L'assenza dello scoope non è un errore: Non voglio far usare questa classe al di fuori del package.
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final Type type;
    private final List<BaseElement<?>> elements;

    public ColumnBase(String name, Type type) {
        this.name = name;
        this.type = type;
        this.elements = new LinkedList<>();
    }

    public void addElement(String entry) {
        elements.add(
                switch (type) {
                    case Integer -> new IntBaseElement(entry);
                    case Float -> new FloatBaseElement(entry);
                    default -> new StringBaseElement(entry);
                }
        );
    }

    public int size() {
        return elements.size();
    }

    public void sortByContent() {
        switch (type) {
            case Integer -> elements.sort(new IntContentComparator());
            case Float -> elements.sort(new FloatContentComparator());
            default -> elements.sort(new StringContentComparator());
        }
    }

    public BaseElement<?> getElement(int pos) {
        return pos < elements.size() ? elements.get(pos) : null;
    }

    public static void SortAndFirstDifference(ColumnBase[] columns) {
        for (ColumnBase column : columns) {
            column.sortByContent();

            List<BaseElement<?>> currentColumnElements = column.elements;//Placeholder
            BaseElement<?> currentElement, lastElement = null;

            for (int y = 0; y < currentColumnElements.size(); y++) {
                currentElement = currentColumnElements.get(y);

                if (y > 0) {
                    var tmp = currentElement.clone();
                    currentElement.subContent(lastElement);
                    lastElement = tmp;
                } else {
                    lastElement = currentElement.clone();
                }

            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Column Name: ").append(name).append("\nContent:\n");
        elements.forEach(sb::append);
        return sb.toString();
    }
}
