package gui;

import javax.swing.*;
import java.awt.*;

public class MainPanel {

    private JPanel mainPanel;

    public MainPanel() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Aba de registro
        AdicionarRegistrosPontoPanel adicionarPanel = new AdicionarRegistrosPontoPanel();
        tabbedPane.addTab("Novo Registro", adicionarPanel.getPanel());

        // Aba de visualização
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBackground(Color.WHITE);
        viewPanel.add(new JLabel(
                "<html><center><h2>Visualização de Registros</h2><p>Nenhum registro encontrado</p></center></html>",
                SwingConstants.CENTER));
        tabbedPane.addTab("Visualizar", viewPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}