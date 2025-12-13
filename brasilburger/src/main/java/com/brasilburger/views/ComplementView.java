package com.brasilburger.views;

import com.brasilburger.enums.TypeComplementEnum;
import com.brasilburger.model.Complement;
import com.brasilburger.service.IComplementService;
import com.brasilburger.service.impl.ComplementServiceImpl;
import com.brasilburger.utils.InputHelper;
import java.math.BigDecimal;
import java.util.List;

public class ComplementView {

    private final IComplementService complementService;

    public ComplementView() {
        this.complementService = new ComplementServiceImpl();
    }

    public void afficherMenu() {
        while (true) {
            InputHelper.clearConsole();
            InputHelper.afficherSeparateur("GESTION DES COMPLÉMENTS");
            System.out.println("1. Ajouter un complément");
            System.out.println("2. Lister les compléments");
            System.out.println("3. Modifier un complément");
            System.out.println("4. Archiver un complément");
            System.out.println("0. Retour");
            System.out.println("═══════════════════════════════════════════════════");

            int choix = InputHelper.lireEntier("Votre choix : ", 0, 4);

            switch (choix) {
                case 1 -> ajouterComplement();
                // case 2 -> listerComplements();
                // case 3 -> modifierComplement();
                // case 4 -> archiverComplement();
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void ajouterComplement() {
        InputHelper.clearConsole();
        InputHelper.afficherSeparateur("AJOUTER UN COMPLÉMENT");

        String nom = InputHelper.lireStringNonVide("Nom du complément : ");

        System.out.println("\nType de complément :");
        System.out.println("1. 🥤 Boisson");
        System.out.println("2. 🍟 Frites");
        int typeChoix = InputHelper.lireEntier("Votre choix : ", 1, 2);
        TypeComplementEnum type = (typeChoix == 1) ? TypeComplementEnum.BOISSON : TypeComplementEnum.FRITES;

        BigDecimal prix = InputHelper.lirePrix("Prix (FCFA) : ");

        System.out.print("URL de l'image (optionnel) : ");
        String image = InputHelper.lireString();
        if (image.isEmpty()) {
            image = null;
        }

        Complement complement = new Complement(nom, type, image, prix);

        System.out.println("\n📋 RÉCAPITULATIF :");
        System.out.println("Nom   : " + nom);
        System.out.println("Type  : " + type.getValue());
        System.out.println("Prix  : " + prix + " FCFA");
        System.out.println("Image : " + (image != null ? "✅ Oui" : "❌ Non"));

        if (InputHelper.confirmer("\nConfirmer la création ?")) {
            Complement created = complementService.create(complement);
            if (created != null) {
                System.out.println("\n" + created.toDetailedString());
            }
        } else {
            InputHelper.afficherInfo("Création annulée.");
        }

        InputHelper.pause();
    }
}