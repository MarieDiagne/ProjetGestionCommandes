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
                case 2 -> listerComplements();
                case 3 -> modifierComplement();
                case 4 -> archiverComplement();
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

    private void listerComplements() {
        InputHelper.clearConsole();
        InputHelper.afficherSeparateur("LISTE DES COMPLÉMENTS");

        System.out.println("1. Tous les compléments");
        System.out.println("2. Compléments disponibles uniquement");
        System.out.println("3. Boissons uniquement");
        System.out.println("4. Frites uniquement");
        System.out.println("0. Retour");
        System.out.println("═══════════════════════════════════════════════════");

        int choix = InputHelper.lireEntier("Votre choix : ", 0, 4);

        List<Complement> complements;
        String titre = "LISTE DES COMPLÉMENTS";

        switch (choix) {
            case 1 -> complements = complementService.findAll();
            case 2 -> complements = complementService.findAllNonArchived();
            case 3 -> {
                complements = complementService.findByTypeNonArchived(TypeComplementEnum.BOISSON);
                titre = "LISTE DES BOISSONS";
            }
            case 4 -> {
                complements = complementService.findByTypeNonArchived(TypeComplementEnum.FRITES);
                titre = "LISTE DES FRITES";
            }
            default -> {
                return;
            }
        }

        InputHelper.clearConsole();
        InputHelper.afficherSeparateur(titre);

        if (complements.isEmpty()) {
            InputHelper.afficherInfo("Aucun complément trouvé.");
        } else {
            System.out.println("Total : " + complements.size() + " complément(s)\n");

            for (int i = 0; i < complements.size(); i++) {
                Complement c = complements.get(i);
                System.out.printf("%d. [ID:%d] %s %s - %.2f FCFA %s%n",
                        i + 1,
                        c.getId(),
                        c.getEmoji(),
                        c.getNom(),
                        c.getPrix(),
                        c.isArchive() ? "❌ (Archivé)" : "✅");
            }

            System.out.println("\n═══════════════════════════════════════════════════");
            System.out.print("Entrez un numéro pour voir les détails (0 pour quitter) : ");
            int numero = InputHelper.lireEntier("", 0, complements.size());

            if (numero > 0) {
                Complement complement = complements.get(numero - 1);
                System.out.println(complement.toDetailedString());
                InputHelper.pause();
            }
        }

        InputHelper.pause();
    }

    private void modifierComplement() {
        InputHelper.clearConsole();
        InputHelper.afficherSeparateur("MODIFIER UN COMPLÉMENT");

        int id = InputHelper.lireEntierPositif("ID du complément à modifier : ");

        Complement complement = complementService.findById(id);
        if (complement == null) {
            InputHelper.pause();
            return;
        }

        System.out.println("\n📋 COMPLÉMENT ACTUEL :");
        System.out.println(complement.toDetailedString());

        System.out.println("\n📝 NOUVELLES INFORMATIONS (Entrée pour garder l'actuel) :");

        System.out.print("Nom [" + complement.getNom() + "] : ");
        String nom = InputHelper.lireString();
        if (nom.isEmpty()) {
            nom = complement.getNom();
        }

        System.out.println("\nType de complément [" + complement.getType().getValue() + "] :");
        System.out.println("1. 🥤 Boisson");
        System.out.println("2. 🍟 Frites");
        System.out.println("0. Garder l'actuel");
        int typeChoix = InputHelper.lireEntier("Votre choix : ", 0, 2);
        TypeComplementEnum type = complement.getType();
        if (typeChoix == 1) {
            type = TypeComplementEnum.BOISSON;
        } else if (typeChoix == 2) {
            type = TypeComplementEnum.FRITES;
        }

        System.out.print("Prix [" + complement.getPrix() + " FCFA] (0 pour garder) : ");
        String prixStr = InputHelper.lireString();
        BigDecimal prix = complement.getPrix();
        if (!prixStr.isEmpty()) {
            try {
                prix = new BigDecimal(prixStr.replace(',', '.'));
            } catch (NumberFormatException e) {
                InputHelper.afficherErreur("Prix invalide, valeur actuelle conservée.");
            }
        }

        System.out.print("URL Image [" + (complement.hasImage() ? "Définie" : "N/A") + "] : ");
        String image = InputHelper.lireString();
        if (image.isEmpty()) {
            image = complement.getImage();
        }

        complement.setNom(nom);
        complement.setType(type);
        complement.setPrix(prix);
        complement.setImage(image);

        if (InputHelper.confirmer("\nConfirmer les modifications ?")) {
            Complement updated = complementService.update(complement);
            if (updated != null) {
                System.out.println("\n" + updated.toDetailedString());
            }
        } else {
            InputHelper.afficherInfo("Modification annulée.");
        }

        InputHelper.pause();
    }

    private void archiverComplement() {
        InputHelper.clearConsole();
        InputHelper.afficherSeparateur("ARCHIVER UN COMPLÉMENT");

        int id = InputHelper.lireEntierPositif("ID du complément à archiver : ");

        Complement complement = complementService.findById(id);
        if (complement == null) {
            InputHelper.pause();
            return;
        }

        System.out.println("\n📋 COMPLÉMENT À ARCHIVER :");
        System.out.println(complement.toDetailedString());

        if (complement.isArchive()) {
            InputHelper.afficherInfo("Ce complément est déjà archivé.");
            InputHelper.pause();
            return;
        }

        if (InputHelper.confirmer("\nConfirmer l'archivage ?")) {
            complementService.archive(id);
        } else {
            InputHelper.afficherInfo("Archivage annulé.");
        }

        InputHelper.pause();
    }
}