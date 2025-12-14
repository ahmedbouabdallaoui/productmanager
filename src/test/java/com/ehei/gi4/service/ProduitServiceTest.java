package com.ehei.gi4.service;

import com.ehei.gi4.exception.ProduitExistantException;
import com.ehei.gi4.exception.ProduitNonTrouveException;
import com.ehei.gi4.model.Produit;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


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


    @Test
    void testMettreAJour() {
        service.ajouterProduit(new Produit(1L, "Ordinateur", 999.99, 10));

        Produit maj = new Produit(1L, "PC Portable", 1299.99, 15);
        service.mettreAJourProduit(1L, maj);

        Produit resultat = service.obtenirProduit(1L);
        assertEquals("PC Portable", resultat.getNom());
    }

    @Test
    void testMajInexistant() {
        assertThrows(ProduitNonTrouveException.class, () -> {
            service.mettreAJourProduit(999L, new Produit(1L, "Test", 10.0, 5));
        });
    }

    // TESTS DELETE
    @Test
    void testSupprimer() {
        service.ajouterProduit(new Produit(1L, "Ordinateur", 999.99, 10));
        service.supprimerProduit(1L);

        assertThrows(ProduitNonTrouveException.class, () -> {
            service.obtenirProduit(1L);
        });
    }

    @Test
    void testSupprimerInexistant() {
        assertThrows(ProduitNonTrouveException.class, () -> {
            service.supprimerProduit(999L);
        });
    }
}