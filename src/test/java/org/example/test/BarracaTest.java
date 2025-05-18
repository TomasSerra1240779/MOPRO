package org.example.test;

import org.example.model.*;
import org.example.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BarracaTest {
    private Barraca barraca;
    private Produto produto;
    private Data dataAtual;
    private VoluntarioVendas voluntario;
    private Federacao federacao;

    @BeforeEach
    public void setUp() {
        federacao = new Federacao("Teste FAP");
        barraca = new Barraca("Barraca FEUP", "FEUP");
        produto = new Produto("Cerveja", 2.0);
        dataAtual = new Data(2025, 5, 18);
        voluntario = new VoluntarioVendas("João", "2023001", "Eng", "123", "FEUP");
        federacao.adicionarBarraca(barraca);
        federacao.adicionarPessoa(voluntario);
    }

    @Test
    public void testAdicionarEstoque() {
        assertTrue(barraca.adicionarEstoque(produto, 100), "Deveria adicionar estoque");
        assertEquals(100, barraca.getQuantidadeStock(produto), "Estoque deveria ser 100");
    }

    @Test
    public void testRemoverEstoque() {
        barraca.adicionarEstoque(produto, 100);
        assertTrue(barraca.removerEstoque(produto, 30), "Deveria remover estoque");
        assertEquals(70, barraca.getQuantidadeStock(produto), "Estoque deveria ser 70");
    }

    @Test
    public void testRegistrarVenda() {
        barraca.adicionarEstoque(produto, 100);
        Escala escala = new Escala(dataAtual, barraca);
        escala.adicionarVoluntario(voluntario);
        Venda venda = new Venda(dataAtual, produto, 10, voluntario, barraca);
        assertTrue(barraca.registrarVenda(venda), "Venda deveria ser registrada");
        assertEquals(90, barraca.getQuantidadeStock(produto), "Estoque deveria ser 90");
    }

    @Test
    public void testCalcularVendasDiarias() {
        barraca.adicionarEstoque(produto, 100);
        Escala escala = new Escala(dataAtual, barraca);
        escala.adicionarVoluntario(voluntario);
        Venda venda = new Venda(dataAtual, produto, 10, voluntario, barraca);
        barraca.registrarVenda(venda);
        assertEquals(20.0, barraca.calcularVendasDiarias(dataAtual), 0.01, "Vendas diárias deveriam ser 20.00");
    }

    @Test
    public void testGetEstoqueTotal() {
        barraca.adicionarEstoque(produto, 100);
        assertEquals(100, barraca.getEstoqueTotal(), "Estoque total deveria ser 100");
    }

    @Test
    public void testClassificar() {
        barraca.adicionarEstoque(produto, 49);
        assertEquals("Ouro", barraca.classificar(dataAtual), "Deveria ser Ouro com estoque < 50");
    }

    @Test
    public void testGetProdutos() {
        barraca.adicionarEstoque(produto, 100);
        assertEquals(1, barraca.getProdutos().size(), "Deveria haver 1 produto");
        assertEquals(produto, barraca.getProdutos().get(0));
    }

    @Test
    public void testGetQuantidadeStock() {
        barraca.adicionarEstoque(produto, 100);
        assertEquals(100, barraca.getQuantidadeStock(produto), "Quantidade deveria ser 100");
    }

    @Test
    public void testGetNome() {
        assertEquals("Barraca FEUP", barraca.getNome(), "Nome deveria ser Barraca FEUP");
    }

    @Test
    public void testGetInstituicao() {
        assertEquals("FEUP", barraca.getInstituicao(), "Instituição deveria ser FEUP");
    }

    @Test
    public void testGetEscalas() {
        assertEquals(0, barraca.getEscalas().size(), "Lista de escalas deveria estar vazia inicialmente");
    }

    @Test
    public void testGetVendas() {
        assertEquals(0, barraca.getVendas().size(), "Lista de vendas deveria estar vazia inicialmente");
    }

    @Test
    public void testToString() {
        assertTrue(barraca.toString().contains("Barraca FEUP"), " deveria conter o nome");
    }
}