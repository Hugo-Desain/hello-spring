package fr.diginamic.hello.dtos;

public class DepartementDto {

    private String codeDepartement;
    private String nomDepartement;
    private int nbHabitants;

    public DepartementDto(String codeDepartement, String nomDepartement, int nbHabitants) {
        this.codeDepartement = codeDepartement;
        this.nomDepartement = nomDepartement;
        this.nbHabitants = nbHabitants;
    }

    public DepartementDto() {
    }

    // Getters et setters
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

    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}
