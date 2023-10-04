package com.sistemaescola.dao;

import com.sistemaescola.modelos.Atividades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class AtividadesDAO {

    private EntityManagerFactory entityManagerFactory;

    public AtividadesDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void salvarAtividade(Atividades atividade) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(atividade);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void atualizarAtividade(Atividades atividade) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(atividade);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void excluirAtividade(Atividades atividade) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Atividades atividadeParaExcluir = entityManager.find(Atividades.class, atividade.getId());
            entityManager.remove(atividadeParaExcluir);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Atividades buscarAtividadePorId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Atividades.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Atividades> buscarTodasAtividades() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT a FROM Atividades a", Atividades.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }
}
