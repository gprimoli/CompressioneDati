package it.unisa.di;

import it.unisa.di.table.DatabaseCSV;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.sql.Timestamp;
import java.util.Objects;

public class GUI {
    public JFrame frame;
    public JTextArea log;

    public GUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Compressore RER");
        frame.setBounds(100, 100, 440, 355);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        JPanel selectPanel = new JPanel(new FlowLayout());
        selectPanel.add(new JLabel("Scegli l'algoritmo"));
        JComboBox<SelectItem> select = new JComboBox<>(new SelectItem[]{
                new SelectItem("GZIP", 1),
                new SelectItem("Zstd", 2),
                new SelectItem("LZ4", 3),
                new SelectItem("Snappy", 4),
                new SelectItem("BZip2", 5),
                new SelectItem("Deflate", 6),
        });
        selectPanel.add(Box.createHorizontalStrut(5));
        selectPanel.add(select);
        mainPanel.add(selectPanel);

        JPanel commandsPanel = new JPanel(new FlowLayout());

        JButton buttonCompress = new JButton("Comprimi");
        buttonCompress.setPreferredSize(new Dimension(150, 30));

        JButton buttonDecompress = new JButton("Decomprimi");
        buttonDecompress.setPreferredSize(new Dimension(150, 30));

        commandsPanel.add(buttonCompress);
        commandsPanel.add(Box.createHorizontalStrut(10));
        commandsPanel.add(buttonDecompress);

        mainPanel.add(commandsPanel);


        JPanel logPanel = new JPanel();
        log = new JTextArea(10, 40);
        log.setEditable(false);
        log.setText("");
        logPanel.add(new JLabel("Log"));
        logPanel.add(new JScrollPane(log));
        mainPanel.add(logPanel);


        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        frame.add(mainPanel);

        buttonCompress.addActionListener(event -> {
            addToLog("Scegliendo un file");

            SelectItem selectedItem = ((SelectItem) Objects.requireNonNull(select.getSelectedItem()));
            String in, out;
            JFileChooser chooser = new JFileChooser("src/main/resources");
            chooser.setFileFilter(new FileNameExtensionFilter("CSV Database", "csv"));

            if(chooser.showDialog(null, "Comprimi") != JFileChooser.APPROVE_OPTION){
                return;
            }

            in = chooser.getSelectedFile().getAbsolutePath();

            out = in + "_ALG_" + selectedItem.label.toUpperCase() + ".RER";
            try {
                DatabaseCSV db = new DatabaseCSV(new FileReader(in),this);
                db.compress(new FileOutputStream(out), selectedItem.value, this);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Path del file insesistente!",
                        "Errore 404", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonDecompress.addActionListener(event -> {
            addToLog("Scegliendo un file");

            SelectItem selectedItem = ((SelectItem) Objects.requireNonNull(select.getSelectedItem()));
            String in, out;
            JFileChooser chooser = new JFileChooser("src/main/resources");
            chooser.setFileFilter(new FileNameExtensionFilter("CSV Database", "RER"));

            if(chooser.showDialog(null, "Comprimi") != JFileChooser.APPROVE_OPTION){
                return;
            }

            in = chooser.getSelectedFile().getAbsolutePath();

            out = in.split("_ALG_")[0] + "_decompress.csv";
            try {
                DatabaseCSV db = DatabaseCSV.decompress(new FileInputStream(in), selectedItem.value,this);
                db.save(new FileWriter(out));
                addToLog("Fine salvataggio file decompresso");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Path del file insesistente!",
                        "Errore 404", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void addToLog(String s) {
        log.append("[" + new Timestamp(System.currentTimeMillis()) + "] " + s + "\n");
        log.repaint();
        log.revalidate();
    }

    private static class SelectItem {
        private final String label;
        private final int value;

        public SelectItem(String label, int value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return label;
        }
    }
}
