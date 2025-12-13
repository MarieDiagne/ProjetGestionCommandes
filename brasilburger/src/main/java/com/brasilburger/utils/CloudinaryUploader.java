package com.brasilburger.utils;

import java.io.File;
import java.util.Map;


public class CloudinaryUploader {
    
    
    private static final String CLOUD_NAME = "YOUR_CLOUD_NAME";
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_SECRET = "YOUR_API_SECRET";
    
   
    static {
        /*
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        cloudinary = new com.cloudinary.Cloudinary(config);
        */
    }
    
 
    public static String uploadImage(String cheminFichier, String dossier) {
        try {
            System.out.println("📤 Upload de l'image en cours...");
            
     
            
            // Version temporaire sans Cloudinary (pour tester)
            System.out.println("⚠️  Cloudinary non configuré. Utilisation d'une URL de test.");
            return "https://res.cloudinary.com/demo/image/upload/brasilburger/" + dossier + "/test.jpg";
            
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'upload : " + e.getMessage());
            return null;
        }
    }
    
    public static String uploadBurgerImage(String cheminFichier) {
        return uploadImage(cheminFichier, "burgers");
    }
    

    public static String uploadMenuImage(String cheminFichier) {
        return uploadImage(cheminFichier, "menus");
    }
    
  
    public static String uploadComplementImage(String cheminFichier) {
        return uploadImage(cheminFichier, "complements");
    }
    
 
    public static boolean isCloudinaryUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        return url.contains("cloudinary.com") || url.contains("res.cloudinary");
    }
    
 
    public static String extractPublicId(String cloudinaryUrl) {
        if (!isCloudinaryUrl(cloudinaryUrl)) {
            return null;
        }
        
        try {
            
            String[] parts = cloudinaryUrl.split("/upload/");
            if (parts.length < 2) {
                return null;
            }
            
            String afterUpload = parts[1];
            
            if (afterUpload.matches("v\\d+/.*")) {
                afterUpload = afterUpload.substring(afterUpload.indexOf('/') + 1);
            }
            
            // Enlever l'extension
            int dotIndex = afterUpload.lastIndexOf('.');
            if (dotIndex > 0) {
                afterUpload = afterUpload.substring(0, dotIndex);
            }
            
            return afterUpload;
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'extraction du public_id : " + e.getMessage());
            return null;
        }
    }
    
   
    public static boolean deleteImage(String cloudinaryUrl) {
        try {
            String publicId = extractPublicId(cloudinaryUrl);
            if (publicId == null) {
                System.err.println("❌ Impossible d'extraire le public_id de l'URL");
                return false;
            }
            
            System.out.println("🗑️  Suppression de l'image : " + publicId);
            

            
            
            System.out.println("⚠️  Cloudinary non configuré. Suppression simulée.");
            return true;
            
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la suppression : " + e.getMessage());
            return false;
        }
    }
    

    public static void afficherInstructionsConfiguration() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║   CONFIGURATION CLOUDINARY                    ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║ 1. Créez un compte sur cloudinary.com        ║");
        System.out.println("║ 2. Récupérez vos credentials :               ║");
        System.out.println("║    - Cloud Name                               ║");
        System.out.println("║    - API Key                                  ║");
        System.out.println("║    - API Secret                               ║");
        System.out.println("║ 3. Ajoutez la dépendance Maven :             ║");
        System.out.println("║    <dependency>                               ║");
        System.out.println("║      <groupId>com.cloudinary</groupId>        ║");
        System.out.println("║      <artifactId>cloudinary-http44</artifactId>║");
        System.out.println("║      <version>1.33.0</version>                ║");
        System.out.println("║    </dependency>                              ║");
        System.out.println("║ 4. Configurez les constantes dans ce fichier ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
    }
}