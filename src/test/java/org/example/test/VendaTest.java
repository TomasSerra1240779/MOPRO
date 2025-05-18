package org.example.test;

import org.example.model.*;
import org.example.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendaTest {
    private Venda venda;
    private Data data;
    private Produto produto;
    private VoluntarioVendas voluntario;
    private Barraca barraca;
    private Federacao federacao;

    @BeforeEach
    void setUp() {
        federacao = new Federacao("Teste FAP");
        data = new Data(2025, 5, 18);
        produto = new Produto("Cerveja", 2.0);
        voluntario = new VoluntarioVendas("João", "2023001", "Eng", "123", "FEUP");
        barraca = new Barraca("Barraca FEUP", "FEUP");
        federacao.adicionarPessoa(voluntario);
        federacao.adicionarBarraca(barraca);
        venda = new Venda(data, produto, 10, voluntario, barraca);
    }

    @Test
    void testConstrutor() {
        assertDoesNotThrow(() -> new Venda(data, produto, 10, voluntario, barraca),
                "Deveria criar venda com parâmetros válidos");

        assertThrows(IllegalArgumentException.class, () -> new Venda(null, produto, 10, voluntario, barraca),
                "Deveria falhar com data nula");
        assertThrows(IllegalArgumentException.class, () -> new Venda(data, null, 10, voluntario, barraca),
                "Deveria falhar com produto nulo");
        assertThrows(IllegalArgumentException.class, () -> new Venda(data, produto, 0, voluntario, barraca),
                "Deveria falhar com quantidade zero");
        assertThrows(IllegalArgumentException.class, () -> new Venda(data, produto, -1, voluntario, barraca),
                "Deveria falhar com quantidade negativa");
        assertThrows(IllegalArgumentException.class, () -> new Venda(data, produto, 10, null, barraca),
                "Deveria falhar com voluntário nulo");
        assertThrows(IllegalArgumentException.class, () -> new Venda(data, produto, 10, voluntario, null),
                "Deveria falhar com barraca nula");
    }

    @Test
    void getData() {
        assertEquals(data, venda.getData(), "Data deveria ser 2025-05-18");
    }

    @Test
    void getProduto() {
        assertEquals(produto, venda.getProduto(), "Produto deveria ser Cerveja");
    }

    @Test
    void getQuantidade() {
        assertEquals(10, venda.getQuantidade(), "Quantidade deveria ser 10");
    }

    @Test
    void getValorTotal() {
        assertEquals(20.0, venda.getValorTotal(), 0.01, "Valor total deveria ser 20.00");
    }

    @Test
    void testToString() {
        assertEquals("Venda em 2025/05/18: Cerveja x10 = €20,00 por João",
                venda.toString(), "toString deveria ser Venda em 2025-05-18: Cerveja x10 = €20,00 por João");
    }
}