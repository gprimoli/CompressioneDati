package it.unisa.di;

import it.unisa.di.table.DatabaseCSV;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI window = new GUI();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
                window.frame.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//        String in;
//        String out;
//        try {
//            if (args.length == 0) {
//                JFileChooser chooser = new JFileChooser("src/main/resources");
//                chooser.setFileFilter(new FileNameExtensionFilter("CSV Database", "csv"));
//                if (chooser.showDialog(null, "Comprimi") == JFileChooser.APPROVE_OPTION) {
//                    in = chooser.getSelectedFile().getAbsolutePath();
//                } else {
//                    in = "src/main/resources/example.csv";
//                }
//            } else {
//                in = args[0];
//            }
//
//            out = in + ".RER";
//            DatabaseCSV db = new DatabaseCSV(new FileReader(in));
//            db.compress(out, 5);
//        } catch (FileNotFoundException e) {
//            JOptionPane.showMessageDialog(null, "Path del file insesistente!",
//                    "Errore 404", JOptionPane.ERROR_MESSAGE);
//        }
    }
}
