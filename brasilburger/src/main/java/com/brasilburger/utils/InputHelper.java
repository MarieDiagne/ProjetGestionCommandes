package com.brasilburger.utils;

import java.math.BigDecimal;
import java.util.Scanner;


public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

  
    public static String lireString() {
        try {
            return scanner.nextLine().trim();
        } catch (Exception e) {
            scanner.nextLine(); // Vider le buffer
            return "";
        }
    }

   
    public static String lireStringNonVide(String message) {
        String input;
        do {
            System.out.print(message);
            input = lireString();
            if (input.isEmpty()) {
                System.out.println("❌ Cette valeur ne peut pas être vide. Veuillez réessayer.");
            }
        } while (input.isEmpty());
        return input;
    }

    
    public static int lireEntier(String message) {
        while (true) {
            System.out.print(message);
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Veuillez entrer un nombre entier valide.");
            }
        }
    }

  
    public static int lireEntier(String message, int min, int max) {
        while (true) {
            int value = lireEntier(message);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("❌ Veuillez entrer un nombre entre " + min + " et " + max + ".");
        }
    }

    
    public static int lireEntierPositif(String message) {
        while (true) {
            int value = lireEntier(message);
            if (value > 0) {
                return value;
            }
            System.out.println("❌ Veuillez entrer un nombre positif (> 0).");
        }
    }


    public static BigDecimal lireBigDecimal(String message) {
        while (true) {
            System.out.print(message);
            try {
                String input = scanner.nextLine().trim().replace(',', '.');
                BigDecimal value = new BigDecimal(input);
                if (value.compareTo(BigDecimal.ZERO) > 0) {
                    return value.setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                System.out.println("❌ Le montant doit être supérieur à 0.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Veuillez entrer un montant valide (ex: 500 ou 500.50).");
            }
        }
    }

 
    public static BigDecimal lirePrix(String message) {
        return lireBigDecimal(message);
    }

 
    public static String lireUrl(String message, boolean obligatoire) {
        while (true) {
            System.out.print(message);
            String url = lireString();

            
            if (url.isEmpty() && !obligatoire) {
                return null;
            }

            
            if (url.isEmpty() && obligatoire) {
                System.out.println("❌ L'URL est obligatoire.");
                continue;
            }

            
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                System.out.println("⚠️  L'URL doit commencer par http:// ou https://");
                System.out.print("Continuer quand même ? (o/n) : ");
                String reponse = lireString();
                if (!reponse.equalsIgnoreCase("o") && !reponse.equalsIgnoreCase("oui")) {
                    continue;
                }
            }

            return url;
        }
    }

   
    public static boolean confirmer(String message) {
        System.out.print(message + " (o/n) : ");
        String reponse = lireString().toLowerCase();
        return reponse.equals("o") || reponse.equals("oui");
    }

  
    public static void pause() {
        System.out.print("\n📌 Appuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }

    
    public static void pause(String message) {
        System.out.print("\n" + message);
        scanner.nextLine();
    }

 
    public static void viderBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

   
    public static int lireChoix(String message, int nbOptions) {
        return lireEntier(message, 1, nbOptions);
    }

    
    public static void afficherSeparateur() {
        System.out.println("\n═══════════════════════════════════════════════════");
    }

  
    public static void afficherSeparateur(String titre) {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("  " + titre);
        System.out.println("═══════════════════════════════════════════════════");
    }

   
    public static void afficherSucces(String message) {
        System.out.println("\n✅ " + message);
    }


    public static void afficherErreur(String message) {
        System.out.println("\n❌ " + message);
    }

   
    public static void afficherInfo(String message) {
        System.out.println("\n📌 " + message);
    }

   
    public static void afficherAvertissement(String message) {
        System.out.println("\n⚠️  " + message);
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}