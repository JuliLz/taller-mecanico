/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.mavenproject.logica.Auto;
import com.mycompany.mavenproject.logica.Cliente;
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
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ClienteJpaController () {
        emf = Persistence.createEntityManagerFactory("testJPAPersistenceUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getListaAutos() == null) {
            cliente.setListaAutos(new ArrayList<Auto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Auto> attachedListaAutos = new ArrayList<Auto>();
            for (Auto listaAutosAutoToAttach : cliente.getListaAutos()) {
                listaAutosAutoToAttach = em.getReference(listaAutosAutoToAttach.getClass(), listaAutosAutoToAttach.getId());
                attachedListaAutos.add(listaAutosAutoToAttach);
            }
            cliente.setListaAutos(attachedListaAutos);
            em.persist(cliente);
            for (Auto listaAutosAuto : cliente.getListaAutos()) {
                Cliente oldClienteOfListaAutosAuto = listaAutosAuto.getCliente();
                listaAutosAuto.setCliente(cliente);
                listaAutosAuto = em.merge(listaAutosAuto);
                if (oldClienteOfListaAutosAuto != null) {
                    oldClienteOfListaAutosAuto.getListaAutos().remove(listaAutosAuto);
                    oldClienteOfListaAutosAuto = em.merge(oldClienteOfListaAutosAuto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
            List<Auto> listaAutosOld = persistentCliente.getListaAutos();
            List<Auto> listaAutosNew = cliente.getListaAutos();
            List<Auto> attachedListaAutosNew = new ArrayList<Auto>();
            for (Auto listaAutosNewAutoToAttach : listaAutosNew) {
                listaAutosNewAutoToAttach = em.getReference(listaAutosNewAutoToAttach.getClass(), listaAutosNewAutoToAttach.getId());
                attachedListaAutosNew.add(listaAutosNewAutoToAttach);
            }
            listaAutosNew = attachedListaAutosNew;
            cliente.setListaAutos(listaAutosNew);
            cliente = em.merge(cliente);
            for (Auto listaAutosOldAuto : listaAutosOld) {
                if (!listaAutosNew.contains(listaAutosOldAuto)) {
                    listaAutosOldAuto.setCliente(null);
                    listaAutosOldAuto = em.merge(listaAutosOldAuto);
                }
            }
            for (Auto listaAutosNewAuto : listaAutosNew) {
                if (!listaAutosOld.contains(listaAutosNewAuto)) {
                    Cliente oldClienteOfListaAutosNewAuto = listaAutosNewAuto.getCliente();
                    listaAutosNewAuto.setCliente(cliente);
                    listaAutosNewAuto = em.merge(listaAutosNewAuto);
                    if (oldClienteOfListaAutosNewAuto != null && !oldClienteOfListaAutosNewAuto.equals(cliente)) {
                        oldClienteOfListaAutosNewAuto.getListaAutos().remove(listaAutosNewAuto);
                        oldClienteOfListaAutosNewAuto = em.merge(oldClienteOfListaAutosNewAuto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Auto> listaAutos = cliente.getListaAutos();
            for (Auto listaAutosAuto : listaAutos) {
                listaAutosAuto.setCliente(null);
                listaAutosAuto = em.merge(listaAutosAuto);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
