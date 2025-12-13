package com.brasilburger.model;


public class Composition {

    private Integer id;
    private Integer menuId;
    private Integer burgerId;
    private Integer boissonId;
    private Integer fritesId;

    // Relations chargées (pour affichage)
    private Menu menu;
    private Burger burger;
    private Complement boisson;
    private Complement frites;

    // ========== Constructeurs ==========

    public Composition() {
    }

    public Composition(Integer id, Integer menuId, Integer burgerId, Integer boissonId, Integer fritesId) {
        this.id = id;
        this.menuId = menuId;
        this.burgerId = burgerId;
        this.boissonId = boissonId;
        this.fritesId = fritesId;
    }

    public Composition(Integer menuId, Integer burgerId, Integer boissonId, Integer fritesId) {
        this.menuId = menuId;
        this.burgerId = burgerId;
        this.boissonId = boissonId;
        this.fritesId = fritesId;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getBurgerId() {
        return burgerId;
    }

    public void setBurgerId(Integer burgerId) {
        this.burgerId = burgerId;
    }

    public Integer getBoissonId() {
        return boissonId;
    }

    public void setBoissonId(Integer boissonId) {
        this.boissonId = boissonId;
    }

    public Integer getFritesId() {
        return fritesId;
    }

    public void setFritesId(Integer fritesId) {
        this.fritesId = fritesId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Burger getBurger() {
        return burger;
    }

    public void setBurger(Burger burger) {
        this.burger = burger;
    }

    public Complement getBoisson() {
        return boisson;
    }

    public void setBoisson(Complement boisson) {
        this.boisson = boisson;
    }

    public Complement getFrites() {
        return frites;
    }

    public void setFrites(Complement frites) {
        this.frites = frites;
    }

  
    public boolean isValid() {
        return menuId != null && burgerId != null && boissonId != null && fritesId != null;
    }

   
    public boolean hasLoadedRelations() {
        return burger != null && boisson != null && frites != null;
    }


    @Override
    public String toString() {
        return String.format("Composition[id=%d, menuId=%d, burgerId=%d, boissonId=%d, fritesId=%d]",
                id, menuId, burgerId, boissonId, fritesId);
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n┌─────────────────────────────────────────────────┐\n");
        sb.append("│ 📋 COMPOSITION DU MENU                          │\n");
        sb.append("├─────────────────────────────────────────────────┤\n");
        sb.append(String.format("│ ID Composition : %-31d │\n", id));

        if (menu != null) {
            sb.append(String.format("│ Menu           : %-31s │\n", menu.getNom()));
        } else {
            sb.append(String.format("│ ID Menu        : %-31d │\n", menuId));
        }

        sb.append("├─────────────────────────────────────────────────┤\n");

        if (burger != null) {
            sb.append(String.format("│ 🍔 Burger      : %-31s │\n", burger.getNom()));
            sb.append(String.format("│    Prix        : %-28.2f FCFA │\n", burger.getPrix()));
        } else {
            sb.append(String.format("│ ID Burger      : %-31d │\n", burgerId));
        }

        if (boisson != null) {
            sb.append(String.format("│ 🥤 Boisson     : %-31s │\n", boisson.getNom()));
            sb.append(String.format("│    Prix        : %-28.2f FCFA │\n", boisson.getPrix()));
        } else {
            sb.append(String.format("│ ID Boisson     : %-31d │\n", boissonId));
        }

        if (frites != null) {
            sb.append(String.format("│ 🍟 Frites      : %-31s │\n", frites.getNom()));
            sb.append(String.format("│    Prix        : %-28.2f FCFA │\n", frites.getPrix()));
        } else {
            sb.append(String.format("│ ID Frites      : %-31d │\n", fritesId));
        }

        sb.append("└─────────────────────────────────────────────────┘\n");
        return sb.toString();
    }
}