package it.unisa.di.table.base;

import com.github.luben.zstd.ZstdOutputStream;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.unisa.di.common.Helper;
import it.unisa.di.exception.ReadRowException;
import net.jpountz.lz4.LZ4BlockOutputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorOutputStream;
import org.xerial.snappy.SnappyOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class DatabaseCSVBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ColumnBase[] columns;
    private final List<String> header;
    private boolean compressed;

    public DatabaseCSVBase(FileReader f) {
        compressed = false;
        header = new ArrayList<>();
        try {
            CSVReader r = new CSVReader(f);
            String[] row = r.readNext();

            _initHeader(row);

            columns = new ColumnBase[header.size()];

            while ((row = r.readNext()) != null) {
                _buildColumns(row);
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

    private void _buildColumns(String[] row) throws Exception {
        if (row.length != header.size()) {
            throw new ReadRowException("La row: " + Arrays.toString(row) + " ha un numero differente di entry rispetto all'header!");
        }

        for (int i = 0; i < row.length; i++) {
            if (row[i].compareTo("") != 0) {
                if (columns[i] == null) {
                    columns[i] = new ColumnBase(header.get(i), Helper.whatIs(row[i]));
                }
                columns[i].addElement(row[i]);
            }
        }
    }

    public void compress(FileOutputStream output, int alg) {
        if (!compressed) {
            ColumnBase.SortAndFirstDifference(columns);
            compressed = true;
        }
        try {
            OutputStream os = null;
            switch (alg) {
                case 1 -> os = new GZIPOutputStream(output);
                case 2 -> os = new ZstdOutputStream(output);
                case 3 -> os = new LZ4BlockOutputStream(output);
                case 4 -> os = new SnappyOutputStream(output);
                case 5 -> os = new BZip2CompressorOutputStream(output);
                case 6 -> os = new DeflateCompressorOutputStream(output);
                default -> System.out.println("OK");
            }
            ObjectOutputStream stream = new ObjectOutputStream(os);
            stream.writeObject(this);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
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
