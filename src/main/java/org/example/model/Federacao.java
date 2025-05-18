package org.example.model;

import org.example.utils.Data;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe que representa a federação que gerencia barracas, produtos e voluntários.
 */
public class Federacao {
    private String nome;
    private List<Produto> produtos;
    private List<Barraca> barracas;
    private List<Pessoa> pessoas;

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
        this.pessoas = new ArrayList<>();
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
     * Adiciona uma pessoa (voluntário ou administrador) à federação.
     * @param pessoa Pessoa a adicionar.
     * @return true se a pessoa foi adicionada, false caso contrário.
     */
    public boolean adicionarPessoa(Pessoa pessoa) {
        if (pessoa == null) return false;
        if (pessoa instanceof Voluntario) {
            ((Voluntario) pessoa).setFederacao(this);
        }
        return pessoas.add(pessoa);
    }

    /**
     * Lista voluntários ordenados por número de aluno.
     * @return Lista ordenada de voluntários.
     */
    public List<Voluntario> listarVoluntariosPorNumeroAluno() {
        List<Voluntario> voluntarios = new ArrayList<>();
        for (Pessoa p : pessoas) {
            if (p instanceof Voluntario) {
                voluntarios.add((Voluntario) p);
            }
        }
        voluntarios.sort(Comparator.comparing(Voluntario::getNumeroAluno));
        return voluntarios;
    }

    /**
     * Lista barracas agrupadas por classificação (Ouro, Prata, Bronze) e ordenadas por vendas (decrescente).
     * @param data Data para a classificação.
     * @return String com a listagem.
     */
    public String listarBarracasPorVendas(Data data) {
        StringBuilder result = new StringBuilder("Barracas por Vendas (" + data.toAnoMesDiaString() + "):\n");
        List<Barraca> ouro = new ArrayList<>();
        List<Barraca> prata = new ArrayList<>();
        List<Barraca> bronze = new ArrayList<>();
        for (Barraca b : barracas) {
            String classificacao = b.classificar(data);
            if (classificacao.equals("Ouro")) ouro.add(b);
            else if (classificacao.equals("Prata")) prata.add(b);
            else bronze.add(b);
        }
        ouro.sort((b1, b2) -> Double.compare(b2.calcularVendasDiarias(data), b1.calcularVendasDiarias(data)));
        prata.sort((b1, b2) -> Double.compare(b2.calcularVendasDiarias(data), b1.calcularVendasDiarias(data)));
        bronze.sort((b1, b2) -> Double.compare(b2.calcularVendasDiarias(data), b1.calcularVendasDiarias(data)));
        result.append("\nOuro:\n").append(listarGrupo(ouro, data));
        result.append("\nPrata:\n").append(listarGrupo(prata, data));
        result.append("\nBronze:\n").append(listarGrupo(bronze, data));
        return result.toString();
    }

    private String listarGrupo(List<Barraca> grupo, Data data) {
        StringBuilder result = new StringBuilder();
        if (grupo.isEmpty()) result.append("\t(Nenhuma barraca)\n");
        else for (Barraca b : grupo) result.append("\t").append(b.getNome()).append(": €").append(String.format("%.2f", b.calcularVendasDiarias(data))).append("\n");
        return result.toString();
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

        // Pessoas
        Administrador admin = new Administrador("Admin", "0000001", "Administração", "admin123");
        Voluntario v1 = new VoluntarioVendas("João Silva", "2023001", "Eng. Sistemas", "123", "FEUP");
        Voluntario v2 = new VoluntarioStock("Maria Santos", "2023002", "Eng. Informática", "123", "FEUP");
        Voluntario v3 = new VoluntarioVendas("Pedro Costa", "2023003", "Eng. Mecânica", "123", "FEUP");
        adicionarPessoa(admin);
        adicionarPessoa(v1);
        adicionarPessoa(v2);
        adicionarPessoa(v3);

        // Escala
        Escala e1 = new Escala(new Data(2025, 5, 15), b1);
        try {
            e1.adicionarVoluntario(v1);
            e1.adicionarVoluntario(v2);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar escala de teste: " + e.getMessage());
        }
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
        result.append("Pessoas:\n");
        if (pessoas.isEmpty()) result.append(" (VAZIO)\n");
        else for (Pessoa p : pessoas) result.append("\t- ").append(p).append("\n");
        return result.toString();
    }

    /**
     * Obtém a lista de produtos.
     * @return Lista de produtos.
     */
    public List<Produto> getProdutos() { return produtos; }

    /**
     * Obtém a lista de barracas.
     * @return Lista de barracas.
     */
    public List<Barraca> getBarracas() { return barracas; }

    /**
     * Obtém a lista de pessoas.
     * @return Lista de pessoas.
     */
    public List<Pessoa> getPessoas() { return pessoas; }
}
