package org.example.model;

import org.example.utils.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa uma barraca no sistema.
 */
public class Barraca implements Classificacao, Serializable {
    private String nome;
    private String instituicao;
    private Map<Produto, Integer> estoque;
    private List<Venda> vendas;
    private List<Escala> escalas;

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
        this.estoque = new HashMap<>();
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
        if (produto == null || quantidade <= 0) return false;
        estoque.merge(produto, quantidade, Integer::sum);
        return true;
    }

    /**
     * Remove estoque de um produto.
     * @param produto Produto a remover.
     * @param quantidade Quantidade a remover.
     * @return true se o estoque foi removido, false caso contrário.
     */
    public boolean removerEstoque(Produto produto, int quantidade) {
        if (produto == null || !estoque.containsKey(produto) || estoque.get(produto) < quantidade || quantidade <= 0) {
            return false;
        }
        int novoEstoque = estoque.get(produto) - quantidade;
        if (novoEstoque == 0) {
            estoque.remove(produto);
        } else {
            estoque.put(produto, novoEstoque);
        }
        return true;
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
        return vendas.stream()
                .filter(v -> v.getData().equals(data))
                .mapToDouble(Venda::getValorTotal)
                .sum();
    }

    /**
     * Obtém o estoque total da barraca.
     * @return Quantidade total de itens em estoque.
     */
    public int getEstoqueTotal() {
        return estoque.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Classifica a barraca com base no estoque.
     * @param data Data para a classificação.
     * @return Categoria (Ouro, Prata, Bronze).
     */
    @Override
    public String classificar(Data data) {
        int totalEstoque = getEstoqueTotal();
        if (totalEstoque < 50) return "Ouro";
        else if (totalEstoque <= 100) return "Prata";
        else return "Bronze";
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
     * Obtém o estoque da barraca.
     * @return Mapa de produtos e quantidades.
     */
    public Map<Produto, Integer> getEstoque() { return estoque; }

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
            for (Map.Entry<Produto, Integer> entry : estoque.entrySet()) {
                result.append("\t- ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" unidades\n");
            }
        }
        return result.toString();
    }
}