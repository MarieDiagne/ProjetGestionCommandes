package com.brasilburger.views;

import com.brasilburger.enums.TypeComplementEnum;
import com.brasilburger.model.Complement;
import com.brasilburger.model.Menu;
import com.brasilburger.service.IComplementService;
import com.brasilburger.service.IMenuService;
import com.brasilburger.service.impl.ComplementServiceImpl;
import com.brasilburger.service.impl.MenuServiceImpl;
import com.brasilburger.utils.InputHelper;

import java.math.BigDecimal;
import java.util.List;

public class MenuView {

    private final IMenuService menuService;

    public MenuView() {
        this.menuService = new MenuServiceImpl();
    }

    public void afficherMenu() {
        while (true) {
            InputHelper.clearConsole();
            InputHelper.afficherSeparateur("GESTION DES MENUS");
            System.out.println("1. Ajouter un menu");
            System.out.println("2. Lister les menus");
            System.out.println("3. Modifier un menu");
            System.out.println("4. Archiver un menu");
            System.out.println("0. Retour");
            System.out.println("═══════════════════════════════════════════════════");

            int choix = InputHelper.lireEntier("Votre choix : ", 0, 4);

            switch (choix) {
                case 1 -> ajouterMenu();
                //case 2 -> listerMenus();
                //case 3 -> modifierMenu();
                //case 4 -> archiverMenu();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void ajouterMenu() {
        InputHelper.clearConsole();
        InputHelper.afficherSeparateur("AJOUTER UN MENU");

        String nom = InputHelper.lireStringNonVide("Nom du menu : ");

        System.out.print("URL de l'image (optionnel) : ");
        String image = InputHelper.lireString();
        if (image.isEmpty())
            image = null;

        Menu menu = new Menu(nom, image);

        System.out.println("\n📋 RÉCAPITULATIF :");
        System.out.println("Nom   : " + nom);
        System.out.println("Image : " + (image != null ? "✅ Oui" : "❌ Non"));

        if (!InputHelper.confirmer("\nConfirmer la création ?")) {
            InputHelper.afficherInfo("Création annulée.");
            InputHelper.pause();
            return;
        }

        Menu createdMenu = menuService.create(menu);
        if (createdMenu == null) {
            InputHelper.afficherErreur("Erreur lors de la création du menu.");
            InputHelper.pause();
            return;
        }

        System.out.println("\n" + createdMenu.toDetailedString());
        InputHelper.afficherInfo(
                "⚠️ Vous devez maintenant ajouter les compléments obligatoires : 🍔 Burger, 🍟 Frites, 🥤 Boisson.");

        IComplementService complementService = new ComplementServiceImpl();

        // ================= FRITES =================
        InputHelper.afficherSeparateur("🍟 FRITES (obligatoire)");
        String nomFrites = InputHelper.lireStringNonVide("Nom des frites : ");
        BigDecimal prixFrites = InputHelper.lirePrix("Prix des frites : ");

        Complement frites = new Complement();
        frites.setNom(nomFrites);
        frites.setType(TypeComplementEnum.FRITES);
        frites.setPrix(prixFrites);
        frites.setMenuId(createdMenu.getId());
        complementService.create(frites);

        // ================= BOISSON =================
        InputHelper.afficherSeparateur("🥤 BOISSON (obligatoire)");
        String nomBoisson = InputHelper.lireStringNonVide("Nom de la boisson : ");
        BigDecimal prixBoisson = InputHelper.lirePrix("Prix de la boisson : ");

        Complement boisson = new Complement();
        boisson.setNom(nomBoisson);
        boisson.setType(TypeComplementEnum.BOISSON);
        boisson.setPrix(prixBoisson);
        boisson.setMenuId(createdMenu.getId());
        complementService.create(boisson);

        InputHelper.afficherSucces("✅ Menu créé avec succès avec tous les compléments obligatoires !");
        InputHelper.pause();
    }
}