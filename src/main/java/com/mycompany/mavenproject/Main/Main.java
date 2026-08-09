package com.mycompany.mavenproject.Main;





import com.mycompany.mavenproject.interfaz.Principal;
import com.mycompany.mavenproject.logica.Auto;
import com.mycompany.mavenproject.logica.Cliente;
import com.mycompany.mavenproject.logica.ControladoraLogica;
import com.mycompany.mavenproject.logica.Mecanico;
import com.mycompany.mavenproject.logica.ServicioMecanico;
import com.mycompany.mavenproject.logica.TipoServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        
        Principal pantallaPrincipal = new Principal();
        pantallaPrincipal.setVisible(true);
        pantallaPrincipal.setLocationRelativeTo(null);
        
        ControladoraLogica controladoraLogica = new ControladoraLogica();
        
        Cliente cliente1 = new Cliente(2, "Lucila", "Fava", 44974832, 489233, null);
//        controladoraLogica.crearCliente(cliente1);
        
        Mecanico mecanico1 = new Mecanico(2, "Jacinto", "Lopez", new Date());
//        controladoraLogica.crearMecanico(mecanico1);
        
        TipoServicio tipoServicio2 = new TipoServicio(201, "Cambio de aceite", "Cambio de aceite");
//        controladoraLogica.crearTipoServicio(tipoServicio2);
        
//        Auto auto1 = new Auto(0, "123123", "Nissan", "Skyline", 2, cliente1, null);
//        controladoraLogica.crearAuto(auto1);
        
        
//        ServicioMecanico servicioMecanico1 = new ServicioMecanico(10, new Date(), new Date(), 1000000, mecanico1, auto1, "Service Nissan Skyline", false, tipoServicio2);
//        controladoraLogica.crearServicioMecanico(servicioMecanico1);
        
//        Cliente cliente2 = controladoraLogica.consultarCliente(2);
//        Auto auto3 = controladoraLogica.consultarAuto(3);
        
//        System.out.println("mecanico: " + controladoraLogica.consultarMecanico(2));
//        System.out.println("auto: " + controladoraLogica.consultarAuto(3));
//        System.out.println("tipo de servicio" + controladoraLogica.consultarTipoServicio(201));
//        controladoraLogica.darDeAltaServicioMecanico(15000, mecanico1, auto2, "cambio de aceite skyline", tipoServicio2);
    }
}
