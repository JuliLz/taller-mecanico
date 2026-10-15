package com.mycompany.mavenproject.Main;

import com.mycompany.mavenproject.interfaz.Principal;
import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Principal pantallaPrincipal = new Principal();
            pantallaPrincipal.setLocationRelativeTo(null);
            pantallaPrincipal.setVisible(true);
        });
    }
}
