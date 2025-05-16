package org.example.model;

import java.io.Serializable;

/**
 * Classe que representa um voluntário responsável por gerenciar estoque.
 */
public class VoluntarioStock extends Voluntario implements Serializable {

    /**
     * Construtor da classe VoluntarioStock.
     * @param nome Nome do voluntário.
     * @param numeroAluno Número de aluno.
     * @param curso Curso do voluntário.
     * @param senha Senha do voluntário.
     * @param instituicao Instituição do voluntário.
     * @throws IllegalArgumentException Se algum parâmetro for nulo.
     */
    public VoluntarioStock(String nome, String numeroAluno, String curso, String senha, String instituicao) {
        super(nome, numeroAluno, curso, senha, instituicao);
    }

    /**
     * Adiciona estoque a uma barraca.
     * @param barraca Barraca a receber o estoque.
     * @param produto Produto a adicionar.
     * @param quantidade Quantidade a adicionar.
     * @return true se o estoque foi adicionado, false caso contrário.
     */
    public boolean adicionarEstoque(Barraca barraca, Produto produto, int quantidade) {
        return barraca.adicionarEstoque(produto, quantidade);
    }
}