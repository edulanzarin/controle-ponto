package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import model.TipoRegistroPonto;

public class AdicionarRegistrosPontoPanel {

    private JPanel registroPontoPanel;
    private Color backgroundColor = Color.WHITE;
    private Color primaryColor = new Color(50, 50, 50);
    private Color accentColor = new Color(0, 122, 255);

    public AdicionarRegistrosPontoPanel() {
        registroPontoPanel = new JPanel();
        registroPontoPanel.setLayout(new BoxLayout(registroPontoPanel, BoxLayout.Y_AXIS));
        registroPontoPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        registroPontoPanel.setBackground(backgroundColor);

        // Título
        JLabel titulo = new JLabel("Registro de Ponto");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(primaryColor);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(new EmptyBorder(0, 0, 30, 0));
        registroPontoPanel.add(titulo);

        // Formulário centralizado
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tipo de Registro
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(createLabel("Tipo de Registro:"), gbc);

        gbc.gridx = 1;
        JComboBox<TipoRegistroPonto> tipoComboBox = new JComboBox<>(TipoRegistroPonto.values());
        styleComboBox(tipoComboBox);
        formPanel.add(tipoComboBox, gbc);

        // Data
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(createLabel("Data:"), gbc);

        gbc.gridx = 1;
        JFormattedTextField dataField = createFormattedField("####-##-##");
        formPanel.add(dataField, gbc);

        // Hora
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(createLabel("Hora:"), gbc);

        gbc.gridx = 1;
        JFormattedTextField horaField = createFormattedField("##:##");
        formPanel.add(horaField, gbc);

        // Observação
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(createLabel("Observação:"), gbc);

        gbc.gridx = 1;
        JTextArea obsArea = new JTextArea(3, 20);
        obsArea.setLineWrap(true);
        obsArea.setWrapStyleWord(true);
        JScrollPane obsScroll = new JScrollPane(obsArea);
        styleTextArea(obsScroll);
        formPanel.add(obsScroll, gbc);

        // Botão Salvar
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        JButton salvarButton = new JButton("SALVAR");
        styleButton(salvarButton);
        formPanel.add(salvarButton, gbc);

        registroPontoPanel.add(formPanel);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(primaryColor);
        return label;
    }

    private JFormattedTextField createFormattedField(String mask) {
        try {
            MaskFormatter formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
            JFormattedTextField field = new JFormattedTextField(formatter);
            field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
                    new EmptyBorder(5, 5, 5, 5)));
            field.setBackground(backgroundColor);
            return field;
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBackground(backgroundColor);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
                new EmptyBorder(5, 5, 5, 5)));
    }

    private void styleTextArea(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
                new EmptyBorder(5, 5, 5, 5)));
        scrollPane.setBackground(backgroundColor);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(accentColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 40, 12, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public JPanel getPanel() {
        return registroPontoPanel;
    }
}