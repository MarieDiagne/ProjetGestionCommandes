package com.brasilburger.views;

import com.brasilburger.model.Burger;
import com.brasilburger.service.IBurgerService;
import com.brasilburger.service.impl.BurgerServiceImpl;
import com.brasilburger.utils.InputHelper;
import java.math.BigDecimal;
import java.util.List;

public class BurgerView {

    private final IBurgerService burgerService;

    public BurgerView() {
        this.burgerService = new BurgerServiceImpl();
    }

    public void afficherMenu() {
        while (true) {
            InputHelper.clearConsole();
            InputHelper.afficherSeparateur("GESTION DES BURGERS");
            System.out.println("1. Ajouter un burger");
            System.out.println("2. Lister les burgers");
            System.out.println("3. Modifier un burger");
            System.out.println("4. Archiver un burger");
            System.out.println("0. Retour");
            System.out.println("═══════════════════════════════════════════════════");

            int choix = InputHelper.lireEntier("Votre choix : ", 0, 4);

            switch (choix) {
                case 1 -> ajouterBurger();
                case 2 -> listerBurgers();
                // case 3 -> modifierBurger();
                // case 4 -> archiverBurger();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void ajouterBurger() {
        InputHelper.clearConsole();
        InputHelper.afficherSeparateur("AJOUTER UN BURGER");

        String nom = InputHelper.lireStringNonVide("Nom du burger : ");

        System.out.print("Description (optionnel) : ");
        String description = InputHelper.lireString();
        if (description.isEmpty()) {
            description = null;
        }

        BigDecimal prix = InputHelper.lirePrix("Prix (FCFA) : ");

        System.out.print("URL de l'image (optionnel) : ");
        String image = InputHelper.lireString();
        if (image.isEmpty()) {
            image = null;
        }

        Burger burger = new Burger(nom, description, prix, image);

        System.out.println("\n📋 RÉCAPITULATIF :");
        System.out.println("Nom         : " + nom);
        System.out.println("Description : " + (description != null ? description : "N/A"));
        System.out.println("Prix        : " + prix + " FCFA");
        System.out.println("Image       : " + (image != null ? "✅ Oui" : "❌ Non"));

        if (InputHelper.confirmer("\nConfirmer la création ?")) {
            Burger created = burgerService.create(burger);
            if (created != null) {
                System.out.println("\n" + created.toDetailedString());
            }
        } else {
            InputHelper.afficherInfo("Création annulée.");
        }

        InputHelper.pause();
    }

    private void listerBurgers() {
        InputHelper.clearConsole();
        InputHelper.afficherSeparateur("LISTE DES BURGERS");

        System.out.println("1. Tous les burgers");
        System.out.println("2. Burgers disponibles uniquement");
        System.out.println("0. Retour");
        System.out.println("═══════════════════════════════════════════════════");

        int choix = InputHelper.lireEntier("Votre choix : ", 0, 2);

        List<Burger> burgers;
        if (choix == 1) {
            burgers = burgerService.findAll();
        } else if (choix == 2) {
            burgers = burgerService.findAllNonArchived();
        } else {
            return;
        }

        InputHelper.clearConsole();
        InputHelper.afficherSeparateur("LISTE DES BURGERS");

        if (burgers.isEmpty()) {
            InputHelper.afficherInfo("Aucun burger trouvé.");
        } else {
            System.out.println("Total : " + burgers.size() + " burger(s)\n");

            for (int i = 0; i < burgers.size(); i++) {
                Burger b = burgers.get(i);
                System.out.printf("%d. [ID:%d] %s - %.2f FCFA %s%n",
                        i + 1,
                        b.getId(),
                        b.getNom(),
                        b.getPrix(),
                        b.isArchive() ? "❌ (Archivé)" : "✅");
            }

            System.out.println("\n═══════════════════════════════════════════════════");
            System.out.print("Entrez un numéro pour voir les détails (0 pour quitter) : ");
            int numero = InputHelper.lireEntier("", 0, burgers.size());

            if (numero > 0) {
                Burger burger = burgers.get(numero - 1);
                System.out.println(burger.toDetailedString());
                InputHelper.pause();
            }
        }

        InputHelper.pause();
    }
}