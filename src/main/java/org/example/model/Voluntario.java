package org.example.model;

import org.example.utils.Data;

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
     * @throws IllegalArgumentException Se algum parâmetro for inválido.
     */
    public Voluntario(String nome, String numeroAluno, String curso, String password, String instituicao) {
        super(nome, numeroAluno, curso, password);
        if (instituicao == null || instituicao.trim().isEmpty()) {
            throw new IllegalArgumentException("Instituição inválida");
        }
        this.instituicao = instituicao;
    }

    /**
     * Define a federação associada ao voluntário.
     * @param federacao A federação.
     */
    public void setFederacao(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Obtém a instituição do voluntário.
     * @return Instituição do voluntário.
     */
    public String getInstituicao() { return instituicao; }

    /**
     * Obtém a federação associada.
     * @return A federação.
     */
    public Federacao getFederacao() { return federacao; }

    /**
     * Verifica se a senha fornecida é válida.
     * @param password Senha a verificar.
     * @return true se a senha for válida, false caso contrário.
     */
    public boolean autenticar(String password) {
        return getPassword().equals(password);
    }

    /**
     * Obtém a barraca atual do voluntário com base na escala do dia atual.
     * @return A barraca atual, ou null se não estiver escalado.
     */
    public Barraca getBarracaAtual() {
        if (federacao == null) return null;
        Data hoje = new Data();
        for (Barraca b : federacao.getBarracas()) {
            for (Escala e : b.getEscalas()) {
                if (e.getData().equals(hoje) && e.getVoluntarios().contains(this)) {
                    return b;
                }
            }
        }
        return null;
    }
}