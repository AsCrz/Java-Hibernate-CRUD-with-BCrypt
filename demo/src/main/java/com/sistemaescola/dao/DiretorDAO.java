package com.sistemaescola.dao;

import com.sistemaescola.modelos.Diretor;
import com.sistemaescola.modelos.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DiretorDAO {
    private EntityManagerFactory emf;

    public DiretorDAO() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void salvarDiretor(Diretor diretor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(diretor);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizarDiretor(Diretor diretor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(diretor);
        em.getTransaction().commit();
        em.close();
    }

    public void excluirDiretor(Diretor diretor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        diretor = em.merge(diretor);
        em.remove(diretor);
        em.getTransaction().commit();
        em.close();
    }

        public List<Diretor> buscarTodosDiretores() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT d FROM Diretor d");
        List<Diretor> diretores = query.getResultList();
        em.close();
        return diretores;
    }

        public Diretor buscarDiretorPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Diretor diretor = em.find(Diretor.class, id);
        em.close();
        return diretor;
    }
}
