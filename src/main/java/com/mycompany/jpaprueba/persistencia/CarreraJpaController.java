package com.mycompany.jpaprueba.persistencia;

import com.mycompany.jpaprueba.persistencia.exceptions.NonexistentEntityException;
import com.mycompany.jpaprueba.logica.Carrera;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CarreraJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public CarreraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CarreraJpaController() {
        this.emf = Persistence.createEntityManagerFactory("pruebajpaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // --- CREAR ---
    public void create(Carrera carrera) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(carrera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // --- EDITAR ---
    public void edit(Carrera carrera) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            carrera = em.merge(carrera);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = carrera.getId(); // Ajusta el getter si tu clave primaria se llama distinto o es Long
                if (findCarrera(id) == null) {
                    throw new NonexistentEntityException("La carrera con id " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // --- ELIMINAR ---
    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrera carrera;
            try {
                carrera = em.getReference(Carrera.class, id);
                carrera.getId(); // Verifica existencia
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("La carrera con id " + id + " no existe.", enfe);
            }
            em.remove(carrera);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // --- CONSULTAR TODAS ---
    public List<Carrera> findCarreraEntities() {
        return findCarreraEntities(true, -1, -1);
    }

    public List<Carrera> findCarreraEntities(int maxResults, int firstResult) {
        return findCarreraEntities(false, maxResults, firstResult);
    }

    private List<Carrera> findCarreraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrera.class));
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

    // --- CONSULTAR POR ID ---
    public Carrera findCarrera(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    // --- CONTAR REGISTROS ---
    public int getCarreraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrera> rt = cq.from(Carrera.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}