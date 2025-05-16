package org.example.model;

import org.example.utils.Data;
import java.io.Serializable;

/**
 * Classe que representa uma venda realizada.
 */
public class Venda implements Serializable {
    private Data data;
    private Produto produto;
    private int quantidade;
    private double valorTotal;
    private VoluntarioVendas voluntario;
    private Barraca barraca;

    /**
     * Construtor da classe Venda.
     * @param data Data da venda.
     * @param produto Produto vendido.
     * @param quantidade Quantidade vendida.
     * @param voluntario Voluntário que realizou a venda.
     * @param barraca Barraca onde a venda ocorreu.
     * @throws IllegalArgumentException Se algum parâmetro for inválido.
     */
    public Venda(Data data, Produto produto, int quantidade, VoluntarioVendas voluntario, Barraca barraca) {
        if (data == null || produto == null || voluntario == null || barraca == null) {
            throw new IllegalArgumentException("Parâmetros não podem ser nulos");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        this.data = data;
        this.produto = produto;
        this.quantidade = quantidade;
        this.voluntario = voluntario;
        this.barraca = barraca;
        this.valorTotal = produto.getPreco() * quantidade;
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
     * Obtém o valor total da venda.
     * @return Valor total da venda.
     */
    public double getValorTotal() { return valorTotal; }

    /**
     * Representação textual da venda.
     * @return String com detalhes da venda.
     */
    @Override
    public String toString() {
        return "Venda: " + produto.getNome() + " x" + quantidade + " (€" + String.format("%.2f" + valorTotal) + ") em " + data.toAnoMesDiaString();
    }
}