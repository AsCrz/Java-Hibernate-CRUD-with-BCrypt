package com.sistemaescola.modelos;

import javax.persistence.*;

@Entity
public class Atividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudante_id")
    private Aluno estudante;

    private String nome, materia, dataEntrega;
    private double valor;

    public Atividades() {
    }

    public Atividades(Aluno aluno, String nome, String materia, String dataEntrega, double valor) {
        this.nome = nome;
        this.materia = materia;
        this.dataEntrega = dataEntrega;
        this.valor = valor;
        this.estudante = aluno;
    }

    //getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Aluno getEstudante() {
        return estudante;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    //setters
    public void setEstudante(Aluno estudante) {
        this.estudante = estudante;
    }

    @Override
    public String toString() {
        return "Atividades [id=" + id + ", estudante=" + estudante.getNome() + ", nome=" + nome + ", materia=" + materia
                + ", dataEntrega=" + dataEntrega + ", valor=" + valor + "]";
    }

    
}
