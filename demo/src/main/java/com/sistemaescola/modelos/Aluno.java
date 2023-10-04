package com.sistemaescola.modelos;

import java.util.List;
import javax.persistence.*;


@Entity
@DiscriminatorValue(value = "aluno")
public class Aluno extends Pessoa {

    @OneToMany(mappedBy = "estudante", fetch = FetchType.EAGER)
    private List <Atividades> listaAtividades;

    

    public Aluno(){
    }

    public Aluno(String nome, String rgm, String senha, String dataNascimento){
        super(nome, rgm, senha, dataNascimento);
    }

    public static boolean loginAluno(Aluno aluno, String usuario, String senha){
        if( aluno.getRgm().equals(usuario) && aluno.getSenha().equals(senha)){
         return true; //login realizado com sucesso
        }else{
         return false;//login não efetuado
        }
     }
    
     public List<Atividades> getListaAtividades() {
    return listaAtividades;
}

    public void setListaAtividades(List<Atividades> listaAtividades) {
        this.listaAtividades = listaAtividades;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    

}
