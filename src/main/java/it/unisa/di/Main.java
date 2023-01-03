package it.unisa.di;

import it.unisa.di.table.DatabaseCSV;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        FileReader f;
        try {

            if (args.length == 0) {
                JFileChooser chooser = new JFileChooser("src/main/resources");
                chooser.setFileFilter(new FileNameExtensionFilter("CSV Database", "csv"));

                if (chooser.showDialog(null, "Comprimi") == JFileChooser.APPROVE_OPTION) {
                    f = new FileReader(chooser.getSelectedFile());
                } else {
                    f = new FileReader("src/main/resources/example.csv");
                }

            } else {
                f = new FileReader(args[0]);
            }

            DatabaseCSV db = new DatabaseCSV(f);
            db.compress("src/main/resources/example.csv.RER", 1);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Path del file insesistente!",
                    "Errore 404", JOptionPane.ERROR_MESSAGE);
        }
    }
}
