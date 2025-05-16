package org.example.model;

import java.io.Serializable;

/**
 * Classe abstrata que representa um voluntário no sistema.
 */
public abstract class Voluntario extends Pessoa implements Serializable {
    private String instituicao;
    private Federacao federacao;

    /**
     * Construtor da classe Voluntario.
     * @param nome Nome do voluntário.
     * @param numeroAluno Número de aluno.
     * @param curso Curso do voluntário.
     * @param password Senha do voluntário.
     * @param instituicao Instituição do voluntário.
     * @throws IllegalArgumentException Se algum parâmetro for nulo.
     */
    public Voluntario(String nome, String numeroAluno, String curso, String password, String instituicao) {
        super(nome, numeroAluno, curso, password);
        this.instituicao = instituicao;
    }

    /**
     * Obtém a instituição do voluntário.
     * @return Instituição do voluntário.
     */
    public String getInstituicao() {
        return instituicao; }

    /**
     * Autentica o voluntário com a senha fornecida.
     * @param password Senha a verificar.
     * @return true se a senha estiver correta, false caso contrário.
     */
    public boolean autenticar(String password) {
        return getPassword().equals(password);
    }

    /**
     * Representação textual do voluntário.
     * @return String com nome, número de aluno, curso e instituição.
     */
    @Override
    public String toString() {
        return super.toString() + " - " + instituicao;
    }

    public Federacao getFederacao() {
        return federacao;
    }

    public void setFederacao(Federacao federacao) {
        this.federacao = federacao;
    }
}