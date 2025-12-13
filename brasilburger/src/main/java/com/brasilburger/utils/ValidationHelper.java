package com.brasilburger.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class ValidationHelper {

    
    private static final Pattern URL_PATTERN = Pattern.compile(
            "^(https?://)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

  
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

   
    public static boolean validateNotEmpty(String str, String fieldName) {
        if (!isNotEmpty(str)) {
            System.err.println("❌ Erreur : Le champ '" + fieldName + "' ne peut pas être vide.");
            return false;
        }
        return true;
    }

    
    public static boolean isValidPrice(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }

  
    public static boolean validatePrice(BigDecimal price, String fieldName) {
        if (!isValidPrice(price)) {
            System.err.println("❌ Erreur : Le champ '" + fieldName + "' doit être un montant supérieur à 0.");
            return false;
        }
        return true;
    }

   
    public static boolean isValidUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        return URL_PATTERN.matcher(url).matches();
    }

   
    public static boolean isValidCloudinaryUrl(String url) {
        if (!isValidUrl(url)) {
            return false;
        }
        return url.contains("cloudinary.com") || url.contains("res.cloudinary");
    }

    public static boolean validateCloudinaryUrl(String url, String fieldName) {
        if (url == null || url.trim().isEmpty()) {
            return true; // L'image est optionnelle
        }

        if (!isValidUrl(url)) {
            System.err.println("❌ Erreur : Le champ '" + fieldName + "' doit être une URL valide.");
            return false;
        }

        if (!isValidCloudinaryUrl(url)) {
            System.out.println("⚠️  Attention : L'URL ne semble pas être une URL Cloudinary.");
            System.out.println("   Format attendu : https://res.cloudinary.com/...");
            System.out.print("   Continuer quand même ? (o/n) : ");
            String response = InputHelper.lireString();
            return response.equalsIgnoreCase("o") || response.equalsIgnoreCase("oui");
        }

        return true;
    }

   
    public static boolean isValidId(Integer id) {
        return id != null && id > 0;
    }

   
    public static boolean validateId(Integer id, String fieldName) {
        if (!isValidId(id)) {
            System.err.println("❌ Erreur : Le champ '" + fieldName + "' doit être un ID valide (> 0).");
            return false;
        }
        return true;
    }

    public static boolean validateLength(String str, int minLength, int maxLength, String fieldName) {
        if (str == null) {
            System.err.println("❌ Erreur : Le champ '" + fieldName + "' ne peut pas être null.");
            return false;
        }

        int length = str.trim().length();

        if (length < minLength) {
            System.err.println(
                    "❌ Erreur : Le champ '" + fieldName + "' doit contenir au moins " + minLength + " caractères.");
            return false;
        }

        if (length > maxLength) {
            System.err.println(
                    "❌ Erreur : Le champ '" + fieldName + "' ne doit pas dépasser " + maxLength + " caractères.");
            return false;
        }

        return true;
    }

    public static boolean confirmer(String message) {
        System.out.print(message + " (o/n) : ");
        String response = InputHelper.lireString();
        return response.equalsIgnoreCase("o") || response.equalsIgnoreCase("oui");
    }

   
    public static String cleanString(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("\\s+", " ");
    }
}