package org.example.model;

/**
 * Classe abstrata que representa uma pessoa no sistema.
 */
public abstract class Pessoa {
    private String nome;
    private String numeroAluno;
    private String curso;
    private String password;

    /**
     * Construtor da classe Pessoa.
     * @param nome Nome da pessoa.
     * @param numeroAluno Número de aluno.
     * @param curso Curso da pessoa.
     * @param password Senha da pessoa.
     * @throws IllegalArgumentException Se algum parâmetro for inválido.
     */
    public Pessoa(String nome, String numeroAluno, String curso, String password) {
        if (nome == null || nome.trim().isEmpty() || numeroAluno == null || numeroAluno.trim().isEmpty() ||
                curso == null || curso.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Parâmetros inválidos para Pessoa");
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
    public String getNome() {
        return nome; }

    /**
     * Obtém o número de aluno.
     * @return Número de aluno.
     */
    public String getNumeroAluno() {
        return numeroAluno; }

    /**
     * Obtém o curso da pessoa.
     * @return Curso da pessoa.
     */
    public String getCurso() {
        return curso; }

    /**
     * Obtém a senha da pessoa.
     * @return Senha da pessoa.
     */
    public String getPassword() {
        return password; }

    /**
     * Representação textual da pessoa.
     * @return String com nome e número de aluno.
     */
    @Override
    public String toString() {
        return nome + " (" + numeroAluno + ")";
    }
}