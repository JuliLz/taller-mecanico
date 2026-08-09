package com.mycompany.mavenproject.logica;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Auto implements Serializable {
    
    // Formato viejo: LLL NNN (ej: ABC123)
//    private static final Pattern PATENTE_VIEJA = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
    // Formato Mercosur: LL NNN LL (ej: AB123CD)
//    private static final Pattern PATENTE_MERCOSUR = Pattern.compile("^[A-Z]{2}[0-9]{3}[A-Z]{2}$");
    
    private static final Pattern patente_vieja = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
    private static final Pattern patente_nueva = Pattern.compile("^[A-Z]{2}[0-9]{3}[A-Z]{2}$");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(unique = true, nullable = false, length = 7)
    private String dominio;
    
    private String marca;
    private String modelo;
    private int capacidad;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(mappedBy = "auto")
    private List<ServicioMecanico> serviciosRealizados;

    @Override
    public String toString() {
        return " " + marca  + " " + modelo + " " +  dominio;
    }

    public Auto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ServicioMecanico> getServiciosRealizados() {
        return serviciosRealizados;
    }

    public void setServiciosRealizados(List<ServicioMecanico> serviciosRealizados) {
        this.serviciosRealizados = serviciosRealizados;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        if (dominio == null) {
            throw new IllegalArgumentException("La patente no puede estar vacía");
        }
        
        String patenteFormateada = dominio.trim().toUpperCase().replace(" ","");
        
        if (!esPatenteValida(patenteFormateada)){
            throw new IllegalArgumentException("La patente no tiene un formato válido");
        } else {
            this.dominio = patenteFormateada;
        }
    }
    
    public static boolean esPatenteValida(String dominio){
        if(dominio == null) {
            return false;
        }
        String dom = dominio.trim().toUpperCase().replace(" ", "");
        return patente_vieja.matcher(dom).matches() || patente_nueva.matcher(dom).matches();
    }
    
    public Auto(int id, String dominio, String marca, String modelo, int capacidad, Cliente cliente, List<ServicioMecanico> serviciosRealizados) {
        this.id = id;
        setDominio(dominio);
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.cliente = cliente;
        this.serviciosRealizados = serviciosRealizados;
    }

}
