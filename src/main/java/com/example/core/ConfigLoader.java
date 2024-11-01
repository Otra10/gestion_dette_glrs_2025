package com.example.core;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ConfigLoader {

    private static Map<String, Object> config;

    static {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("application.yaml")) {
            if (inputStream != null) {
                config = yaml.load(inputStream);
            } else {
                System.err.println("Le fichier application.yaml est introuvable.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRepositoryType() {
        if (config != null && config.containsKey("app")) {
            Map<String, Object> appConfig = (Map<String, Object>) config.get("app");
            return (String) appConfig.get("repositoryType");
        }
        return null; // Retourne null si config est vide ou si "repositoryType" est introuvable
    }

    public static String getPersistenceUnit() {
        if (config != null && config.containsKey("app")) {
            Map<String, Object> appConfig = (Map<String, Object>) config.get("app");
            return (String) appConfig.get("persistence-unit");
        }
        return null; 
    }
}
