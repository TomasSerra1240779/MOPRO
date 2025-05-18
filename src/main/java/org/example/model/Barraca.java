package org.example.model;

import org.example.utils.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma barraca.
 */
public class Barraca implements Classificacao {
    private String nome;
    private String instituicao;
    private List<ItemEstoque> estoque;
    private List<Venda> vendas;
    private List<Escala> escalas;

    /**
     * Classe interna para armazenar um produto e sua quantidade no estoque.
     */
    private static class ItemEstoque {
        private Produto produto;
        private int quantidade;

        public ItemEstoque(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
        }

        public Produto getProduto() { return produto; }
        public int getQuantidade() { return quantidade; }
        public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    }

    /**
     * Construtor da classe Barraca.
     * @param nome Nome da barraca.
     * @param instituicao Instituição associada à barraca.
     * @throws IllegalArgumentException Se nome ou instituição forem nulos.
     */
    public Barraca(String nome, String instituicao) {
        if (nome == null || instituicao == null) {
            throw new IllegalArgumentException("Nome e instituição não podem ser nulos");
        }
        this.nome = nome;
        this.instituicao = instituicao;
        this.estoque = new ArrayList<>();
        this.vendas = new ArrayList<>();
        this.escalas = new ArrayList<>();
    }

    /**
     * Adiciona estoque de um produto.
     * @param produto Produto a adicionar.
     * @param quantidade Quantidade a adicionar.
     * @return true se o estoque foi adicionado, false caso contrário.
     */
    public boolean adicionarEstoque(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            return false;
        }
        for (ItemEstoque item : estoque) {
            if (item.getProduto().equals(produto)) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return true;
            }
        }
        estoque.add(new ItemEstoque(produto, quantidade));
        return true;
    }

    /**
     * Remove estoque de um produto.
     * @param produto Produto a remover.
     * @param quantidade Quantidade a remover.
     * @return true se o estoque foi removido, false caso contrário.
     */
    public boolean removerEstoque(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            return false;
        }
        for (ItemEstoque item : estoque) {
            if (item.getProduto().equals(produto)) {
                if (item.getQuantidade() < quantidade) {
                    return false;
                }
                int novaQuantidade = item.getQuantidade() - quantidade;
                if (novaQuantidade == 0) {
                    estoque.remove(item);
                } else {
                    item.setQuantidade(novaQuantidade);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Registra uma venda na barraca.
     * @param venda Venda a registrar.
     * @return true se a venda foi registrada, false caso contrário.
     */
    public boolean registrarVenda(Venda venda) {
        if (venda == null || !removerEstoque(venda.getProduto(), venda.getQuantidade())) {
            return false;
        }
        return vendas.add(venda);
    }

    /**
     * Calcula o total de vendas em uma data.
     * @param data Data das vendas.
     * @return Total das vendas.
     */
    public double calcularVendasDiarias(Data data) {
        double total = 0.0;
        for (Venda venda : vendas) {
            if (venda.getData().equals(data)) {
                total += venda.getValorTotal();
            }
        }
        return total;
    }

    /**
     * Obtém o estoque total da barraca.
     * @return Quantidade total de itens em estoque.
     */
    public int getEstoqueTotal() {
        int total = 0;
        for (ItemEstoque item : estoque) {
            total += item.getQuantidade();
        }
        return total;
    }

    /**
     * Calcula o estoque final diário, considerando vendas realizadas na data.
     * @param data A data para o cálculo.
     * @return A soma das quantidades de todos os produtos no estoque após deduzir vendas do dia.
     */
    private int calcularEstoqueFinalDiario(Data data) {
        int total = getEstoqueTotal();
        for (Venda venda : vendas) {
            if (venda.getData().equals(data)) {
                total -= venda.getQuantidade();
            }
        }
        return Math.max(total, 0);
    }

    /**
     * Classifica a barraca com base no estoque final diário.
     * @param data Data para a classificação.
     * @return "Ouro" (estoque <50), "Prata" (estoque 50-100), ou "Bronze" (estoque >100).
     */
    @Override
    public String classificar(Data data) {
        int estoqueFinal = calcularEstoqueFinalDiario(data);
        if (estoqueFinal < 50) return "Ouro";
        if (estoqueFinal <= 100) return "Prata";
        return "Bronze";
    }

    /**
     * Obtém os produtos disponíveis no estoque.
     * @return Lista de produtos no estoque.
     */
    public List<Produto> getProdutos() {
        List<Produto> produtos = new ArrayList<>();
        for (ItemEstoque item : estoque) {
            produtos.add(item.getProduto());
        }
        return produtos;
    }

    /**
     * Obtém a quantidade em estoque de um produto.
     * @param produto Produto a verificar.
     * @return Quantidade em estoque, ou 0 se não estiver no estoque.
     */
    public int getQuantidadeStock(Produto produto) {
        for (ItemEstoque item : estoque) {
            if (item.getProduto().equals(produto)) {
                return item.getQuantidade();
            }
        }
        return 0;
    }

    /**
     * Obtém o nome da barraca.
     * @return Nome da barraca.
     */
    public String getNome() { return nome; }

    /**
     * Obtém a instituição da barraca.
     * @return Instituição da barraca.
     */
    public String getInstituicao() { return instituicao; }

    /**
     * Obtém a lista de escalas.
     * @return Lista de escalas.
     */
    public List<Escala> getEscalas() { return escalas; }

    /**
     * Obtém a lista de vendas.
     * @return Lista de vendas.
     */
    public List<Venda> getVendas() { return vendas; }

    /**
     * Representação textual da barraca.
     * @return String com nome, instituição e estoque.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Barraca: " + nome + " (" + instituicao + ")\nEstoque:\n");
        if (estoque.isEmpty()) {
            result.append(" (VAZIO)\n");
        } else {
            for (ItemEstoque item : estoque) {
                result.append("\t- ").append(item.getProduto()).append(": ").append(item.getQuantidade()).append(" unidades\n");
            }
        }
        return result.toString();
    }
}