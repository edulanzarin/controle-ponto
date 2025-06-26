package gui;

import java.awt.Dimension;

import javax.swing.*;

public class MainWindow {

    private JFrame mainFrame;

    public MainWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // Estilo minimalista para os componentes
            UIManager.put("TabbedPane.contentBorderInsets", new java.awt.Insets(0, 0, 0, 0));
            UIManager.put("TabbedPane.tabsOverlapBorder", true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainFrame = new JFrame("Controle de Ponto");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 500);
        mainFrame.setMinimumSize(new Dimension(600, 600));
        mainFrame.setLocationRelativeTo(null);

        MainPanel panel = new MainPanel();
        mainFrame.add(panel.getPanel());

        mainFrame.setVisible(true);
    }
}