package org.example.model;

import java.io.Serializable;

/**
 * Classe abstrata que representa uma pessoa no sistema.
 */
public abstract class Pessoa implements Serializable {
    private String nome;
    private String numeroAluno;
    private String curso;
    private String password;

    /**
     * Construtor da classe Pessoa.
     * @param nome Nome da pessoa.
     * @param numeroAluno Número de aluno.
     * @param curso Curso da pessoa.
     * @param password Senha para autenticação.
     * @throws IllegalArgumentException Se algum parâmetro for nulo.
     */
    public Pessoa(String nome, String numeroAluno, String curso, String password) {
        if (nome == null || numeroAluno == null || curso == null || password == null) {
            throw new IllegalArgumentException("Parâmetros não podem ser nulos");
        }
        this.nome = nome;
        this.numeroAluno = numeroAluno;
        this.curso = curso;
        this.password = password;
    }

    /**
     * Obtém o nome da pessoa.
     * @return Nome da pessoa.
     */
    public String getNome() { return nome; }

    /**
     * Obtém o número de aluno.
     * @return Número de aluno.
     */
    public String getNumeroAluno() { return numeroAluno; }

    /**
     * Obtém o curso da pessoa.
     * @return Curso da pessoa.
     */
    public String getCurso() { return curso; }

    /**
     * Obtém a senha da pessoa.
     * @return Senha da pessoa.
     */
    public String getPassword() { return password; }

    /**
     * Altera a senha da pessoa.
     * @param password Nova senha.
     * @throws IllegalArgumentException Se a senha for nula.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Representação textual da pessoa.
     * @return String com nome, número de aluno e curso.
     */
    @Override
    public String toString() {

        return nome + " (" + numeroAluno + ") - " + curso;
    }
}