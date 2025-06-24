package gui;

import javax.swing.*;
import java.awt.*;
import model.TipoRegistroPonto;

public class AdicionarRegistroPontoPanel {

    private JPanel registroPontoPanel;

    public AdicionarRegistroPontoPanel() {
        registroPontoPanel = new JPanel();
        registroPontoPanel.setLayout(new BoxLayout(registroPontoPanel, BoxLayout.Y_AXIS));
        registroPontoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Margens

        // Tipo de Registro
        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tipoPanel.add(new JLabel("Tipo de Registro:"));
        JComboBox<TipoRegistroPonto> tipoComboBox = new JComboBox<>(TipoRegistroPonto.values());
        tipoPanel.add(tipoComboBox);

        // Data
        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dataPanel.add(new JLabel("Data (AAAA-MM-DD):"));
        JTextField dataField = new JTextField(10);
        dataPanel.add(dataField);

        // Hora
        JPanel horaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        horaPanel.add(new JLabel("Hora (HH:MM):"));
        JTextField horaField = new JTextField(5);
        horaPanel.add(horaField);

        // Observação
        JPanel obsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        obsPanel.add(new JLabel("Observação:"));
        JTextArea obsArea = new JTextArea(3, 20);
        obsArea.setLineWrap(true);
        obsArea.setWrapStyleWord(true);
        JScrollPane obsScroll = new JScrollPane(obsArea);
        obsPanel.add(obsScroll);

        // Botão Salvar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton salvarButton = new JButton("Salvar");
        buttonPanel.add(salvarButton);

        // Adiciona tudo ao painel principal
        registroPontoPanel.add(tipoPanel);
        registroPontoPanel.add(dataPanel);
        registroPontoPanel.add(horaPanel);
        registroPontoPanel.add(obsPanel);
        registroPontoPanel.add(buttonPanel);
    }

    public JPanel getPanel() {
        return registroPontoPanel;
    }
}
