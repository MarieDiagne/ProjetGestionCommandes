package com.brasilburger.model;

import java.math.BigDecimal;


public class Burger {
    
    private Integer id;
    private String nom;
    private String description;
    private BigDecimal prix;
    private String image;
    private Boolean archive;
    
    
    
    public Burger() {
        this.archive = false;
    }
    
    public Burger(Integer id, String nom, String description, BigDecimal prix, String image, Boolean archive) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.archive = archive != null ? archive : false;
    }
    
    public Burger(String nom, String description, BigDecimal prix, String image) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getPrix() {
        return prix;
    }
    
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
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
    
    public boolean isArchive() {
        return archive != null && archive;
    }
    
 
    public boolean isDisponible() {
        return !isArchive();
    }
    
   
    public boolean hasImage() {
        return image != null && !image.trim().isEmpty();
    }
    
    
    
    @Override
    public String toString() {
        return String.format("Burger[id=%d, nom=%s, prix=%.2f FCFA, archive=%s]", 
            id, nom, prix, archive ? "Oui" : "Non");
    }
    
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n┌─────────────────────────────────────────────────┐\n");
        sb.append("│ 🍔 BURGER                                       │\n");
        sb.append("├─────────────────────────────────────────────────┤\n");
        sb.append(String.format("│ ID          : %-34d │\n", id));
        sb.append(String.format("│ Nom         : %-34s │\n", nom));
        
        if (description != null && !description.isEmpty()) {
            
            String[] descLines = splitText(description, 34);
            sb.append(String.format("│ Description : %-34s │\n", descLines[0]));
            for (int i = 1; i < descLines.length; i++) {
                sb.append(String.format("│               %-34s │\n", descLines[i]));
            }
        } else {
            sb.append("│ Description : N/A                              │\n");
        }
        
        sb.append(String.format("│ Prix        : %-28.2f FCFA │\n", prix));
        sb.append(String.format("│ Statut      : %-34s │\n", archive ? "❌ Archivé" : "✅ Disponible"));
        
        if (hasImage()) {
            sb.append(String.format("│ Image       : %-34s │\n", "✅ Disponible"));
        } else {
            sb.append("│ Image       : ❌ Non disponible                │\n");
        }
        
        sb.append("└─────────────────────────────────────────────────┘\n");
        return sb.toString();
    }
    

    private String[] splitText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return new String[]{text};
        }
        
        java.util.List<String> lines = new java.util.ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();
        
        for (String word : words) {
            if (currentLine.length() + word.length() + 1 <= maxLength) {
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }
        
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }
        
        return lines.toArray(new String[0]);
    }
}