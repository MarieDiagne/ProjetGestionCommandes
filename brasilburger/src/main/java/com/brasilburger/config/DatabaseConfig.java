package com.brasilburger.config;

public class DatabaseConfig {

  
    public static final String DB_URL = "jdbc:postgresql://ep-icy-sound-a44jlj19-pooler.us-east-1.aws.neon.tech/neondb?sslmode=require&channel_binding=require";

    
    public static final String DB_USER = "neondb_owner";

    public static final String DB_PASSWORD = "npg_Rf6xZiG9gEkA";

    
    public static final String DB_DRIVER = "org.postgresql.Driver";

    

    
    public static final int MAX_POOL_SIZE = 10;

    
    public static final int CONNECTION_TIMEOUT = 30;

    

   
    public static final String DEFAULT_SCHEMA = "public";

    

    public static final String TABLE_BURGER = "burger";
    public static final String TABLE_MENU = "menu";
    public static final String TABLE_COMPLEMENT = "complement";
    public static final String TABLE_COMPOSITION = "composition";
    public static final String TABLE_UTILISATEUR = "utilisateur";
    public static final String TABLE_CLIENT = "client";
    public static final String TABLE_GESTIONNAIRE = "gestionnaire";
    public static final String TABLE_COMMANDE = "commande";
    public static final String TABLE_LIGNE_COMMANDE = "ligne_commande";
    public static final String TABLE_PAIEMENT = "paiement";
    public static final String TABLE_ZONE = "zone";

    

    public static final String ENUM_TYPE_COMPLEMENT = "type_complement_enum";
    public static final String ENUM_ROLE = "role_enum";
    public static final String ENUM_TYPE_COMMANDE = "type_commande_enum";
    public static final String ENUM_ETAT_COMMANDE = "etat_commande_enum";
    public static final String ENUM_TYPE_PRODUIT = "type_produit_enum";
    public static final String ENUM_MODE_PAIEMENT = "mode_paiement_enum";

    
    public static boolean isConfigurationComplete() {
        return !DB_URL.contains("YOUR_") &&
                !DB_USER.contains("YOUR_") &&
                !DB_PASSWORD.contains("YOUR_");
    }

    
    public static void afficherConfiguration() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║   CONFIGURATION BASE DE DONNÉES              ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║ URL      : " + maskUrl(DB_URL));
        System.out.println("║ User     : " + DB_USER);
        System.out.println("║ Password : " + maskPassword(DB_PASSWORD));
        System.out.println("║ Driver   : " + DB_DRIVER);
        System.out.println("║ Schema   : " + DEFAULT_SCHEMA);
        System.out.println("╚═══════════════════════════════════════════════╝");

        if (!isConfigurationComplete()) {
            System.out.println("\n⚠️  ATTENTION : La configuration n'est pas complète !");
            System.out.println("   Veuillez modifier les valeurs dans DatabaseConfig.java");
        }
    }

    
    private static String maskUrl(String url) {
        if (url.contains("YOUR_")) {
            return "❌ Non configurée";
        }
        
        if (url.contains("password=")) {
            int start = url.indexOf("password=") + 9;
            int end = url.indexOf("&", start);
            if (end == -1)
                end = url.length();
            String masked = url.substring(0, start) + "****" + url.substring(end);
            return masked;
        }
        return url;
    }

   
    private static String maskPassword(String password) {
        if (password.contains("YOUR_")) {
            return "❌ Non configuré";
        }
        return "****";
    }

  
    public static void afficherInstructionsNeon() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║   CONFIGURATION NEON POSTGRESQL               ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("║ 1. Connectez-vous sur neon.tech              ║");
        System.out.println("║ 2. Créez un nouveau projet                    ║");
        System.out.println("║ 3. Récupérez les credentials de connexion :  ║");
        System.out.println("║    - Host (ex: ep-cool-sound-123.neon.tech)  ║");
        System.out.println("║    - Database name (ex: brasilburger)         ║");
        System.out.println("║    - Username                                 ║");
        System.out.println("║    - Password                                 ║");
        System.out.println("║ 4. Exécutez le script SQL fourni             ║");
        System.out.println("║ 5. Modifiez les constantes dans              ║");
        System.out.println("║    DatabaseConfig.java                        ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
    }

    
    public static String generateConnectionUrl(String host, int port, String database, boolean sslMode) {
        StringBuilder url = new StringBuilder("jdbc:postgresql://");
        url.append(host);
        url.append(":").append(port);
        url.append("/").append(database);
        if (sslMode) {
            url.append("?sslmode=require");
        }
        return url.toString();
    }
}