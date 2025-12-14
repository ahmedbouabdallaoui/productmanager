package com.ehei.gi4;

import com.ehei.gi4.model.Produit;
import com.ehei.gi4.service.ProduitService;

public class App {

    public static void main(String[] args) {

        ProduitService service = new ProduitService();

        try {
            System.out.println("AJOUT DE PRODUITS ");
            Produit p1 = new Produit(1L, "Clavier", 250.0, 10);
            Produit p2 = new Produit(2L, "Souris", 120.0, 20);

            service.ajouterProduit(p1);
            service.ajouterProduit(p2);

            System.out.println("Produits ajoutés avec succès\n");

            System.out.println("AFFICHAGE D’UN PRODUIT");
            Produit produit = service.obtenirProduit(1L);
            afficherProduit(produit);

            System.out.println("\nAFFICHAGE DE TOUS LES PRODUITS");
            for (Produit p : service.obtenirTousProduits()) {
                afficherProduit(p);
            }


            System.out.println("\nPRODUITS RESTANTS");
            for (Produit p : service.obtenirTousProduits()) {
                afficherProduit(p);
            }

            System.out.println("\nMISE À JOUR D’UN PRODUIT");
            Produit nouveau = new Produit(1L, "Clavier Gaming", 350.0, 8);
            service.mettreAJourProduit(1L, nouveau);

            afficherProduit(service.obtenirProduit(1L));

            System.out.println("\nSUPPRESSION D’UN PRODUIT");
            service.supprimerProduit(2L);
            System.out.println("Produit avec ID 2 supprimé");

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    private static void afficherProduit(Produit p) {
        System.out.println(
                "ID: " + p.getId() +
                        ", Nom: " + p.getNom() +
                        ", Prix: " + p.getPrix() +
                        ", Quantité: " + p.getQuantite()
        );
    }
}
