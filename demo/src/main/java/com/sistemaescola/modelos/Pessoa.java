package com.sistemaescola.modelos;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa")
public class Pessoa{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    protected String nome, rgm, senha, dataNascimento;

    

    public Pessoa(){
    }

    public Pessoa(String nome, String rgm, String senha, String dataNascimento) {
        this.nome = nome;
        this.senha = senha;
        this.rgm = rgm;
        this.dataNascimento = dataNascimento;
    }
    
//getters
    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getRgm() {
        return rgm;
    }

    public String getSenha() {
        return senha;
    }

//setters
    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setRgm(String rgm) {
        this.rgm = rgm;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }


    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + id + ", nome=" + nome + ", rgm=" + rgm + ", senha=" + senha + ", dataNascimento="
                + dataNascimento ;
    }
    
    

}