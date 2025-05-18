package org.example.model;

/**
 * Classe que representa um administrador no sistema.
 */
public class Administrador extends Pessoa {
    /**
     * Construtor da classe Administrador.
     * @param nome Nome do administrador.
     * @param numeroAluno Número de aluno do administrador.
     * @param curso Curso do administrador.
     * @param password Senha do administrador.
     */
    public Administrador(String nome, String numeroAluno, String curso, String password) {
        super(nome, numeroAluno, curso, password);
    }

    /**
     * Verifica se a senha fornecida é válida.
     * @param password Senha a verificar.
     * @return true se a senha for válida, false caso contrário.
     */
    public boolean autenticar(String password) {
        return getPassword().equals(password);
    }
}