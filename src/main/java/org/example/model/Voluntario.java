package org.example.model;

import java.io.Serializable;


public abstract class Voluntario extends Pessoa implements Serializable {
    private String instituicao;
    private Federacao federacao;

    public Voluntario(String nome, String numeroAluno, String curso, String password, String instituicao) {
        super(nome, numeroAluno, curso, password);
        if (instituicao == null || instituicao.trim().isEmpty()) {
            throw new IllegalArgumentException("Instituição não pode ser nula ou vazia");
        }
        this.instituicao = instituicao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public Federacao getFederacao() {
        return federacao;
    }

    public void setFederacao(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Verifica se a senha fornecida é válida.
     * @param password Senha a verificar.
     * @return true se a senha for válida, false caso contrário.
     */
    public boolean autenticar(String password) {
        return getPassword().equals(password);
    }

    /**
     * Representação textual do voluntário.
     * @return String com informações do voluntário, incluindo instituição.
     */
    @Override
    public String toString() {
        return super.toString() + " - " + instituicao;
    }
}