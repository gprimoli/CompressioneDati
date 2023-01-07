package it.unisa.di;

import java.awt.*;

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
    }
}
