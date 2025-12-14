package com.ehei.gi4.model;

public class Produit {
    private Long id;
    private String nom;
    private double prix;
    private int quantite;

    public Produit(Long id, String nom, double prix, int quantite) {
        if (prix < 0 || quantite < 0) {
            throw new IllegalArgumentException("Prix et quantité doivent être positifs");
        }

        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}