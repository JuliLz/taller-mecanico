
package com.mycompany.mavenproject.persistencia;

import com.mycompany.mavenproject.logica.Auto;
import com.mycompany.mavenproject.logica.Cliente;
import com.mycompany.mavenproject.logica.Mecanico;
import com.mycompany.mavenproject.logica.ServicioMecanico;
import com.mycompany.mavenproject.logica.TipoServicio;
import com.mycompany.mavenproject.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    MecanicoJpaController mecJpa = new MecanicoJpaController();
    ServicioMecanicoJpaController servMecJpa = new ServicioMecanicoJpaController();
    TipoServicioJpaController tipoServJpa = new TipoServicioJpaController();
    ClienteJpaController clienteJpa = new ClienteJpaController();
    AutoJpaController autoJpa = new AutoJpaController();

    public void eliminarMecanico(int id) {
        try {
            mecJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "no hay mecanico con ese id", ex);
        }
    }
    
    public void crearMecanico(Mecanico mecanico) {
        mecJpa.create(mecanico);
    }

    public void editarMecanico(Mecanico mecanico) {
        try {
            mecJpa.edit(mecanico);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "No hay mecanico con ese id", ex);
        }
    }

    public Mecanico traerMecanico(int id) {
        return mecJpa.findMecanico(id);
    }

    public ArrayList<Mecanico> traerListaMecanicos() {
        
        List<Mecanico> listaMecanicosTemporal = mecJpa.findMecanicoEntities();
        ArrayList<Mecanico> listaMecanicos = new ArrayList<Mecanico> (listaMecanicosTemporal);
        return listaMecanicos;
    }

    public void crearServicioMecanico(ServicioMecanico servicioMecanico) {
        servMecJpa.create(servicioMecanico);
    }

    public void editarServicioMecanico(ServicioMecanico servicioMecanico) {
        try {
            servMecJpa.edit(servicioMecanico);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarServicioMecanico(int id) {
        try {
            servMecJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServicioMecanico traerServicioMecanico(int id) {
        return servMecJpa.findServicioMecanico(id);
    }

    public ArrayList<ServicioMecanico> traerListaServiciosMecanicos() {
        List<ServicioMecanico> listaServiciosMecanicosTemporal = servMecJpa.findServicioMecanicoEntities();
        ArrayList<ServicioMecanico> listaServiciosMecanicos = new ArrayList<ServicioMecanico> (listaServiciosMecanicosTemporal);
        return listaServiciosMecanicos;
    }

    public void crearTipoServicio(TipoServicio tipoServicio) {
        tipoServJpa.create(tipoServicio);
    }

    public void editarTipoServicio(TipoServicio tipoServicio) {
        try {
            tipoServJpa.edit(tipoServicio);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarTipoServicio(int id) {
        try {
            tipoServJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoServicio traerTipoServicio(int id) {
        return tipoServJpa.findTipoServicio(id);
    }

    public ArrayList<TipoServicio> traerListaTiposServicios() {
        List<TipoServicio> listaTiposServiciosTemporal = tipoServJpa.findTipoServicioEntities();
        ArrayList<TipoServicio> listaTiposServicios = new ArrayList<TipoServicio> (listaTiposServiciosTemporal);
        return listaTiposServicios;
    }

    public void crearCliente(Cliente cliente) {
        clienteJpa.create(cliente);
    }

    public void editarCliente(Cliente cliente) {
        try {
            clienteJpa.edit(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarCliente(int id) {
        try {
            clienteJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente traerCliente(int id) {
        return clienteJpa.findCliente(id);
    }

    public ArrayList<Cliente> traerListaClientes() {
        List<Cliente> listaClientesTemporal = clienteJpa.findClienteEntities();
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente> (listaClientesTemporal);
        return listaClientes;
    }

    public void crearAuto(Auto auto) {
        autoJpa.create(auto);
    }

    public void editarAuto(Auto auto) {
        try {
            autoJpa.edit(auto);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarAuto(int id) {
        try {
            autoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Auto traerAuto(int id) {
        return autoJpa.findAuto(id);
    }

    public ArrayList<Auto> traerListaAutos() {
        List<Auto> listaAutosTemporal = autoJpa.findAutoEntities();
        ArrayList<Auto> listaAutos = new ArrayList<Auto> (listaAutosTemporal);
        return listaAutos;
    }
}
