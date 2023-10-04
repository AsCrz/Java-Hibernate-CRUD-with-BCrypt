package com.sistemaescola.dao;

import com.sistemaescola.modelos.Professor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ProfessorDAO {
    private EntityManagerFactory emf;

    public ProfessorDAO() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void salvarProfessor(Professor professor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(professor);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizarProfessor(Professor professor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(professor);
        em.getTransaction().commit();
        em.close();
    }

    public void excluirProfessor(Professor professor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        professor = em.merge(professor);
        em.remove(professor);
        em.getTransaction().commit();
        em.close();
    }

        public List<Professor> buscarTodosProfessores() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Professor p");
        List<Professor> professores = query.getResultList();
        em.close();
        return professores;
    }
}