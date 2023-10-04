package com.sistemaescola.modelos;

import com.sistemaescola.interfaces.CalculaSalario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.*;


@Entity
@DiscriminatorValue(value = "professor")
public class Professor extends Pessoa implements CalculaSalario{
    
    private int horasAulaMES;
    private double salario;
    private String materia;

public Professor(){
}

    public Professor(String nome, String rgm, String senha, String dataNascimento, String materia, int horasAulaMES){
    super(nome, rgm, senha, dataNascimento);
    this.horasAulaMES = horasAulaMES;
    this.materia = materia;
    }

public void calculaSalario(){
    salario = horasAulaMES * 50.00;
}

public static boolean loginProfessor(Professor professor, String usuario, String senha){
    if( professor.getRgm().equals(usuario) && professor.getSenha().equals(senha)){
     return true; //login realizado com sucesso
    }else{
     return false;//login não efetuado
    }
 }
 
 

public double getSalario() {
    return salario;
}

public void setMateria(String materia){
    this.materia = materia;
}

public void setHorasAulaMES(int horasAulaMES) {
    this.horasAulaMES = horasAulaMES;
}

@Override
    public String toString() {
        return super.toString() + ", materia= " + materia + ", salario= " +salario + ", horas aulas no mes= " +horasAulaMES + "]";
    }

}
