package org.example.model;

import org.example.utils.Data;

/**
 * Classe que representa uma venda.
 */
public class Venda {
    private Data data;
    private Produto produto;
    private int quantidade;
    private VoluntarioVendas voluntario;
    private Barraca barraca;

    /**
     * Construtor da classe Venda.
     * @param data Data da venda.
     * @param produto Produto vendido.
     * @param quantidade Quantidade vendida.
     * @param voluntario Voluntário que realizou a venda.
     * @param barraca Barraca onde a venda foi realizada.
     * @throws IllegalArgumentException Se algum parâmetro for inválido.
     */
    public Venda(Data data, Produto produto, int quantidade, VoluntarioVendas voluntario, Barraca barraca) {
        if (data == null || produto == null || quantidade <= 0 || voluntario == null || barraca == null) {
            throw new IllegalArgumentException("Parâmetros inválidos para Venda");
        }
        this.data = data;
        this.produto = produto;
        this.quantidade = quantidade;
        this.voluntario = voluntario;
        this.barraca = barraca;
    }

    /**
     * Obtém a data da venda.
     * @return Data da venda.
     */
    public Data getData() { return data; }

    /**
     * Obtém o produto vendido.
     * @return Produto vendido.
     */
    public Produto getProduto() { return produto; }

    /**
     * Obtém a quantidade vendida.
     * @return Quantidade vendida.
     */
    public int getQuantidade() { return quantidade; }

    /**
     * Calcula o valor total da venda.
     * @return Valor total (preço do produto × quantidade).
     */
    public double getValorTotal() {
        return produto.getPreco() * quantidade;
    }

    /**
     * Representação textual da venda.
     * @return String com detalhes da venda.
     */
    @Override
    public String toString() {
        return "Venda em " + data.toAnoMesDiaString() + ": " + produto.getNome() + " x" + quantidade +
                " = €" + String.format("%.2f", getValorTotal()) + " por " + voluntario.getNome();
    }
}