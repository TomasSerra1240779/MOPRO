package org.example.model;

/**
 * Classe que representa um voluntário de gestão de estoque.
 */
public class VoluntarioStock extends Voluntario {
    /**
     * Construtor da classe VoluntarioStock.
     * @param nome Nome do voluntário.
     * @param numeroAluno Número de aluno.
     * @param curso Curso do voluntário.
     * @param password Senha do voluntário.
     * @param instituicao Instituição do voluntário.
     */
    public VoluntarioStock(String nome, String numeroAluno, String curso, String password, String instituicao) {
        super(nome, numeroAluno, curso, password, instituicao);
    }

    /**
     * Adiciona estoque a uma barraca.
     * @param barraca Barraca onde adicionar o estoque.
     * @param produto Produto a adicionar.
     * @param quantidade Quantidade a adicionar.
     * @return true se o estoque foi adicionado, false caso contrário.
     */
    public boolean adicionarEstoque(Barraca barraca, Produto produto, int quantidade) {
        if (barraca == null || !barraca.getInstituicao().equals(getInstituicao())) {
            return false;
        }
        return barraca.adicionarEstoque(produto, quantidade);
    }
}