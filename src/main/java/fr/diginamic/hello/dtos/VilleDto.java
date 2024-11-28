package fr.diginamic.hello.dtos;

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

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }
}
