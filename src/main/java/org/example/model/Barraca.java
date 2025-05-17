package org.example.model;

import org.example.utils.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma barraca no sistema.
 */
public class Barraca implements Classificacao, Serializable {
    private String nome;
    private String instituicao;
    private List<ItemEstoque> estoque; // Substitui Map por uma lista de ItemEstoque
    private List<Venda> vendas;
    private List<Escala> escalas;

    /**
     * Classe auxiliar para armazenar um produto e sua quantidade no estoque.
     */
    private static class ItemEstoque {
        private Produto produto;
        private int quantidade;

        public ItemEstoque(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = quantidade;
        }

        public Produto getProduto() {
            return produto;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }
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
        // Procura o produto na lista de estoque
        for (ItemEstoque item : estoque) {
            if (item.getProduto().equals(produto)) {
                // Se o produto já existe, incrementa a quantidade
                item.setQuantidade(item.getQuantidade() + quantidade);
                return true;
            }
        }
        // Se o produto não existe, adiciona um novo item ao estoque
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
        // Procura o produto na lista de estoque
        for (ItemEstoque item : estoque) {
            if (item.getProduto().equals(produto)) {
                // Verifica se há quantidade suficiente
                if (item.getQuantidade() < quantidade) {
                    return false;
                }
                // Atualiza a quantidade
                int novaQuantidade = item.getQuantidade() - quantidade;
                if (novaQuantidade == 0) {
                    // Remove o item se a quantidade for zero
                    estoque.remove(item);
                } else {
                    // Atualiza a quantidade do item
                    item.setQuantidade(novaQuantidade);
                }
                return true;
            }
        }
        // Produto não encontrado no estoque
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
     * Classifica a barraca com base no estoque.
     * @param data Data para a classificação.
     * @return Categoria (Ouro, Prata, Bronze).
     */
    @Override
    public String classificar(Data data) {
        int totalEstoque = getEstoqueTotal();
        if (totalEstoque < 50) {
            return "Ouro";
        } else if (totalEstoque <= 100) {
            return "Prata";
        } else {
            return "Bronze";
        }
    }

    /**
     * Obtém o nome da barraca.
     * @return Nome da barraca.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a instituição da barraca.
     * @return Instituição da barraca.
     */
    public String getInstituicao() {
        return instituicao;
    }

    /**
     * Obtém a lista de escalas.
     * @return Lista de escalas.
     */
    public List<Escala> getEscalas() {
        return escalas;
    }

    /**
     * Obtém o estoque da barraca.
     * @return Lista de itens de estoque.
     */
    public List<ItemEstoque> getEstoque() {
        return estoque;
    }

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