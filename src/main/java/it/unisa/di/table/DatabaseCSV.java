package it.unisa.di.table;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.unisa.di.common.Helper;
import it.unisa.di.exception.ReadRowException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class DatabaseCSV implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Column[] columns;
    private final List<String> header;
    private boolean compressed;

    public DatabaseCSV(FileReader f) {
        compressed = false;
        header = new ArrayList<>();
        try {
            CSVReader r = new CSVReader(f);
            String[] row = r.readNext();

            _initHeader(row);
            columns = new Column[header.size()];

            for (int i = 1; (row = r.readNext()) != null; i++) {//i = 1 perchè 0 è l'Header
                _buildColumns(row, i);
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
                    columns[i] = Column.init(header.get(i), Helper.whatIs(row[i]));
                }
                columns[i].addElement(row[i], pos);
            }
        }
    }

    public void compress(String output, int alg) {
        if (!compressed) {
            Column.SortAndFirstDifference(columns);
            compressed = true;
        }
        switch (alg){
            case 1 -> {
                try {
                    ObjectOutputStream stream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(output)));
                    stream.writeObject(this);
                    stream.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            case 2 -> {
                System.out.println("KO");
            }
            default -> {
                System.out.println("OK");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tabella:");
        header.forEach(s -> sb.append(s).append("|"));
        sb.append(Arrays.toString(columns));
        return sb.toString();
    }
}
