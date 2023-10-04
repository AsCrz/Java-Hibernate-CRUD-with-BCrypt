package com.sistemaescola;

import com.sistemaescola.dao.AlunoDAO;
import com.sistemaescola.dao.AtividadesDAO;
import com.sistemaescola.dao.ProfessorDAO;
import com.sistemaescola.dao.DiretorDAO;
import com.sistemaescola.modelos.Aluno;
import com.sistemaescola.modelos.Atividades;
import com.sistemaescola.modelos.Diretor;
import com.sistemaescola.modelos.Professor;

import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Aplicacao {
    
    static Scanner leitor = new Scanner (System.in);
    static ArrayList <Professor> listaProfessors = new ArrayList<>();
    static ArrayList <Aluno> listaAluno = new ArrayList<>();
    static ArrayList <Atividades> listaAtividade = new ArrayList<>();

    static AlunoDAO alunoDAO = new AlunoDAO();
    static ProfessorDAO professorDAO = new ProfessorDAO();
    static DiretorDAO diretorDAO = new DiretorDAO();
    static AtividadesDAO atividadesDAO = new AtividadesDAO();

    public static void main(String args[]) {
        Diretor diretor = new Diretor("Jorge", "12345", "teste1", "15/09/1990", 200);
                String senhaCriptografada = BCrypt.hashpw(diretor.getSenha(), BCrypt.gensalt());
                diretor.setSenha(senhaCriptografada); 
        diretorDAO.atualizarDiretor(diretor);

    listaAluno = (ArrayList<Aluno>) alunoDAO.buscarTodosAlunos();
    listaProfessors = (ArrayList<Professor>) professorDAO.buscarTodosProfessores();
    listaAtividade = (ArrayList<Atividades>) atividadesDAO.buscarTodasAtividades();

        boolean continuar = true;
        while (continuar) {

    atividadesDAO.buscarTodasAtividades();
            // Realização do login pelo usuário
            System.out.println("Para fechar o programa digite '0'");
            System.out.println("Rgm: ");
            String user = leitor.nextLine();

                if(user.equals("0")){
                System.out.println("Fechando...");
                continuar = false;
                break;
            }

            System.out.println("Senha: ");
            String senha = leitor.nextLine();

            // Autenticação do login
            boolean loginRealizado = false;

            if (BCrypt.checkpw(senha, diretor.getSenha())){
                loginRealizado = true;
            }
            if (loginRealizado) {//verifica se é "Diretor"
                menuDiretor(diretor);
            } else {
                Professor professor = encontrarProfessor(listaProfessors, user);
                if (professor != null) { // verifica se é "Professor"
                    if (BCrypt.checkpw(senha, professor.getSenha())){
                        loginRealizado = true;
                    }
                    if (loginRealizado) {
                        menuProfessor(professor);
                    }
                }
            }
            if (!loginRealizado) { // verifica se é "Aluno"
                Aluno aluno = encontrarAluno(listaAluno, user);
                if (aluno != null) {
                    if (BCrypt.checkpw(senha, aluno.getSenha())){
                        loginRealizado = true;
                    }
                    if (loginRealizado) {
                        menuAluno(aluno);
                    }
                }
            }

            if (!loginRealizado) { // caso todas as autenticações falharem
                System.out.println("Login inválido. Tentar novamente? [1]- Sim [0]- Não");
                int tentarNovamente = leitor.nextInt();
                leitor.nextLine(); // Limpar o buffer
                if (tentarNovamente == 0) {
                    continuar = false;
                }
            }
        }
    }

// métodos para achar objetos
public static Professor encontrarProfessor(ArrayList<Professor> listaProfessors, String usuario) {
        for (Professor professor : listaProfessors) {
            if (professor.getRgm().equals(usuario)) {
                return professor;
            }
        }
        return null;
    }

public static Aluno encontrarAluno(ArrayList<Aluno> listaAlunos, String usuario) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getRgm().equals(usuario)) {
                return aluno;
            }
        }
        return null;
    }

public static Atividades encontrarAtividade(List <Atividades> listaAtividade, String nomeAtv) {
    for (Atividades atividade : listaAtividade) {
        if (atividade.getNome().equals(nomeAtv)) {
            return atividade;
        }
    }
    return null;
}

// métodos para "ALUNO"
public static void cadastrarAluno(){//feito
String continuar;

do{

System.out.println("Digite o rgm do aluno: ");
    String rgm = leitor.nextLine();

Professor profe = encontrarProfessor(listaProfessors, rgm);
Aluno aluno = encontrarAluno(listaAluno, rgm);

if(profe == null && aluno == null){
    System.out.println("Digite o nome do aluno: ");
    String nome = leitor.nextLine();

System.out.println("Digite a data de nascimento do aluno: ");
    String dataN = leitor.nextLine();
System.out.println("Digite a senha do aluno: ");
    String senha = leitor.nextLine();
    String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

Aluno a = new Aluno(nome, rgm, senhaCriptografada, dataN);
alunoDAO.salvarAluno(a);
listaAluno.add(a);

}else{
    System.out.println("RGM já registrado");
}
System.out.println("Deseja cadastar outro aluno? (s/n)");
    continuar = leitor.nextLine();

}while(continuar.equals("s"));
}

public static void listarAlunos(){// feito

listaAluno = (ArrayList<Aluno>) alunoDAO.buscarTodosAlunos();

for(Aluno item : listaAluno){
    System.out.println(item);
}
}

public static void alterarAluno(){//feito
String continuar;

do{
System.out.println("Digite o rgm do aluno que sera alterado: ");
    String alterar = leitor.nextLine();

Aluno aluno = encontrarAluno(listaAluno, alterar);

if(aluno != null){
    System.out.println("O que você deseja alterar? (nome, rgm, data de nascimento, senha)");
        String op = leitor.nextLine();

            switch (op) {

                case "nome":
                    System.out.println("Digite o novo nome do aluno: ");
                    String novoNome = leitor.nextLine();
                    aluno.setNome(novoNome);
                    System.out.println("Nome alterado com sucesso.");
                        break;

                case "rgm":
                    System.out.println("Digite o novo RGM do aluno: ");
                    String novoRgm = leitor.nextLine();
                    aluno.setRgm(novoRgm);
                    System.out.println("RGM alterado com sucesso.");
                        break;
                
                case "data de nascimento":
                    System.out.println("Digite a nova data de nascimento do aluno: ");
                    String novaDataNascimento = leitor.nextLine();
                    aluno.setDataNascimento(novaDataNascimento);
                    System.out.println("Data de nascimento alterada com sucesso.");
                        break;
                case "senha":
                    System.out.println("Digite a nova senha: ");
                    String novaSenha = leitor.nextLine();
                    String senhaCriptografada = BCrypt.hashpw(novaSenha, BCrypt.gensalt());
                    aluno.setSenha(senhaCriptografada);
                    System.out.println("Senha alterada com sucesso!");
                default:
                    System.out.println("Opção inválida.");
                }
                    alunoDAO.atualizarAluno(aluno);
}else{
    System.out.println("Aluno não encontrado");
        return;
}

System.out.println("Deseja alterar outro aluno? (s/n)");
    continuar = leitor.nextLine();

}while(continuar.equals("s"));
}

public static void excluirAluno(){//feito
String continuar;    
    
do{    
    System.out.println("Digite o rgm do aluno que será excluído: ");
        String excluir = leitor.nextLine();

    Aluno aluno = encontrarAluno(listaAluno, excluir);

    if (aluno != null) {
        listaAluno.remove(aluno);
        alunoDAO.excluirAluno(aluno);
        System.out.println("Aluno excluído com sucesso.");
    } else {
        System.out.println("Aluno não encontrado");
    }

    System.out.println("Deseja excluir outro aluno? (s/n)");
        continuar = leitor.nextLine();

}while(continuar.equals("s"));
}

// métodos para "PROFESSOR"
public static void cadastrarProfessor(){//feito
String continuar;

do{
System.out.println("Digite o rgm do professor: ");
    String rgm = leitor.nextLine();

Professor profe = encontrarProfessor(listaProfessors, rgm);
Aluno aluno = encontrarAluno(listaAluno, rgm);

if(profe == null && aluno == null){
    System.out.println("Digite o nome do professor: ");
    String nome = leitor.nextLine();

System.out.println("Digite a matéria do professor: ");
    String materia = leitor.nextLine();
System.out.println("Digite a data de nascimento do professor: ");
    String dataN = leitor.nextLine();
System.out.println("Digite a quantidade de horas aulas no mês desse professor: ");
    int horasAula = leitor.nextInt();
        leitor.nextLine();
System.out.println("Digite a senha do professor: ");
    String senha = leitor.nextLine();
    String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

Professor p = new Professor(nome, rgm, senhaCriptografada, dataN, materia, horasAula);
professorDAO.salvarProfessor(p);
listaProfessors.add(p);
}else{
    System.out.println("RGM já cadastrado");
}
System.out.println("Deseja cadastar outro professor? (s/n)");
    continuar = leitor.nextLine();


}while(continuar.equals("s"));
}

public static void listarProfessores(){ //feito

listaProfessors = (ArrayList <Professor>) professorDAO.buscarTodosProfessores();

for(Professor item : listaProfessors){
    System.out.println(item);
}
}

public static void alterarProfessor(){//feito
String continuar;

do{
    System.out.println("Digite o rgm do professor a ser alterado: ");
        String alterar = leitor.nextLine();
    
    Professor profe = encontrarProfessor(listaProfessors, alterar);
    
    if(profe != null){
        System.out.println("O que você deseja alterar? (nome, rgm, data de nascimento, materia, horas aula, senha)");
            String op = leitor.nextLine();

        switch (op) {
            case "nome":
                System.out.println("Digite o novo nome do professor: ");
                    String novoNome = leitor.nextLine();
                profe.setNome(novoNome);
                System.out.println("Nome alterado com sucesso.");
                    break;
                                
            case "rgm":
                System.out.println("Digite o novo RGM do professor: ");
                    String novoRgm = leitor.nextLine();
                profe.setRgm(novoRgm);
                System.out.println("RGM alterado com sucesso.");
                    break;
                                
            case "data de nascimento":                
                System.out.println("Digite a nova data de nascimento do professor: ");
                    String novaDataNascimento = leitor.nextLine();
                profe.setDataNascimento(novaDataNascimento);
                System.out.println("Data de nascimento alterada com sucesso.");
                    break;
            case "materia":
                System.out.println("Digite a nova materia: ");
                    String novaMateria = leitor.nextLine();
                profe.setMateria(novaMateria);
                System.out.println("Materia alterada com sucesso.");
                    break;

            case "horas aula":
                System.out.println("Digite a nova quantidade de horas aula: ");
                    int novaHorasA = leitor.nextInt();
                            leitor.nextLine();
                profe.setHorasAulaMES(novaHorasA);
                System.out.println("Horas aula alteradas com sucesso.");
            case "senha":
                System.out.println("Digite a nova senha: ");
                String novaSenha = leitor.nextLine();
                String senhaCriptografada = BCrypt.hashpw(novaSenha, BCrypt.gensalt());
                profe.setSenha(senhaCriptografada);
                System.out.println("Senha alterada com sucesso!");

            default:
                System.out.println("Opção inválida.");
            }
            professorDAO.atualizarProfessor(profe);
}else{
    System.out.println("Professor não encontrado");
        return;
}

System.out.println("Deseja alterar outro professor? (s/n)");
    continuar = leitor.nextLine();

}while(continuar.equals("s"));

}

public static void excluirProfessor(){//feito
String continuar;

do{
    System.out.println("Digite o rgm do professor que será excluído: ");
    String excluir = leitor.nextLine();

    Professor professor = encontrarProfessor(listaProfessors, excluir);

    if (professor != null) {
        listaProfessors.remove(professor);
        professorDAO.excluirProfessor(professor);
        
        System.out.println("Professor excluído com sucesso.");
    } else {
        System.out.println("Professor não encontrado");
    }

System.out.println("Deseja excluir outro professor? (s/n)");
    continuar = leitor.nextLine();

}while(continuar.equals("s"));
}

// métodos para "ATIVIDADE"
public static void cadastrarAtividade(){ //feito
String continuar;

do{
    System.out.println("Digite o nome da atividade: ");
        String nome = leitor.nextLine();
    System.out.println("Digite a matéria: ");
        String materia = leitor.nextLine();
    System.out.println("Digite a data de entrega: ");
        String data = leitor.nextLine();
    System.out.println("Digite o valor da atividade: ");
        double nota = leitor.nextDouble();
    

    Atividades atv = new Atividades(null, nome, materia, data, nota);
    atividadesDAO.salvarAtividade(atv);
    listaAtividade.add(atv);

    System.out.println("Deseja cadastrar outra atividade? (s/n)");
        continuar = leitor.nextLine();

}while(continuar.equals("s"));

}

public static void atribuirAtividade() {
    String continuar;
    do {
        System.out.println("Digite o rgm do aluno que a atividade sera atribuida: ");
        String rgm = leitor.nextLine();

        Aluno aluno = encontrarAluno(listaAluno, rgm);

        if (aluno != null) {
            System.out.println("Digite o nome da atividade: ");
            String nomeAtv = leitor.nextLine();

            Atividades atividade = encontrarAtividade(listaAtividade, nomeAtv);

            if (atividade != null) {
                List<Atividades> atividadesDoAluno = aluno.getListaAtividades(); //recupera a lista de atividades atribuídas ao aluno.
                atividadesDoAluno.add(atividade);
                aluno.setListaAtividades(atividadesDoAluno);//atualiza a lista de atividades do aluno
                atividade.setEstudante(aluno);
                atividadesDAO.atualizarAtividade(atividade);
                System.out.println("Atividade atribuida com sucesso!");
            } else {
                System.out.println("Atividade não encontrada!");
            }
        } else {
            System.out.println("Aluno não encontrado!");
        }

        System.out.println("Deseja atribuir outra atividade? (s/n)");
        continuar = leitor.nextLine();
    } while (continuar.equals("s"));
}


public static void entregarAtividades(){
 System.out.println("Qual atividade gostaria de entrgar? ");
    String nomeAtv = leitor.nextLine();

Atividades atividade = encontrarAtividade(listaAtividade, nomeAtv);

    if(atividade != null){
        System.out.println("Digite a data que está efetuando a entrega: ");
            String data = leitor.nextLine();
            listaAtividade.remove(atividade);
            atividadesDAO.excluirAtividade(atividade);
            System.out.println("Atividade devolvida no dia "+ data);

    }else{
        System.out.println("Atividade não encontrada.");
    }

}

// métodos dos menus
public static void menuDiretor(Diretor diretor) {//feito
    boolean continuar = true;
    
    System.out.println("Bem vindo Diretor " + diretor.getNome());
    while (continuar) {
  
System.out.println("""
        [1]- Cadastrar Aluno                 [2]- Alterar aluno
        [3]- Excluir aluno                   [4]- Cadastrar professor
        [5]- Alterar professor               [6]- Excluir professor
        [7]- Listar alunos                   [8]- Listar professores
        [9]- Calcular salario de professor   [10]- Calcular seu salario
        [11]- Adicionar horas extras         [12]- Ver salario
        [0]- Sair
        """);
        int opcao = leitor.nextInt();
        leitor.nextLine(); // Limpar o buffer

        switch (opcao) {
            case 1:
                cadastrarAluno();
                break;
            case 2:
                alterarAluno();
                break;
            case 3:
                excluirAluno();
                break;
            case 4:
                cadastrarProfessor();
                break;
            case 5:
                alterarAluno();
                break;
            case 6:
                excluirAluno();
                break;
            case 7:
                listarAlunos();
                break;
            case 8: 
                listarProfessores();
                break;
            case 9:
                System.out.println("Digite o RGM do professor: ");
                    String rgm = leitor.nextLine();

                Professor professor = encontrarProfessor(listaProfessors, rgm);
                if(professor != null){
                    professor.calculaSalario();
                    professorDAO.atualizarProfessor(professor);
                }else{
                    System.out.println("Professor não encontrado");
                }
                break;
            case 10:
                diretor.calculaSalario();
                diretorDAO.atualizarDiretor(diretor);
                break;
            case 11: 
                System.out.println("Adicionar horas extras: ");
                    int horaExtra = leitor.nextInt();
                        leitor.nextLine();// limpa o buffer
                diretor.setHorasExtras(horaExtra);
                diretor.salarioHorasExtras();
                diretorDAO.atualizarDiretor(diretor);
                break;
            case 12:
                System.out.println("Seu salario é "+ diretor.getSalario());
            case 0:
                System.out.println("Saindo...");
                continuar = false;
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}

public static void menuAluno(Aluno aluno) { //feito
    boolean continuar = true;
    
    System.out.println("Bem vindo Aluno " + aluno.getNome());
    while (continuar) {

System.out.println("""
        [1]- Ver suas atividades  [2]- Entregar atividades
        [0]- Sair
        """);
        int opcao = leitor.nextInt();
        leitor.nextLine(); // Limpar o buffer

        switch (opcao) {
            
            case 1:
                listaAtividade = (ArrayList<Atividades>) atividadesDAO.buscarTodasAtividades();
    
                for (Atividades atividade : listaAtividade) { // verifica se aluno possui atividades 
                if (atividade.getEstudante().equals(aluno)) {
                System.out.println(atividade);
        }
    }
                break;
            case 2:
                entregarAtividades();
                break;
            case 0:
                System.out.println("Saindo...");
                continuar = false;
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}

public static void menuProfessor(Professor professor) { //feito
    boolean continuar = true;
    
    System.out.println("Bem vindo Professor " + professor.getNome());
    while (continuar) {

System.out.println("""
        [1]- Cadastrar atividade    [2]- Listar alunos
        [3]- Atribuir atividade     [4]- Listar atividades
        [5]- Ver seu salario atual  [0]- Sair
        """);
        int opcao = leitor.nextInt();
        leitor.nextLine(); // Limpar o buffer

        switch (opcao) {
            
            case 1:
                cadastrarAtividade();
                break;
            case 2:
                listarAlunos();
                break;
            case 3:
                atribuirAtividade();
                break;
            case 4:
            if(listaAtividade != null){
                listaAtividade = (ArrayList<Atividades>) atividadesDAO.buscarTodasAtividades();

                for (Atividades item : listaAtividade) {
                System.out.println(item);
                    }
            }else{
                System.out.println("Sem atividades cadastradas");
                return;
            }
                break;
            case 5:
                System.out.println("Seu salario é de " + professor.getSalario());
            case 0:
                System.out.println("Saindo...");
                continuar = false;
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}
}
