/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject.persistencia;

import com.mycompany.mavenproject.logica.Auto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.mavenproject.logica.Cliente;
import com.mycompany.mavenproject.logica.ServicioMecanico;
import com.mycompany.mavenproject.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class AutoJpaController implements Serializable {

    public AutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AutoJpaController () {
        emf = Persistence.createEntityManagerFactory("testJPAPersistenceUnit");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Auto auto) {
        if (auto.getServiciosRealizados() == null) {
            auto.setServiciosRealizados(new ArrayList<ServicioMecanico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = auto.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                auto.setCliente(cliente);
            }
            List<ServicioMecanico> attachedServiciosRealizados = new ArrayList<ServicioMecanico>();
            for (ServicioMecanico serviciosRealizadosServicioMecanicoToAttach : auto.getServiciosRealizados()) {
                serviciosRealizadosServicioMecanicoToAttach = em.getReference(serviciosRealizadosServicioMecanicoToAttach.getClass(), serviciosRealizadosServicioMecanicoToAttach.getId());
                attachedServiciosRealizados.add(serviciosRealizadosServicioMecanicoToAttach);
            }
            auto.setServiciosRealizados(attachedServiciosRealizados);
            em.persist(auto);
            if (cliente != null) {
                cliente.getListaAutos().add(auto);
                cliente = em.merge(cliente);
            }
            for (ServicioMecanico serviciosRealizadosServicioMecanico : auto.getServiciosRealizados()) {
                Auto oldAutoOfServiciosRealizadosServicioMecanico = serviciosRealizadosServicioMecanico.getAuto();
                serviciosRealizadosServicioMecanico.setAuto(auto);
                serviciosRealizadosServicioMecanico = em.merge(serviciosRealizadosServicioMecanico);
                if (oldAutoOfServiciosRealizadosServicioMecanico != null) {
                    oldAutoOfServiciosRealizadosServicioMecanico.getServiciosRealizados().remove(serviciosRealizadosServicioMecanico);
                    oldAutoOfServiciosRealizadosServicioMecanico = em.merge(oldAutoOfServiciosRealizadosServicioMecanico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Auto auto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Auto persistentAuto = em.find(Auto.class, auto.getId());
            Cliente clienteOld = persistentAuto.getCliente();
            Cliente clienteNew = auto.getCliente();
            List<ServicioMecanico> serviciosRealizadosOld = persistentAuto.getServiciosRealizados();
            List<ServicioMecanico> serviciosRealizadosNew = auto.getServiciosRealizados();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                auto.setCliente(clienteNew);
            }
            List<ServicioMecanico> attachedServiciosRealizadosNew = new ArrayList<ServicioMecanico>();
            for (ServicioMecanico serviciosRealizadosNewServicioMecanicoToAttach : serviciosRealizadosNew) {
                serviciosRealizadosNewServicioMecanicoToAttach = em.getReference(serviciosRealizadosNewServicioMecanicoToAttach.getClass(), serviciosRealizadosNewServicioMecanicoToAttach.getId());
                attachedServiciosRealizadosNew.add(serviciosRealizadosNewServicioMecanicoToAttach);
            }
            serviciosRealizadosNew = attachedServiciosRealizadosNew;
            auto.setServiciosRealizados(serviciosRealizadosNew);
            auto = em.merge(auto);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getListaAutos().remove(auto);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getListaAutos().add(auto);
                clienteNew = em.merge(clienteNew);
            }
            for (ServicioMecanico serviciosRealizadosOldServicioMecanico : serviciosRealizadosOld) {
                if (!serviciosRealizadosNew.contains(serviciosRealizadosOldServicioMecanico)) {
                    serviciosRealizadosOldServicioMecanico.setAuto(null);
                    serviciosRealizadosOldServicioMecanico = em.merge(serviciosRealizadosOldServicioMecanico);
                }
            }
            for (ServicioMecanico serviciosRealizadosNewServicioMecanico : serviciosRealizadosNew) {
                if (!serviciosRealizadosOld.contains(serviciosRealizadosNewServicioMecanico)) {
                    Auto oldAutoOfServiciosRealizadosNewServicioMecanico = serviciosRealizadosNewServicioMecanico.getAuto();
                    serviciosRealizadosNewServicioMecanico.setAuto(auto);
                    serviciosRealizadosNewServicioMecanico = em.merge(serviciosRealizadosNewServicioMecanico);
                    if (oldAutoOfServiciosRealizadosNewServicioMecanico != null && !oldAutoOfServiciosRealizadosNewServicioMecanico.equals(auto)) {
                        oldAutoOfServiciosRealizadosNewServicioMecanico.getServiciosRealizados().remove(serviciosRealizadosNewServicioMecanico);
                        oldAutoOfServiciosRealizadosNewServicioMecanico = em.merge(oldAutoOfServiciosRealizadosNewServicioMecanico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = auto.getId();
                if (findAuto(id) == null) {
                    throw new NonexistentEntityException("The auto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Auto auto;
            try {
                auto = em.getReference(Auto.class, id);
                auto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The auto with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = auto.getCliente();
            if (cliente != null) {
                cliente.getListaAutos().remove(auto);
                cliente = em.merge(cliente);
            }
            List<ServicioMecanico> serviciosRealizados = auto.getServiciosRealizados();
            for (ServicioMecanico serviciosRealizadosServicioMecanico : serviciosRealizados) {
                serviciosRealizadosServicioMecanico.setAuto(null);
                serviciosRealizadosServicioMecanico = em.merge(serviciosRealizadosServicioMecanico);
            }
            em.remove(auto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Auto> findAutoEntities() {
        return findAutoEntities(true, -1, -1);
    }

    public List<Auto> findAutoEntities(int maxResults, int firstResult) {
        return findAutoEntities(false, maxResults, firstResult);
    }

    private List<Auto> findAutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Auto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Auto findAuto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Auto.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Auto> rt = cq.from(Auto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
