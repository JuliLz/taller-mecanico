package com.mycompany.mavenproject.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

/**
 * Estilos compartidos para las ventanas de gestión.
 */
final class EstiloInterfaz {

    static final Color FONDO = new Color(245, 247, 250);
    static final Color AZUL_OSCURO = new Color(25, 45, 70);
    static final Color AZUL = new Color(35, 109, 190);
    static final Color TEXTO = new Color(38, 50, 56);
    static final Color BORDE = new Color(218, 225, 232);

    private EstiloInterfaz() {
    }

    static void aplicar(JFrame ventana) {
        ventana.getContentPane().setBackground(FONDO);
        aplicarComponente(ventana.getContentPane());
    }

    private static void aplicarComponente(Component componente) {
        if (componente instanceof JLabel) {
            JLabel label = (JLabel) componente;
            if (label.getFont().getSize() >= 30) {
                label.setFont(new Font("Segoe UI", Font.BOLD, 28));
                label.setForeground(AZUL_OSCURO);
                label.setHorizontalAlignment(JLabel.CENTER);
            } else {
                label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                label.setForeground(TEXTO);
            }
        } else if (componente instanceof JButton) {
            JButton button = (JButton) componente;
            button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setBackground(AZUL);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
            button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        } else if (componente instanceof JTable) {
            JTable table = (JTable) componente;
            table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            table.setForeground(TEXTO);
            table.setRowHeight(30);
            table.setGridColor(BORDE);
            table.setSelectionBackground(new Color(218, 234, 249));
            table.setSelectionForeground(TEXTO);
            JTableHeader header = table.getTableHeader();
            header.setFont(new Font("Segoe UI", Font.BOLD, 13));
            header.setForeground(Color.WHITE);
            header.setBackground(AZUL_OSCURO);
            header.setReorderingAllowed(false);
        } else if (componente instanceof JTextField || componente instanceof JTextArea
                || componente instanceof JComboBox) {
            JComponent input = (JComponent) componente;
            input.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            input.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDE),
                    new EmptyBorder(7, 9, 7, 9)));
        } else if (componente instanceof JScrollPane) {
            componente.setBackground(Color.WHITE);
            ((JScrollPane) componente).setBorder(BorderFactory.createLineBorder(BORDE));
        }

        if (componente instanceof Container) {
            for (Component child : ((Container) componente).getComponents()) {
                aplicarComponente(child);
            }
        }

    }
}
