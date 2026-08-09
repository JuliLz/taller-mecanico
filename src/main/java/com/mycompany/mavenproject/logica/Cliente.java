package com.mycompany.mavenproject.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String nombre;
    private String apellido;
    private long documento;
    private long telefono;
    @OneToMany (mappedBy="cliente")
    private List<Auto> listaAutos; 

    public Cliente() {
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }

    public List<Auto> getListaAutos() {
        return listaAutos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setListaAutos(List<Auto> listaAutos) {
        this.listaAutos = listaAutos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public Cliente(int id, String nombre, String apellido, int documento, int telefono, List<Auto> listaAutos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.telefono = telefono;
        this.listaAutos = listaAutos;
    }
}
