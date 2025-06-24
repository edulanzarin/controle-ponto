package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class MainPanel {

    private JPanel mainPanel;

    public MainPanel() {
        mainPanel = new JPanel(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        /* aba para adicionar registros de ponto */
        AdicionarRegistroPontoPanel adicionarPanel = new AdicionarRegistroPontoPanel();
        JPanel adicionarRegistrosPontoTab = adicionarPanel.getPanel();

        /* aba para visualizar registros de ponto */
        JPanel tab2 = new JPanel();

        tabbedPane.addTab("Adicionar Pontos", adicionarRegistrosPontoTab);
        tabbedPane.addTab("Visualizar Pontos", tab2);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
