package com.brasilburger.model;

import com.brasilburger.enums.TypeComplementEnum;
import java.math.BigDecimal;


public class Complement {

    private Integer id;
    private String nom;
    private TypeComplementEnum type;
    private String image;
    private BigDecimal prix;
    private Boolean archive;
    private Integer menuId; 


    

    public Complement() {
        this.archive = false;
    }

    public Complement(Integer id, String nom, TypeComplementEnum type, String image, BigDecimal prix, Boolean archive) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.image = image;
        this.prix = prix;
        this.archive = archive != null ? archive : false;
    }

    public Complement(String nom, TypeComplementEnum type, String image, BigDecimal prix) {
        this.nom = nom;
        this.type = type;
        this.image = image;
        this.prix = prix;
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

    public TypeComplementEnum getType() {
        return type;
    }

    public void setType(TypeComplementEnum type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
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

    public boolean isBoisson() {
        return type == TypeComplementEnum.BOISSON;
    }

   
    public boolean isFrites() {
        return type == TypeComplementEnum.FRITES;
    }
    public Integer getMenuId() {
    return menuId;
}

    public void setMenuId(Integer menuId) {
    this.menuId = menuId;
    }


   
    public String getEmoji() {
        return isBoisson() ? "🥤" : "🍟";
    }

    

    @Override
    public String toString() {
        return String.format("Complement[id=%d, nom=%s, type=%s, prix=%.2f FCFA, archive=%s]",
                id, nom, type, prix, archive ? "Oui" : "Non");
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n┌─────────────────────────────────────────────────┐\n");
        sb.append(String.format("│ %s COMPLÉMENT                                   │\n", getEmoji()));
        sb.append("├─────────────────────────────────────────────────┤\n");
        sb.append(String.format("│ ID          : %-34d │\n", id));
        sb.append(String.format("│ Nom         : %-34s │\n", nom));
        sb.append(String.format("│ Type        : %-34s │\n", type));
        sb.append(String.format("│ Prix        : %-28.2f FCFA │\n", prix));
        sb.append(String.format("│ Statut      : %-34s │\n", archive ? "❌ Archivé" : "✅ Disponible"));

        if (hasImage()) {
            sb.append("│ Image       : ✅ Disponible                     │\n");
        } else {
            sb.append("│ Image       : ❌ Non disponible                │\n");
        }

        sb.append("└─────────────────────────────────────────────────┘\n");
        return sb.toString();
    }

  
    public String toCompactString() {
        return String.format("%s %s - %.2f FCFA %s",
                getEmoji(),
                nom,
                prix,
                archive ? "(Archivé)" : "");
    }
}