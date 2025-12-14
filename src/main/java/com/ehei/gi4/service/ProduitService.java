package com.ehei.gi4.service;

import com.ehei.gi4.model.Produit;
import com.ehei.gi4.exception.ProduitExistantException;
import com.ehei.gi4.exception.ProduitNonTrouveException;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {
    private List<Produit> produits;

    public ProduitService() {
        this.produits = new ArrayList<>();
    }

    // CREATE
    public void ajouterProduit(Produit produit) {
        // Vérifier unicité ID
        for (Produit p : produits) {
            if (p.getId().equals(produit.getId())) {
                throw new ProduitExistantException("ID existe déjà");
            }
        }

        // Vérifier unicité nom
        for (Produit p : produits) {
            if (p.getNom().equalsIgnoreCase(produit.getNom())) {
                throw new ProduitExistantException("Nom existe déjà");
            }
        }

        produits.add(produit);
    }

    // READ
    public Produit obtenirProduit(Long id) {
        for (Produit p : produits) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new ProduitNonTrouveException("Produit non trouvé");
    }

    public List<Produit> obtenirTousProduits() {
        return produits;
    }
    public void mettreAJourProduit(Long id, Produit nouveauProduit) {
        Produit produit = obtenirProduit(id);

        // Vérifier si le nouveau nom existe déjà
        for (Produit p : produits) {
            if (!p.getId().equals(id) && p.getNom().equalsIgnoreCase(nouveauProduit.getNom())) {
                throw new ProduitExistantException("Ce nom existe déjà");
            }
        }

        produit.setNom(nouveauProduit.getNom());
        produit.setPrix(nouveauProduit.getPrix());
        produit.setQuantite(nouveauProduit.getQuantite());
    }

    // DELETE
    public void supprimerProduit(Long id) {
        Produit produit = obtenirProduit(id);
        produits.remove(produit);
    }
}
