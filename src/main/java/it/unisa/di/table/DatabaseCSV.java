package it.unisa.di.table;

import com.github.luben.zstd.ZstdOutputStream;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.unisa.di.GUI;
import it.unisa.di.common.Helper;
import it.unisa.di.exception.ReadRowException;
import net.jpountz.lz4.LZ4BlockOutputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorOutputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;
import org.xerial.snappy.SnappyOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class DatabaseCSV implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Column[] columns;
    private final List<String> header;
    private boolean compressed;

    public DatabaseCSV(FileReader f, GUI log) {
        compressed = false;
        header = new ArrayList<>();
        try {
            CSVReader r = new CSVReader(f);
            String[] row = r.readNext();

            _initHeader(row);

            columns = new Column[header.size()];
            int i = 1;
            while ((row = r.readNext()) != null) {
                if (i % 2500 == 0)
                    log.addToLog("Lette " + i + " righe");
                _buildColumns(row, i++);
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

    public void compress(FileOutputStream output, int alg, GUI log) {
        log.addToLog("\n====================INIZIO COMPRESSIONE====================");
        if (!compressed) {
            Column.SortAndFirstDifference(columns, log);
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
                case 7 -> os = new LZMACompressorOutputStream(output);
                default -> System.out.println("OK");
            }
            ObjectOutputStream stream = new ObjectOutputStream(os);
            stream.writeObject(this);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.addToLog("\n====================FINE COMPRESSIONE====================");
    }


    public void decompress(String input, int alg, GUI log) {
//        ObjectInputStream inputStream = new ObjectInputStream(new GZIPInputStream(new FileInputStream(output)));
//        DatabaseCSV check = (DatabaseCSV) inputStream.readObject();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tabella:");
        header.forEach(s -> sb.append(s).append("|"));
        sb.append(Arrays.toString(columns));
        return sb.toString();
    }
}
