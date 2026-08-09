
package com.mycompany.mavenproject.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ServicioMecanico implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    private double importe;
    @ManyToOne
    private Mecanico mecanico; 
    @ManyToOne
    private Auto auto; 
    private String descripcion;
    private boolean finalizado;
    @ManyToOne
    private TipoServicio tipoServicio; 
    
    @Override
    public String toString() {
        return "ServicioMecanico{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", importe=" + importe + ", mecanico=" + mecanico + ", auto=" + auto + ", descripcion=" + descripcion + ", finalizado=" + finalizado + ", tipoServicio=" + tipoServicio + '}';
    }
    
    public ServicioMecanico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public ServicioMecanico(int id, Date fechaInicio, Date fechaFin, double importe, Mecanico mecanico, Auto auto, String descripcion, boolean finalizado, TipoServicio tipoServicio) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.importe = importe;
        this.mecanico = mecanico;
        this.auto = auto;
        this.descripcion = descripcion;
        this.finalizado = finalizado;
        this.tipoServicio = tipoServicio;
    }
}
