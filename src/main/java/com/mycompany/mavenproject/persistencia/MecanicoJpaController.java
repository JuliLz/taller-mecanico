/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject.persistencia;

import com.mycompany.mavenproject.logica.Mecanico;
import com.mycompany.mavenproject.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author User
 */
public class MecanicoJpaController implements Serializable {

    public MecanicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MecanicoJpaController () {
        emf = Persistence.createEntityManagerFactory("testJPAPersistenceUnit");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mecanico mecanico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mecanico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mecanico mecanico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mecanico = em.merge(mecanico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = mecanico.getId();
                if (findMecanico(id) == null) {
                    throw new NonexistentEntityException("The mecanico with id " + id + " no longer exists.");
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
            Mecanico mecanico;
            try {
                mecanico = em.getReference(Mecanico.class, id);
                mecanico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mecanico with id " + id + " no longer exists.", enfe);
            }
            em.remove(mecanico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean tieneServiciosAsociados(int id) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT COUNT(s) FROM ServicioMecanico s WHERE s.mecanico.id = :id");
            query.setParameter("id", id);
            return ((Long) query.getSingleResult()) > 0;
        } finally {
            em.close();
        }
    }

    public List<Mecanico> findMecanicoEntities() {
        return findMecanicoEntities(true, -1, -1);
    }

    public List<Mecanico> findMecanicoEntities(int maxResults, int firstResult) {
        return findMecanicoEntities(false, maxResults, firstResult);
    }

    private List<Mecanico> findMecanicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mecanico.class));
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

    public Mecanico findMecanico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mecanico.class, id);
        } finally {
            em.close();
        }
    }

    public int getMecanicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mecanico> rt = cq.from(Mecanico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
