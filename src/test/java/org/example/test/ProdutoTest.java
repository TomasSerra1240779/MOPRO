package org.example.test;

import org.example.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {
    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto("Cerveja", 2.0);
    }

    @Test
    void testConstrutor() {
        assertDoesNotThrow(() -> new Produto("Cerveja", 2.0), "Deveria criar produto com nome e preço válidos");

        assertThrows(IllegalArgumentException.class, () -> new Produto(null, 2.0), "Deveria falhar com nome nulo");
        assertThrows(IllegalArgumentException.class, () -> new Produto("", 2.0), "Deveria falhar com nome vazio");
        assertThrows(IllegalArgumentException.class, () -> new Produto("Cerveja", -1.0), "Deveria falhar com preço negativo");
    }

    @Test
    void getNome() {
        assertEquals("Cerveja", produto.getNome(), "Nome deveria ser Cerveja");
    }

    @Test
    void getPreco() {
        assertEquals(2.0, produto.getPreco(), 0.01, "Preço deveria ser 2.0");
    }

    @Test
    void testToString() {
        assertEquals("Cerveja (€2,00)", produto.toString(), "toString deveria ser Cerveja (€2,00)");
    }

    @Test
    void testEquals() {
        Produto mesmoNome = new Produto("cerveja", 3.0);
        Produto nomeDiferente = new Produto("Água", 1.0);

        assertTrue(produto.equals(mesmoNome), "Deveria ser igual com mesmo nome");
        assertFalse(produto.equals(nomeDiferente), "Deveria ser diferente com nome diferente");
        assertFalse(produto.equals(null), "Deveria ser diferente de null");
        assertFalse(produto.equals("Cerveja"), "Deveria ser diferente de outra classe");
        assertTrue(produto.equals(produto), "Deveria ser igual a si mesmo");
    }
}