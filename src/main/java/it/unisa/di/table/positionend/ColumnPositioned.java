package it.unisa.di.table.positionend;

import it.unisa.di.GUI;
import it.unisa.di.common.Type;
import it.unisa.di.comparator.Positionend.FloatContentComparator;
import it.unisa.di.comparator.Positionend.IntContentComparator;
import it.unisa.di.comparator.Positionend.PositionalComparator;
import it.unisa.di.comparator.Positionend.StringContentComparator;
import it.unisa.di.table.positionend.element.FloatPositionedElement;
import it.unisa.di.table.positionend.element.IntPositionedElement;
import it.unisa.di.table.positionend.element.PositionedElement;
import it.unisa.di.table.positionend.element.StringPositionedElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ColumnPositioned implements Serializable {//L'assenza dello scoope non è un errore: Non voglio far usare questa classe al di fuori del package.
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final Type type;
    private final List<PositionedElement<?>> elements;

    public ColumnPositioned(String name, Type type) {
        this.name = name;
        this.type = type;
        this.elements = new LinkedList<>();
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

    public PositionedElement<?> getElement(int pos) {
        return pos < elements.size() ? elements.get(pos) : null;
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

    public void sortByPosition() {
        elements.sort(new PositionalComparator());
    }

    public static void SortAndFirstDifference(ColumnPositioned[] columns, GUI log) {
        List<Integer> currentColumnPositions = new ArrayList<>();
        List<Integer> lastColumnPositions = null;

        for (int i = 0; i < columns.length; i++) {
            log.addToLog("Sort della colonna: " + i);
            columns[i].sortByContent();

            List<PositionedElement<?>> currentColumnElements = columns[i].elements;//Placeholder
            PositionedElement<?> currentElement, lastElement = null;

            log.addToLog("PreProcessing della colonna: " + i);
            for (int y = 0; y < currentColumnElements.size(); y++) {
                currentElement = currentColumnElements.get(y);
                currentColumnPositions.add(currentElement.getPos());

                if (y > 0) {
                    var tmp = currentElement.clone();
                    currentElement.subContent(lastElement);
                    lastElement = tmp;
                } else {
                    lastElement = currentElement.clone();
                }

                if (i == 0) {
                    currentElement.subPos(y + 1);//Gli autori contano gli indici da 1
                } else {
                    if (y < lastColumnPositions.size())
                        currentElement.subPos(lastColumnPositions.get(y));
                }
            }

            lastColumnPositions = currentColumnPositions;
            currentColumnPositions = new ArrayList<>();
        }
    }

    public static void FirstAddAndSortBack(ColumnPositioned[] columns, GUI log) {
        List<Integer> currentColumnPositions = new ArrayList<>();
        List<Integer> lastColumnPositions = null;

        for (int i = 0; i < columns.length; i++) {
            List<PositionedElement<?>> currentColumnElements = columns[i].elements;//Placeholder
            PositionedElement<?> currentElement, lastElement = null;

            for (int y = 0; y < currentColumnElements.size(); y++) {
                currentElement = currentColumnElements.get(y);

                if (y > 0) {
                    currentElement.plusContent(lastElement);
                }
                lastElement = currentElement.clone();

                if (i == 0) {
                    currentElement.addPos(y + 1);//Gli autori contano gli indici da 1
                } else {
                    if (y < lastColumnPositions.size())
                        currentElement.addPos(lastColumnPositions.get(y));
                }

                currentColumnPositions.add(currentElement.getPos());
            }

            lastColumnPositions = currentColumnPositions;
            currentColumnPositions = new ArrayList<>();

            columns[i].sortByPosition();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Column Name: ").append(name).append("\nContent:\n");
        elements.forEach(sb::append);
        return sb.toString();
    }
}
