package org.example.test;

import org.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoluntarioStockTest {
    private VoluntarioStock voluntario;
    private Barraca barraca;
    private Produto produto;
    private Federacao federacao;

    @BeforeEach
    void setUp() {
        federacao = new Federacao("Teste FAP");
        voluntario = new VoluntarioStock("Maria", "2023002", "Eng", "123", "FEUP");
        barraca = new Barraca("Barraca FEUP", "FEUP");
        produto = new Produto("Cerveja", 2.0);
        federacao.adicionarPessoa(voluntario);
        federacao.adicionarBarraca(barraca);
    }

    @Test
    void testConstrutor() {
        assertDoesNotThrow(() -> new VoluntarioStock("Maria", "2023002", "Eng", "123", "FEUP"),
                "Deveria criar voluntário com parâmetros válidos");

        assertThrows(IllegalArgumentException.class, () -> new VoluntarioStock(null, "2023002", "Eng", "123", "FEUP"),
                "Deveria falhar com nome nulo");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioStock("", "2023002", "Eng", "123", "FEUP"),
                "Deveria falhar com nome vazio");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioStock("Maria", null, "Eng", "123", "FEUP"),
                "Deveria falhar com número de aluno nulo");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioStock("Maria", "2023002", null, "123", "FEUP"),
                "Deveria falhar com curso nulo");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioStock("Maria", "2023002", "Eng", null, "FEUP"),
                "Deveria falhar com senha nula");
        assertThrows(IllegalArgumentException.class, () -> new VoluntarioStock("Maria", "2023002", "Eng", "123", null),
                "Deveria falhar com instituição nula");
    }

    @Test
    void adicionarEstoque() {
        assertTrue(voluntario.adicionarEstoque(barraca, produto, 100), "Deveria adicionar estoque");
        assertEquals(100, barraca.getQuantidadeStock(produto), "Estoque deveria ser 100");

        assertFalse(voluntario.adicionarEstoque(null, produto, 100), "Deveria falhar com barraca nula");

        Barraca outraBarraca = new Barraca("Barraca ISEP", "ISEP");
        federacao.adicionarBarraca(outraBarraca);
        assertFalse(voluntario.adicionarEstoque(outraBarraca, produto, 100),
                "Deveria falhar com instituição diferente");

        assertFalse(voluntario.adicionarEstoque(barraca, null, 100),
                "Deveria falhar com produto nulo");
        assertFalse(voluntario.adicionarEstoque(barraca, produto, -10),
                "Deveria falhar com quantidade negativa");
    }
}