package com.sistemaescola.dao;

import com.sistemaescola.modelos.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AlunoDAO {
    private EntityManagerFactory emf;

    public AlunoDAO() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void salvarAluno(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizarAluno(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(aluno);
        em.getTransaction().commit();
        em.close();
    }

    public void excluirAluno(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        aluno = em.merge(aluno);
        em.remove(aluno);
        em.getTransaction().commit();
        em.close();
    }

    public List<Aluno> buscarTodosAlunos() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT a FROM Aluno a");
        List<Aluno> alunos = query.getResultList();
        em.close();
        return alunos;
    }

        public Aluno buscarAlunoPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        Aluno aluno = em.find(Aluno.class, id);
        em.close();
        return aluno;
    }
}