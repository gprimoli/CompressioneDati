package it.unisa.di.table;

import it.unisa.di.GUI;
import it.unisa.di.common.Type;
import it.unisa.di.comparator.FloatContentComparator;
import it.unisa.di.comparator.IntContentComparator;
import it.unisa.di.comparator.StringContentComparator;
import it.unisa.di.wrapper.positinoed.FloatPositionedElement;
import it.unisa.di.wrapper.positinoed.IntPositionedElement;
import it.unisa.di.wrapper.positinoed.PositionedElement;
import it.unisa.di.wrapper.positinoed.StringPositionedElement;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Column implements Serializable {//L'assenza dello scoope non è un errore: Non voglio far usare questa classe al di fuori del package.
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final Type type;
    private final List<BaseElement<?>> elements;

    private Column(String name, Type type) {
        this.name = name;
        this.type = type;
        this.elements = new LinkedList<>();
    }

    public static Column init(String name, Type type) {
        return new Column(name, type);
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

    public int size(){
        return elements.size();
    }

    public void sort() {
        switch (type) {
            case Integer -> elements.sort(new IntContentComparator());
            case Float -> elements.sort(new FloatContentComparator());
            default -> elements.sort(new StringContentComparator());
        }
    }

    public static void SortAndFirstDifference(Column[] columns, GUI log) {
        List<Integer> currentColumnPositions = new ArrayList<>();
        List<Integer> lastColumnPositions = null;

        for (int i = 0; i < columns.length; i++) {
            log.addToLog("Sort della colonna: " + i);
            columns[i].sort();
            log.addToLog("Fine Sort della colonna: " + i);

            List<BaseElement<?>> currentColumnElements = columns[i].elements;//Placeholder
            PositionedElement<?> currentElement, lastElement = null;

            log.addToLog("PreProcessing della colonna: " + i);
            for (int y = 0; y < currentColumnElements.size(); y++) {
                currentElement = ((PositionedElement<?>) currentColumnElements.get(y));
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
            log.addToLog("Fine PreProcessing della colonna: " + i);

            lastColumnPositions = currentColumnPositions;
            currentColumnPositions = new ArrayList<>();
        }
    }

    public int[] getAllPosition() {//Exstend
        int[] intArray = new int[elements.size()];
        for (int i = 0; i < elements.size(); i++){
            intArray[i] = ((PositionedElement<?>) elements.get(i)).getPos();
        }
        return intArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Column Name: ").append(name).append("\nContent:\n");
        elements.forEach(sb::append);
        return sb.toString();
    }
}
