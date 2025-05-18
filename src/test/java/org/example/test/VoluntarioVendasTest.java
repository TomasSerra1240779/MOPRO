package org.example.test;

import org.example.model.*;
import org.example.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoluntarioVendasTest {

    private VoluntarioVendas voluntario;
    private Produto produto1;
    private Produto produto2;
    private Data data;
    private Barraca barraca;

    @BeforeEach
    void setUp() {
        voluntario = new VoluntarioVendas("Ana", "12345", "Engenharia", "senha123", "FEUP");
        produto1 = new Produto("Cerveja", 2.0);
        produto2 = new Produto("Água", 1.0);
        data = new Data(2025, 5, 18);
        barraca = new Barraca("Barraca do Zé", "FEUP");
    }

    @Test
    void registrarVenda() {
        Venda venda = new Venda(data, produto1, 3, voluntario, barraca);
        boolean resultado = voluntario.registrarVenda(venda);
        assertTrue(resultado, "A venda deveria ter sido registrada com sucesso.");
    }

    @Test
    void calcularVendasDiarias() {
        voluntario.registrarVenda(new Venda(data, produto1, 5, voluntario, barraca)); // 5 * 2.0 = 10.0
        voluntario.registrarVenda(new Venda(data, produto2, 2, voluntario, barraca)); // 2 * 1.0 = 2.0
        Data outroDia = new Data(2025, 5, 17);
        voluntario.registrarVenda(new Venda(outroDia, produto1, 10, voluntario, barraca)); // 10 * 2.0 = 20.0 (não conta)

        double total = voluntario.calcularVendasDiarias(data);
        assertEquals(12.0, total, 0.01, "Total de vendas diárias está incorreto.");
    }

    @Test
    void classificar() {
        // Teste "Bronze" (< 500)
        voluntario.registrarVenda(new Venda(data, produto1, 10, voluntario, barraca)); // 10 * 2.0 = 20.0
        assertEquals("Bronze", voluntario.classificar(data));

        // Teste "Prata" (500 <= x <= 1000)
        voluntario = new VoluntarioVendas("Ana", "12345", "Engenharia", "senha123", "FEUP");
        voluntario.registrarVenda(new Venda(data, produto1, 300, voluntario, barraca)); // 300 * 2 = 600
        assertEquals("Prata", voluntario.classificar(data));

        // Teste "Ouro" (> 1000)
        voluntario = new VoluntarioVendas("Ana", "12345", "Engenharia", "senha123", "FEUP");
        voluntario.registrarVenda(new Venda(data, produto1, 600, voluntario, barraca)); // 600 * 2 = 1200
        assertEquals("Ouro", voluntario.classificar(data));
    }
}
