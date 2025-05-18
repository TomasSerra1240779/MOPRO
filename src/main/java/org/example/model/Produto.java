package org.example.model;

import java.io.Serializable;

/**
 * Classe que representa um produto.
 */
public class Produto implements Serializable {
    private String nome;
    private double preco;

    /**
     * Construtor da classe Produto.
     * @param nome Nome do produto.
     * @param preco Preço do produto.
     * @throws IllegalArgumentException Se nome for inválido ou preço for negativo.
     */
    public Produto(String nome, double preco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto inválido");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("Preço do produto não pode ser negativo");
        }
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Obtém o nome do produto.
     * @return Nome do produto.
     */
    public String getNome() {
        return nome; }

    /**
     * Obtém o preço do produto.
     * @return Preço do produto.
     */
    public double getPreco() {
        return preco; }

    /**
     * Representação textual do produto.
     * @return String com nome e preço.
     */
    @Override
    public String toString() {
        return nome + " (€" + String.format("%.2f", preco) + ")";
    }

    /**
     * Compara produtos com base no nome.
     * @param obj Objeto a comparar.
     * @return true se os produtos têm o mesmo nome, false caso contrário.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Produto)) return false;
        Produto other = (Produto) obj;
        return nome.equalsIgnoreCase(other.nome);
    }
}