package org.example.test;

import org.example.model.*;
import org.example.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FederacaoTest {

    private Federacao federacao;

    @BeforeEach
    public void setUp() {
        federacao = new Federacao("Queima Porto");
    }

    @Test
    public void testAdicionarProdutoValido() {
        Produto cerveja = new Produto("Cerveja", 2.0);
        assertTrue(federacao.adicionarProduto(cerveja));
    }

    @Test
    public void testAdicionarProdutoDuplicado() {
        Produto cerveja = new Produto("Cerveja", 2.0);
        federacao.adicionarProduto(cerveja);
        Produto repetido = new Produto("cerveja", 2.5); // mesmo nome, diferente case
        assertFalse(federacao.adicionarProduto(repetido));
    }

    @Test
    public void testAdicionarProdutoNulo() {
        assertFalse(federacao.adicionarProduto(null));
    }

    @Test
    public void testAdicionarBarracaValida() {
        Barraca barraca = new Barraca("Barraca FEUP", "FEUP");
        assertTrue(federacao.adicionarBarraca(barraca));
    }

    @Test
    public void testAdicionarBarracaNula() {
        assertFalse(federacao.adicionarBarraca(null));
    }

    @Test
    public void testAdicionarPessoaValida() {
        Pessoa voluntario = new VoluntarioVendas("Joana", "1234567", "Eng. Informática", "abc123", "FEUP");
        assertTrue(federacao.adicionarPessoa(voluntario));
    }

    @Test
    public void testAdicionarPessoaNula() {
        assertFalse(federacao.adicionarPessoa(null));
    }

    @Test
    public void testListarVoluntariosPorNumeroAluno() {
        Voluntario v1 = new VoluntarioVendas("Zé", "2024003", "Curso", "pass", "ISEP");
        Voluntario v2 = new VoluntarioStock("Ana", "2024001", "Curso", "pass", "ISEP");
        Voluntario v3 = new VoluntarioStock("Luis", "2024002", "Curso", "pass", "ISEP");

        federacao.adicionarPessoa(v1);
        federacao.adicionarPessoa(v2);
        federacao.adicionarPessoa(v3);

        var lista = federacao.listarVoluntariosPorNumeroAluno();
        assertEquals(3, lista.size());
        assertEquals("2024001", lista.get(0).getNumeroAluno());
        assertEquals("2024002", lista.get(1).getNumeroAluno());
        assertEquals("2024003", lista.get(2).getNumeroAluno());
    }

    @Test
    public void testInicializarDadosTeste() {
        federacao.inicializarDadosTeste();
        assertEquals(2, federacao.getProdutos().size());
        assertEquals(2, federacao.getBarracas().size());
        assertEquals(4, federacao.getPessoas().size()); // 1 admin + 3 voluntários
    }

    @Test
    public void testToStringNaoVazio() {
        federacao.inicializarDadosTeste();
        String output = federacao.toString();
        assertTrue(output.contains("Federação:"));
        assertTrue(output.contains("Produtos:"));
        assertTrue(output.contains("Barracas:"));
        assertTrue(output.contains("Pessoas:"));
    }
}
