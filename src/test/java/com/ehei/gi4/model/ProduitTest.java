package com.ehei.gi4.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Produit produit;

    @BeforeEach
    void setUp() {
        produit = new Produit(1L, "Ordinateur", 999.99, 10);
    }

    @AfterEach
    void tearDown() {
        produit = null;
    }

    @Test
    void testCreationProduit() {
        assertEquals(1L, produit.getId());
        assertEquals("Ordinateur", produit.getNom());
        assertEquals(999.99, produit.getPrix());
        assertEquals(10, produit.getQuantite());
    }

    @Test
    void testPrixNegatif() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Produit(1L, "Test", -10.0, 5);
        });
    }

    @Test
    void testQuantiteNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Produit(1L, "Test", 10.0, -5);
        });
    }

    @Test
    void testGetters() {
        assertEquals(1L, produit.getId());
        assertEquals("Ordinateur", produit.getNom());
    }

    @Test
    void testSetters() {
        produit.setNom("PC");
        produit.setPrix(1200.0);
        produit.setQuantite(15);

        assertEquals("PC", produit.getNom());
        assertEquals(1200.0, produit.getPrix());
        assertEquals(15, produit.getQuantite());
    }
}