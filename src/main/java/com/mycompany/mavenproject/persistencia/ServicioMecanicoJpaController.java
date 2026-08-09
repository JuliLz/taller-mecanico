
package com.mycompany.mavenproject.persistencia;

import com.mycompany.mavenproject.logica.ServicioMecanico;
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

public class ServicioMecanicoJpaController implements Serializable {

    public ServicioMecanicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ServicioMecanicoJpaController () {
        emf = Persistence.createEntityManagerFactory("testJPAPersistenceUnit");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioMecanico servicioMecanico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(servicioMecanico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioMecanico servicioMecanico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            servicioMecanico = em.merge(servicioMecanico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicioMecanico.getId();
                if (findServicioMecanico(id) == null) {
                    throw new NonexistentEntityException("The servicioMecanico with id " + id + " no longer exists.");
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
            ServicioMecanico servicioMecanico;
            try {
                servicioMecanico = em.getReference(ServicioMecanico.class, id);
                servicioMecanico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioMecanico with id " + id + " no longer exists.", enfe);
            }
            em.remove(servicioMecanico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioMecanico> findServicioMecanicoEntities() {
        return findServicioMecanicoEntities(true, -1, -1);
    }

    public List<ServicioMecanico> findServicioMecanicoEntities(int maxResults, int firstResult) {
        return findServicioMecanicoEntities(false, maxResults, firstResult);
    }

    private List<ServicioMecanico> findServicioMecanicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioMecanico.class));
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

    public ServicioMecanico findServicioMecanico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioMecanico.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioMecanicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioMecanico> rt = cq.from(ServicioMecanico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
