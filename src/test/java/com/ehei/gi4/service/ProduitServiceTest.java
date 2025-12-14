package com.ehei.gi4.service;

import com.ehei.gi4.exception.ProduitExistantException;
import com.ehei.gi4.exception.ProduitNonTrouveException;
import com.ehei.gi4.model.Produit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProduitServiceTest {

    private ProduitService service;

    @BeforeEach
    void setUp() {
        service = new ProduitService();
    }

    @AfterEach
    void tearDown() {
        service = null;
    }

    @Test
    void testAjouterProduit() {
        Produit produit = new Produit(1L, "Ordinateur", 999.99, 10);
        service.ajouterProduit(produit);

        assertEquals(1, service.obtenirTousProduits().size());
    }

    @Test
    void testAjouterIdExistant() {
        service.ajouterProduit(new Produit(1L, "Produit1", 10.0, 5));

        assertThrows(ProduitExistantException.class, () -> {
            service.ajouterProduit(new Produit(1L, "Produit2", 20.0, 10));
        });
    }

    @Test
    void testAjouterNomExistant() {
        service.ajouterProduit(new Produit(1L, "Ordinateur", 10.0, 5));

        assertThrows(ProduitExistantException.class, () -> {
            service.ajouterProduit(new Produit(2L, "Ordinateur", 20.0, 10));
        });
    }

    @Test
    void testObtenirProduit() {
        service.ajouterProduit(new Produit(1L, "Ordinateur", 999.99, 10));

        Produit resultat = service.obtenirProduit(1L);
        assertEquals("Ordinateur", resultat.getNom());
    }

    @Test
    void testObtenirProduitInexistant() {
        assertThrows(ProduitNonTrouveException.class, () -> {
            service.obtenirProduit(999L);
        });
    }

    @Test
    void testObtenirTous() {
        service.ajouterProduit(new Produit(1L, "Produit1", 10.0, 5));
        service.ajouterProduit(new Produit(2L, "Produit2", 20.0, 10));

        assertEquals(2, service.obtenirTousProduits().size());
    }


}