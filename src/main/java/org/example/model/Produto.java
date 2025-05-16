package org.example.model;

import java.io.Serializable;

/**
 * Classe que representa um produto à venda nas barracas.
 */
public class Produto implements Serializable {
    private String nome;
    private double preco;

    /**
     * Construtor da classe Produto.
     * @param nome Nome do produto.
     * @param preco Preço do produto.
     * @throws IllegalArgumentException Se nome for nulo ou preço for negativo.
     */
    public Produto(String nome, double preco) {
        if (nome == null) throw new IllegalArgumentException("Nome não pode ser nulo");
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo");
        this.nome = nome;
        this.preco = preco;
    }

    /**
     * Obtém o nome do produto.
     * @return Nome do produto.
     */
    public String getNome() { return nome; }

    /**
     * Obtém o preço do produto.
     * @return Preço do produto.
     */
    public double getPreco() { return preco; }

    /**
     * Representação textual do produto.
     * @return String com nome e preço.
     */
    @Override
    public String toString() {
        return nome + " - €" + String.format("%.2f", preco);
    }

    /**
     * Verifica se dois produtos são iguais (baseado no nome, ignorando maiúsculas/minúsculas).
     * @param o Objeto a comparar.
     * @return true se os produtos têm o mesmo nome, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return nome.equalsIgnoreCase(produto.nome);
    }

    /**
     * Calcula o código hash do produto.
     * @return Código hash baseado no nome.
     */
    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }
}