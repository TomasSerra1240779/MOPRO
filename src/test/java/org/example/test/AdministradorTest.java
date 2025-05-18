package org.example.test;

import org.example.model.Administrador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {

    private Administrador admin;

    @BeforeEach
    public void setUp() {
        admin = new Administrador("Maria", "2023123", "Gestão", "admin123");
    }

    @Test
    public void testCriacaoAdministrador() {
        assertEquals("Maria", admin.getNome());
        assertEquals("2023123", admin.getNumeroAluno());
        assertEquals("Gestão", admin.getCurso());
        assertEquals("admin123", admin.getPassword());
    }

    @Test
    public void testAutenticacaoCorreta() {
        assertTrue(admin.autenticar("admin123"));
    }

    @Test
    public void testAutenticacaoIncorreta() {
        assertFalse(admin.autenticar("senhaErrada"));
    }

    @Test
    public void testAutenticacaoComStringVazia() {
        assertFalse(admin.autenticar(""));
    }

    @Test
    public void testAutenticacaoComNull() {
        assertFalse(admin.autenticar(null));
    }
}
