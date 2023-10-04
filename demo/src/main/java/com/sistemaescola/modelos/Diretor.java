package com.sistemaescola.modelos;

import com.sistemaescola.interfaces.CalculaSalario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "diretor")
public class Diretor extends Pessoa implements CalculaSalario{
   private int horasMES, horasExtras; // pagamento da hora extra será o valor da hora normal + 50%
   private double salario;

   public Diretor(){
   }

    public Diretor(String nome, String rgm, String senha, String dataNascimento, int horasTrabMES){
        super(nome, rgm, senha, dataNascimento);
        this.horasMES = horasTrabMES;
}

@Override
public void calculaSalario(){
    salario = horasMES * 65.00;
}

public void salarioHorasExtras(){
    double horasTotais = horasExtras + horasMES; 
    salario = horasTotais * (65.00 + (65.00 * 0.5));
}



public double getSalario() {
    return salario;
}



public void setHorasExtras(int horasExtras) {
    this.horasExtras = horasExtras;
}

public static boolean loginDiretor(Diretor diretor, String usuario, String senha){
   if( diretor.getRgm().equals(usuario) && diretor.getSenha().equals(senha)){
    return true; //login realizado com sucesso
   }else{
    return false;//login não efetuado
   }
}

}