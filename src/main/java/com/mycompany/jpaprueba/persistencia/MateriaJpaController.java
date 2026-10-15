package com.mycompany.jpaprueba.persistencia;

import com.mycompany.jpaprueba.logica.Materia;
import com.mycompany.jpaprueba.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MateriaJpaController {

    private EntityManagerFactory emf = null;

    public MateriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public MateriaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("pruebajpaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // --- CREAR ---
    public void create(Materia materia) {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(materia);

            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // --- EDITAR ---
    public void edit(Materia materia)
            throws NonexistentEntityException, Exception {

        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            materia = em.merge(materia);

            em.getTransaction().commit();

        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();

            if (msg == null || msg.length() == 0) {

                int id = materia.getId();

                if (findMateria(id) == null) {
                    throw new NonexistentEntityException(
                            "La materia con id " + id + " no existe."
                    );
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
    public void destroy(int id)
            throws NonexistentEntityException {

        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Materia materia;

            try {

                materia = em.getReference(Materia.class, id);

                // Verifica que exista
                materia.getId();

            } catch (EntityNotFoundException enfe) {

                throw new NonexistentEntityException(
                        "La materia con id " + id + " no existe.",
                        enfe
                );
            }

            em.remove(materia);

            em.getTransaction().commit();

        } finally {

            if (em != null) {
                em.close();
            }
        }
    }

    // --- CONSULTAR TODAS ---
    public List<Materia> findMateriaEntities() {
        return findMateriaEntities(true, -1, -1);
    }

    public List<Materia> findMateriaEntities(
            int maxResults,
            int firstResult) {

        return findMateriaEntities(
                false,
                maxResults,
                firstResult
        );
    }

    private List<Materia> findMateriaEntities(
            boolean all,
            int maxResults,
            int firstResult) {

        EntityManager em = getEntityManager();

        try {

            CriteriaQuery<Materia> cq =
                    em.getCriteriaBuilder()
                      .createQuery(Materia.class);

            cq.select(cq.from(Materia.class));

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
    public Materia findMateria(int id) {

        EntityManager em = getEntityManager();

        try {

            return em.find(Materia.class, id);

        } finally {

            em.close();
        }
    }

    // --- CONTAR REGISTROS ---
    public int getMateriaCount() {

        EntityManager em = getEntityManager();

        try {

            CriteriaQuery<Long> cq =
                    em.getCriteriaBuilder()
                      .createQuery(Long.class);

            Root<Materia> rt = cq.from(Materia.class);

            cq.select(
                    em.getCriteriaBuilder()
                      .count(rt)
            );

            Query q = em.createQuery(cq);

            return ((Long) q.getSingleResult()).intValue();

        } finally {

            em.close();
        }
    }
}