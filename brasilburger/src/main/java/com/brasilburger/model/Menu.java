package com.brasilburger.model;

import java.math.BigDecimal;


public class Menu {

    private Integer id;
    private String nom;
    private String image;
    private Boolean archive;

    
    private Composition composition;

    
    private BigDecimal prixCalcule;

    

    public Menu() {
        this.archive = false;
    }

    public Menu(Integer id, String nom, String image, Boolean archive) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.archive = archive != null ? archive : false;
    }

    public Menu(String nom, String image) {
        this.nom = nom;
        this.image = image;
        this.archive = false;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
        
        if (composition != null && composition.hasLoadedRelations()) {
            calculerPrix();
        }
    }

    public BigDecimal getPrixCalcule() {
        return prixCalcule;
    }

    public void setPrixCalcule(BigDecimal prixCalcule) {
        this.prixCalcule = prixCalcule;
    }

    
    public void calculerPrix() {
        if (composition != null && composition.hasLoadedRelations()) {
            BigDecimal prixBurger = composition.getBurger().getPrix();
            BigDecimal prixBoisson = composition.getBoisson().getPrix();
            BigDecimal prixFrites = composition.getFrites().getPrix();

            this.prixCalcule = prixBurger.add(prixBoisson).add(prixFrites);
        } else {
            this.prixCalcule = BigDecimal.ZERO;
        }
    }

   
    public boolean isArchive() {
        return archive != null && archive;
    }

   
    public boolean isDisponible() {
        return !isArchive();
    }

  
    public boolean hasImage() {
        return image != null && !image.trim().isEmpty();
    }

  
    public boolean hasComposition() {
        return composition != null;
    }

  
    public boolean isComplet() {
        return hasComposition() && composition.hasLoadedRelations();
    }



    @Override
    public String toString() {
        String prixStr = prixCalcule != null ? String.format("%.2f FCFA", prixCalcule) : "N/A";
        return String.format("Menu[id=%d, nom=%s, prix=%s, archive=%s]",
                id, nom, prixStr, archive ? "Oui" : "Non");
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n┌─────────────────────────────────────────────────┐\n");
        sb.append("│ 🍽️  MENU                                        │\n");
        sb.append("├─────────────────────────────────────────────────┤\n");
        sb.append(String.format("│ ID          : %-34d │\n", id));
        sb.append(String.format("│ Nom         : %-34s │\n", nom));

        if (prixCalcule != null) {
            sb.append(String.format("│ Prix total  : %-28.2f FCFA │\n", prixCalcule));
        } else {
            sb.append("│ Prix total  : Non calculé                      │\n");
        }

        sb.append(String.format("│ Statut      : %-34s │\n", archive ? "❌ Archivé" : "✅ Disponible"));

        if (hasImage()) {
            sb.append("│ Image       : ✅ Disponible                     │\n");
        } else {
            sb.append("│ Image       : ❌ Non disponible                │\n");
        }

        sb.append("├─────────────────────────────────────────────────┤\n");

        if (hasComposition() && composition.hasLoadedRelations()) {
            sb.append("│ COMPOSITION :                                   │\n");
            sb.append(String.format("│  🍔 %-44s │\n", composition.getBurger().getNom()));
            sb.append(String.format("│     %.2f FCFA                                   │\n",
                    composition.getBurger().getPrix()));
            sb.append(String.format("│  🥤 %-44s │\n", composition.getBoisson().getNom()));
            sb.append(String.format("│     %.2f FCFA                                   │\n",
                    composition.getBoisson().getPrix()));
            sb.append(String.format("│  🍟 %-44s │\n", composition.getFrites().getNom()));
            sb.append(String.format("│     %.2f FCFA                                   │\n",
                    composition.getFrites().getPrix()));
        } else {
            sb.append("│ COMPOSITION : ❌ Non définie                    │\n");
        }

        sb.append("└─────────────────────────────────────────────────┘\n");
        return sb.toString();
    }
}