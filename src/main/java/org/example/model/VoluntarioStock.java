package org.example.model;

import java.io.Serializable;

/**
 * Classe para voluntários de estoque.
 */
public class VoluntarioStock extends Voluntario implements Serializable {

    /**
     * Construtor.
     */
    public VoluntarioStock(String nome, String numeroAluno, String curso, String senha, String instituicao) {
        super(nome, numeroAluno, curso, senha, instituicao);
    }

    /**
     * Adiciona estoque a uma barraca.
     */
    public boolean adicionarEstoque(Barraca barraca, Produto produto, int quantidade) {
        return barraca.adicionarEstoque(produto, quantidade);
    }
}