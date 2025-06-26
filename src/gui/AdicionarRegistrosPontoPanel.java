package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import model.TipoRegistroPonto;

public class AdicionarRegistrosPontoPanel {

    private JPanel registroPontoPanel;
    private Color backgroundColor = new Color(245, 248, 250);
    private Color primaryColor = new Color(51, 51, 51);
    private Color accentColor = new Color(0, 122, 255);
    private Color borderColor = new Color(225, 232, 237);

    public AdicionarRegistrosPontoPanel() {
        registroPontoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(backgroundColor);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        registroPontoPanel.setLayout(new BoxLayout(registroPontoPanel, BoxLayout.Y_AXIS));
        registroPontoPanel.setBorder(new EmptyBorder(30, 40, 40, 40));
        registroPontoPanel.setOpaque(false);

        // Título
        JLabel titulo = new JLabel("Registro de Ponto");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(primaryColor);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(new EmptyBorder(0, 0, 25, 0));
        registroPontoPanel.add(titulo);

        // Formulário centralizado
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
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
        JFormattedTextField dataField = createFormattedField("##/##/####");
        formPanel.add(createFieldPanel(dataField), gbc);

        // Hora
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(createLabel("Hora:"), gbc);

        gbc.gridx = 1;
        JFormattedTextField horaField = createFormattedField("##:##");
        formPanel.add(createFieldPanel(horaField), gbc);

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
        formPanel.add(createFieldPanel(obsScroll), gbc);

        // Botão Salvar
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        JButton salvarButton = new JButton("SALVAR REGISTRO") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g);
            }
        };
        styleButton(salvarButton);
        formPanel.add(salvarButton, gbc);

        registroPontoPanel.add(formPanel);
    }

    private JPanel createFieldPanel(JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(new CompoundBorder(
                new MatteBorder(1, 1, 1, 1, borderColor),
                new EmptyBorder(5, 10, 5, 10)));
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        label.setForeground(primaryColor);
        return label;
    }

    private JFormattedTextField createFormattedField(String mask) {
        try {
            MaskFormatter formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
            JFormattedTextField field = new JFormattedTextField(formatter);
            field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            field.setBorder(BorderFactory.createEmptyBorder());
            field.setOpaque(false);
            return field;
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }

    private void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(1, 1, 1, 1, borderColor),
                new EmptyBorder(5, 10, 5, 10)));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(5, 10, 5, 10));
                return this;
            }
        });
    }

    private void styleTextArea(JScrollPane scrollPane) {
        JTextArea textArea = (JTextArea) scrollPane.getViewport().getView();
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
        button.setBackground(accentColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 30, 12, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        // Efeito hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(accentColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(accentColor);
            }
        });
    }

    public JPanel getPanel() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(new Color(240, 242, 245));
        container.setBorder(new EmptyBorder(20, 20, 20, 20));
        container.add(registroPontoPanel, BorderLayout.CENTER);
        return container;
    }
}