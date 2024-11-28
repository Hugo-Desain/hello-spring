package fr.diginamic.hello.dtos;

import org.springframework.web.client.RestTemplate;

public class VilleDto {

    private String nomVille;
    private int nbHabitants;
    private String codeDepartement;
    private String nomDepartement;

    public VilleDto(String nomVille, int nbHabitants, String codeDepartement, String nomDepartement) {
        this.nomVille = nomVille;
        this.nbHabitants = nbHabitants;
        this.codeDepartement = codeDepartement;
        this.nomDepartement = nomDepartement;
    }

    public VilleDto() {

    }

    // Getters et setters
    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getNomDepartementApi(String codeDepartement) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "https://geo.api.gouv.fr/departements/" + codeDepartement + "?fields=nom,code,codeRegion";
            var response = restTemplate.getForObject(apiUrl, ApiResponse.class);
            return response != null ? response.getNom() : "Nom inconnu";
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du nom du département via l'API", e);
        }
    }

    private static class ApiResponse {
        private String nom;

        public String getNom() {
            return nom;
        }
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }
}
