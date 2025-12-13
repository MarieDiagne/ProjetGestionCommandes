package com.brasilburger.config;


public class CloudinaryConfig {

    
    public static final String CLOUD_NAME = "YOUR_CLOUD_NAME";

   
    public static final String API_KEY = "YOUR_API_KEY";


    public static final String API_SECRET = "YOUR_API_SECRET";

    
    public static final String ROOT_FOLDER = "brasilburger";

   
    public static final String BURGER_FOLDER = ROOT_FOLDER + "/burgers";

    
    public static final String MENU_FOLDER = ROOT_FOLDER + "/menus";

    
    public static final String COMPLEMENT_FOLDER = ROOT_FOLDER + "/complements";

  
    public static final int MAX_WIDTH = 800;

    
    public static final int MAX_HEIGHT = 600;

  
    public static final int QUALITY = 85;

    

    public static final String[] ACCEPTED_FORMATS = { "jpg", "jpeg", "png", "webp" };

   
    public static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    // ========== Méthodes utilitaires ==========

  
    public static boolean isConfigurationComplete() {
        return !CLOUD_NAME.contains("YOUR_") &&
                !API_KEY.contains("YOUR_") &&
                !API_SECRET.contains("YOUR_");
    }


    public static void afficherConfiguration() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║   CONFIGURATION CLOUDINARY                    ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║ Cloud Name : " + maskValue(CLOUD_NAME));
        System.out.println("║ API Key    : " + maskValue(API_KEY));
        System.out.println("║ API Secret : " + maskSecret(API_SECRET));
        System.out.println("║ Root Folder: " + ROOT_FOLDER);
        System.out.println("╚═══════════════════════════════════════════════╝");

        if (!isConfigurationComplete()) {
            System.out.println("\n⚠️  ATTENTION : Cloudinary n'est pas configuré !");
            System.out.println("   Les uploads d'images ne fonctionneront pas.");
            System.out.println("   Vous pouvez entrer des URLs manuellement.");
        }
    }

    
    private static String maskValue(String value) {
        if (value.contains("YOUR_")) {
            return "❌ Non configuré";
        }
        if (value.length() <= 4) {
            return "****";
        }
        return value.substring(0, 4) + "****";
    }

    private static String maskSecret(String secret) {
        if (secret.contains("YOUR_")) {
            return "❌ Non configuré";
        }
        return "****";
    }

    public static String getCloudinaryBaseUrl() {
        return String.format("https://res.cloudinary.com/%s/image/upload", CLOUD_NAME);
    }

    public static boolean isFormatAccepted(String filename) {
        if (filename == null || filename.isEmpty()) {
            return false;
        }

        String extension = "";
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0) {
            extension = filename.substring(lastDot + 1).toLowerCase();
        }

        for (String format : ACCEPTED_FORMATS) {
            if (format.equals(extension)) {
                return true;
            }
        }

        return false;
    }

   
    public static void afficherInstructions() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║   CONFIGURATION CLOUDINARY                    ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║ 1. Créez un compte gratuit sur :             ║");
        System.out.println("║    https://cloudinary.com                     ║");
        System.out.println("║                                               ║");
        System.out.println("║ 2. Dans le Dashboard, récupérez :            ║");
        System.out.println("║    - Cloud Name                               ║");
        System.out.println("║    - API Key                                  ║");
        System.out.println("║    - API Secret                               ║");
        System.out.println("║                                               ║");
        System.out.println("║ 3. Ajoutez la dépendance Maven :             ║");
        System.out.println("║    <dependency>                               ║");
        System.out.println("║      <groupId>com.cloudinary</groupId>        ║");
        System.out.println("║      <artifactId>cloudinary-http44</artifactId>║");
        System.out.println("║      <version>1.33.0</version>                ║");
        System.out.println("║    </dependency>                              ║");
        System.out.println("║                                               ║");
        System.out.println("║ 4. Modifiez les constantes dans :            ║");
        System.out.println("║    CloudinaryConfig.java                      ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
    }

  
    public static String buildTransformation(int width, int height, String format) {
        return String.format("w_%d,h_%d,c_limit,q_%d,f_%s",
                width, height, QUALITY, format);
    }


    public static String buildImageUrl(String publicId, int width, int height) {
        String transformation = buildTransformation(width, height, "auto");
        return String.format("%s/%s/%s", getCloudinaryBaseUrl(), transformation, publicId);
    }
}