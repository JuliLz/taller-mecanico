
package com.mycompany.mavenproject.logica;

import com.mycompany.mavenproject.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.Date;

public class ControladoraLogica {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    public void crearMecanico (Mecanico mecanico) {
        controlPersis.crearMecanico (mecanico);
    }
    
    public boolean eliminarMecanico(int id) {
        return controlPersis.eliminarMecanico(id);
    }
    
    public void editarMecanico(Mecanico mecanico) {
        controlPersis.editarMecanico(mecanico);
    }
    
    public Mecanico consultarMecanico(int id) {
        return controlPersis.traerMecanico(id);
    }
    
    public ArrayList<Mecanico> traerListaMecanicos(){
        return controlPersis.traerListaMecanicos();
    }
    
    public void crearServicioMecanico(ServicioMecanico servicioMecanico){
        controlPersis.crearServicioMecanico(servicioMecanico);
    }
    
    public void editarServicioMecanico(ServicioMecanico servicioMecanico){
        controlPersis.editarServicioMecanico(servicioMecanico);
    }
    
    public void eliminarServicioMecanico(int id) {
        controlPersis.eliminarServicioMecanico (id);
    }
    
    public ServicioMecanico consultarServicioMecanico(int id) {
        return controlPersis.traerServicioMecanico(id);
    }
    
    public ArrayList<ServicioMecanico> traerListaServiciosMecanicos(){
        return controlPersis.traerListaServiciosMecanicos();
    }
    
    public void crearTipoServicio(TipoServicio tipoServicio){
        controlPersis.crearTipoServicio(tipoServicio);
    }
    
    public void editarTipoServicio(TipoServicio tipoServicio){
        controlPersis.editarTipoServicio(tipoServicio);
    }
    
    public boolean eliminarTipoServicio(int id) {
        return controlPersis.eliminarTipoServicio(id);
    }
    
    public TipoServicio consultarTipoServicio(int id) {
        return controlPersis.traerTipoServicio(id);
    }
    
    public ArrayList<TipoServicio> traerListaTiposServicios(){
        return controlPersis.traerListaTiposServicios();
    }
    
    public void crearCliente(Cliente cliente){
        controlPersis.crearCliente(cliente);
    }
    
    public void editarCliente(Cliente cliente){
        controlPersis.editarCliente(cliente);
    }
    
    public void eliminarCliente(int id) {
        controlPersis.eliminarCliente (id);
    }
    
    public Cliente consultarCliente(int id) {
        return controlPersis.traerCliente(id);
    }
    
    public ArrayList<Cliente> traerListaClientes(){
        return controlPersis.traerListaClientes();
    }
    
    public void crearAuto(Auto auto){
        controlPersis.crearAuto(auto);
    }
    
    public void editarAuto(Auto auto){
        controlPersis.editarAuto(auto);
    }
    
    public void eliminarAuto(int id) {
        controlPersis.eliminarAuto (id);
    }
    
    public Auto consultarAuto(int id) {
        return controlPersis.traerAuto(id);
    }
    
    public ArrayList<Auto> traerListaAutos(){
        return controlPersis.traerListaAutos();
    }
    
    public void darDeAltaServicioMecanico(double importe, 
            Mecanico mecanico, Auto auto, String descripcion, TipoServicio tipoServicio){
        
        int id = 0;
        Date fechaInicio = new Date();
        Date fechaFin = new Date(0000, 1, 1, 0, 0);
        boolean finalizado = false;
        
        ServicioMecanico servicioMecanicoADarDeAlta = new ServicioMecanico(id, fechaInicio, fechaFin,
        importe, mecanico, auto, descripcion, finalizado, tipoServicio);
        
        crearServicioMecanico(servicioMecanicoADarDeAlta);
 
    }
    
}
