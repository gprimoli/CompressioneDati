package it.unisa.di;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser("src/main/resources");
        chooser.setFileFilter(new FileNameExtensionFilter("CSV Database", "csv"));

        if (chooser.showDialog(null, "Comprimi") == JFileChooser.APPROVE_OPTION) {
            File f = new File(chooser.getSelectedFile().getAbsolutePath());
            System.out.println(f);
        }
    }
}
