package org.example.model;

import org.example.utils.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.text.DecimalFormat;

/**
 * Classe que representa a federação que gerencia barracas, produtos e voluntários.
 */
public class Federacao implements Serializable {
    private String nome;
    private Federacao federacao;
    private final List<Produto> produtos;
    private final List<Barraca> barracas;
    private final List<Voluntario> voluntarios;

    /**
     * Construtor da classe Federacao.
     * @param nome Nome da federação.
     * @throws IllegalArgumentException Se o nome for nulo.
     */
    public Federacao(String nome) {
        if (nome == null) throw new IllegalArgumentException("Nome não pode ser nulo");
        this.nome = nome;
        this.produtos = new ArrayList<>();
        this.barracas = new ArrayList<>();
        this.voluntarios = new ArrayList<>();
    }


    /**
     * Adiciona um produto à federação.
     * @param produto Produto a adicionar.
     * @return true se o produto foi adicionado, false caso contrário.
     */
    public boolean adicionarProduto(Produto produto) {
        if (produto == null || produtos.stream().anyMatch(p -> p.getNome().equalsIgnoreCase(produto.getNome()))) {
            return false;
        }
        return produtos.add(produto);
    }

    /**
     * Adiciona uma barraca à federação.
     * @param barraca Barraca a adicionar.
     * @return true se a barraca foi adicionada, false caso contrário.
     */
    public boolean adicionarBarraca(Barraca barraca) {
        if (barraca == null) return false;
        return barracas.add(barraca);
    }

    /**
     * Adiciona um voluntário à federação.
     * @param voluntario Voluntário a adicionar.
     * @return true se o voluntário foi adicionado, false caso contrário.
     */
    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (voluntario == null) return false;
        voluntario.setFederacao(this);
        return voluntarios.add(voluntario);
    }

    /**
     * Lista voluntários ordenados por número de aluno.
     * @return Lista ordenada de voluntários.
     */
    public List<Voluntario> listarVoluntariosPorNumeroAluno() {
        List<Voluntario> sortedList = new ArrayList<>(voluntarios);
        sortedList.sort(Comparator.comparing(Voluntario::getNumeroAluno));
        return sortedList;
    }

    /**
     * Lista barracas ordenadas por vendas (decrescente) e agrupadas por categoria.
     * @param data Data para a classificação.
     * @return String com a listagem.
     */
    /**
     * Lista barracas ordenadas por vendas (decrescente).
     * @param data Data para a classificação.
     * @return Lista de barracas ordenada.
     */
    public List<Barraca> listarBarracasPorVendas(Data data) {
        List<Barraca> sortedList = new ArrayList<>(barracas);
        sortedList.sort((b1, b2) -> Double.compare(b2.calcularVendasDiarias(data), b1.calcularVendasDiarias(data)));
        return sortedList;
    }

    /**
     * Salva os dados da federação em um arquivo.
     */
    public void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("federacao.dat"))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    /**
     * Carrega os dados da federação de um arquivo.
     */
    public void carregarDados() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("federacao.dat"))) {
            Federacao loaded = (Federacao) in.readObject();
            this.nome = loaded.nome;
            this.produtos.clear();
            this.produtos.addAll(loaded.produtos);
            this.barracas.clear();
            this.barracas.addAll(loaded.barracas);
            this.voluntarios.clear();
            this.voluntarios.addAll(loaded.voluntarios);
            for (Voluntario v : this.voluntarios) {
                v.setFederacao(this);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    /**
     * Inicializa a federação com dados de teste.
     */
    public void inicializarDadosTeste() {
        // Produtos
        adicionarProduto(new Produto("Cerveja", 2.0));
        adicionarProduto(new Produto("Hamburger", 5.0));

        // Barracas
        Barraca b1 = new Barraca("Barraca FEUP", "FEUP");
        Barraca b2 = new Barraca("Barraca ISEP", "ISEP");
        adicionarBarraca(b1);
        adicionarBarraca(b2);

        // Estoque
        b1.adicionarEstoque(produtos.get(0), 100);
        b1.adicionarEstoque(produtos.get(1), 50);
        b2.adicionarEstoque(produtos.get(0), 80);

        // Voluntários
        Voluntario v1 = new VoluntarioVendas("João Silva", "2023001", "Eng. Sistemas", "123", "ISEP");
        Voluntario v2 = new VoluntarioStock("Maria Santos", "2023002", "Eng. Informática", "123", "FEUP");
        Voluntario v3 = new VoluntarioVendas("Pedro Costa", "2023003", "Eng. Mecânica", "123", "ISEP");
        adicionarVoluntario(v1);
        adicionarVoluntario(v2);
        adicionarVoluntario(v3);

        // Escala
        Escala e1 = new Escala(new Data(2025, 5, 15), b1);
        e1.adicionarVoluntario(v1);
        e1.adicionarVoluntario(v2);
        b1.getEscalas().add(e1);
    }

    /**
     * Representação textual da federação.
     * @return String com detalhes da federação.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Federação: " + nome + "\n");
        result.append("Produtos:\n");
        if (produtos.isEmpty()) result.append(" (VAZIO)\n");
        else for (Produto p : produtos) result.append("\t- ").append(p).append("\n");
        result.append("Barracas:\n");
        if (barracas.isEmpty()) result.append(" (VAZIO)\n");
        else for (Barraca b : barracas) result.append("\t- ").append(b.getNome()).append(" (").append(b.getInstituicao()).append(")\n");
        result.append("Voluntários:\n");
        if (voluntarios.isEmpty()) result.append(" (VAZIO)\n");
        else for (Voluntario v : voluntarios) result.append("\t- ").append(v).append("\n");
        return result.toString();
    }

    /**
     * Obtém a lista de produtos.
     * @return Lista de produtos.
     */
    public List<Produto> getProdutos() {
        return produtos; }

    /**
     * Obtém a lista de barracas.
     * @return Lista de barracas.
     */
    public List<Barraca> getBarracas() {
        return barracas; }

    /**
     * Obtém a lista de voluntários.
     * @return Lista de voluntários.
     */
    public List<Voluntario> getVoluntarios() {
        return voluntarios; }
}