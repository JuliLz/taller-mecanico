package com.mycompany.mavenproject.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Pantalla principal de navegación del taller.
 *
 * <p>Las acciones de uso diario están separadas de las tareas de
 * administración para que la pantalla inicial sea más fácil de recorrer.</p>
 */
public class Principal extends JFrame {

    private static final Color BACKGROUND = new Color(245, 247, 250);
    private static final Color NAVY = new Color(25, 45, 70);
    private static final Color BLUE = new Color(35, 109, 190);
    private static final Color TEXT = new Color(38, 50, 56);
    private static final Color MUTED_TEXT = new Color(96, 108, 118);
    private static final Color CARD_BORDER = new Color(224, 229, 235);

    public Principal() {
        configurarVentana();
        initComponents();
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Taller mecánico");
        setMinimumSize(new Dimension(820, 620));
        setSize(980, 700);
        getContentPane().setBackground(BACKGROUND);
    }

    private void initComponents() {
        JPanel content = new JPanel(new BorderLayout(0, 24));
        content.setBackground(BACKGROUND);
        content.setBorder(new EmptyBorder(30, 42, 30, 42));

        content.add(crearEncabezado(), BorderLayout.NORTH);
        content.add(crearContenido(), BorderLayout.CENTER);

        setContentPane(content);
    }

    private JPanel crearEncabezado() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new javax.swing.BoxLayout(titles, javax.swing.BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Taller mecánico");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(NAVY);

        JLabel subtitle = new JLabel("Panel de control · Gestioná el trabajo del taller desde un solo lugar");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(MUTED_TEXT);
        subtitle.setBorder(new EmptyBorder(6, 0, 0, 0));

        titles.add(title);
        titles.add(subtitle);
        header.add(titles, BorderLayout.WEST);

        return header;
    }

    private JScrollPane crearContenido() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new javax.swing.BoxLayout(content, javax.swing.BoxLayout.Y_AXIS));

        content.add(crearSeccionOperativa());
        content.add(javax.swing.Box.createVerticalStrut(24));
        content.add(crearSeccionAdministracion());

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private JPanel crearSeccionOperativa() {
        JPanel section = new JPanel(new BorderLayout(0, 10));
        section.setOpaque(false);

        section.add(crearTituloSeccion("Operaciones", "Acciones principales del día"), BorderLayout.NORTH);

        JPanel actions = new JPanel(new GridLayout(1, 2, 16, 0));
        actions.setOpaque(false);
        actions.add(crearBotonPrincipal("Nuevo servicio", event -> abrir(new NuevoServicio())));
        actions.add(crearBotonSecundario("Ver servicios", event -> abrir(new ListaServiciosMecanicos())));
        section.add(actions, BorderLayout.CENTER);
        return section;
    }

    private JPanel crearSeccionAdministracion() {
        JPanel section = new JPanel(new BorderLayout(0, 10));
        section.setOpaque(false);
        section.add(crearTituloSeccion("Administración", "Clientes, vehículos y configuración del taller"),
                BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(2, 2, 16, 16));
        cards.setOpaque(false);
        cards.add(crearTarjeta("Clientes", "Alta y consulta de clientes",
                "Nuevo cliente", event -> abrir(new NuevoCliente()),
                "Ver clientes", event -> abrir(new ListaClientes())));
        cards.add(crearTarjeta("Vehículos", "Autos asociados a clientes",
                "Nuevo vehículo", event -> abrir(new NuevoVehiculo()),
                "Ver vehículos", event -> abrir(new ListaVehiculos())));
        cards.add(crearTarjeta("Mecánicos", "Equipo y personal del taller",
                "Nuevo mecánico", event -> abrir(new NuevoMecanico()),
                "Ver mecánicos", event -> abrir(new ListaMecanicos())));
        cards.add(crearTarjeta("Tipos de servicio", "Catálogo de trabajos disponibles",
                "Nuevo tipo", event -> abrir(new NuevoTipoServicio()),
                "Ver tipos", event -> abrir(new ListaTipoServicio())));
        section.add(cards, BorderLayout.CENTER);
        return section;
    }

    private JPanel crearTituloSeccion(String titleText, String description) {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JLabel title = new JLabel(titleText);
        title.setFont(new Font("Segoe UI", Font.BOLD, 19));
        title.setForeground(TEXT);

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descriptionLabel.setForeground(MUTED_TEXT);
        descriptionLabel.setBorder(new EmptyBorder(3, 0, 0, 0));

        JPanel labels = new JPanel();
        labels.setOpaque(false);
        labels.setLayout(new javax.swing.BoxLayout(labels, javax.swing.BoxLayout.Y_AXIS));
        labels.add(title);
        labels.add(descriptionLabel);
        titlePanel.add(labels, BorderLayout.WEST);
        return titlePanel;
    }

    private JPanel crearTarjeta(String titleText, String description, String primaryText,
            ActionListener primaryAction, String secondaryText, ActionListener secondaryAction) {
        JPanel card = new JPanel(new BorderLayout(0, 13));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CARD_BORDER),
                new EmptyBorder(18, 20, 18, 20)));

        JPanel labels = new JPanel();
        labels.setOpaque(false);
        labels.setLayout(new javax.swing.BoxLayout(labels, javax.swing.BoxLayout.Y_AXIS));

        JLabel title = new JLabel(titleText);
        title.setFont(new Font("Segoe UI", Font.BOLD, 17));
        title.setForeground(NAVY);
        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descriptionLabel.setForeground(MUTED_TEXT);
        descriptionLabel.setBorder(new EmptyBorder(4, 0, 0, 0));
        labels.add(title);
        labels.add(descriptionLabel);
        card.add(labels, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        buttons.setOpaque(false);
        buttons.add(crearBotonPequeno(primaryText, primaryAction, BLUE));
        buttons.add(crearBotonPequeno(secondaryText, secondaryAction, NAVY));
        card.add(buttons, BorderLayout.SOUTH);
        return card;
    }

    private JButton crearBotonPrincipal(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setOpaque(true);
        button.setBackground(BLUE);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 17));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(17, 12, 17, 12));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button.addActionListener(action);
        button.addMouseListener(crearHover(button, BLUE, new Color(28, 91, 163)));
        return button;
    }

    private JButton crearBotonSecundario(String text, ActionListener action) {
        JButton button = crearBotonPrincipal(text, action);
        button.setBackground(NAVY);
        button.addMouseListener(crearHover(button, NAVY, new Color(17, 33, 52)));
        return button;
    }

    private java.awt.event.MouseAdapter crearHover(JButton button, Color normal, Color hover) {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent event) {
                button.setBackground(hover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent event) {
                button.setBackground(normal);
            }
        };
    }

    private JButton crearBotonPequeno(String text, ActionListener action, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(color);
        button.setBackground(new Color(238, 244, 250));
        button.setFocusPainted(false);
        button.setMargin(new Insets(7, 10, 7, 10));
        button.setBorder(BorderFactory.createEmptyBorder(8, 11, 8, 11));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button.addActionListener(action);
        return button;
    }

    private void abrir(JFrame window) {
        window.setLocationRelativeTo(this);
        window.setVisible(true);
    }

}
