package it.unisa.di.table;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.unisa.di.exception.ReadRowException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
    private final Column[] columns;
    private final List<String> header;


    public Table(FileReader f) {
        header = new ArrayList<>();
        try {
            CSVReader r = new CSVReader(f);
            String[] row = r.readNext();

            _initHeader(row);
            columns = new Column[header.size()];

            int pos = 0;
            while ((row = r.readNext()) != null) {
                _buildColumns(row, ++pos);
            }

        } catch (CsvValidationException e) {
            System.err.println("Formattazione CSV non valida. (Usa la \",\" per separare ogni entry e \";\" per dividere le row)");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void _initHeader(String[] row) {
        header.addAll(Arrays.asList(row));
    }

    private void _buildColumns(String[] row, int pos) throws Exception {
        if (row.length != header.size()) {
            throw new ReadRowException("La row: " + Arrays.toString(row) + " ha un numero differente di entry rispetto all'header!");
        }

        for (int i = 0; i < row.length; i++) {
            if (row[i].compareTo("") != 0) {
                if (columns[i] == null) {
                    columns[i] = Column.init(row[i], header.get(i));
                }
                columns[i].addElement(row[i], pos);
            }
        }
    }

    public void compress() {
        Column.SortAndFirstDifference(columns);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tabella:");
        header.forEach(s -> sb.append(s).append("|"));
        sb.append(Arrays.toString(columns));
        return sb.toString();
    }
}
