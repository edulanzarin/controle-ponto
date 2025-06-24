package gui;

import javax.swing.JFrame;

public class MainWindow {

    private JFrame mainFrame;

    public MainWindow() {
        mainFrame = new JFrame("Controle de Ponto");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1024, 768);
        mainFrame.setVisible(true);
    }
}
