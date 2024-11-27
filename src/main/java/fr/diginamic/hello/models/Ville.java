package fr.diginamic.hello.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ville")
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(name = "nb_habitants", nullable = false)
    private int nbHabitants;

    @Column(name = "ID_REGION", nullable = true)
    private Integer regionId;

    @ManyToOne
    @JoinColumn(name = "id_dept", nullable = false)
    @JsonBackReference
    private Departement departement;

    public Ville() {}

    public Ville(String nom, int nbHabitants, Departement departement) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
        this.departement = departement;
    }

    /**
     * Getter
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter
     *
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     *
     * @return nbHabitants
     */
    public int getNbHabitants() {
        return nbHabitants;
    }

    /**
     * Setter
     *
     * @param nbHabitants nbHabitants
     */
    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    /**
     * Getter
     *
     * @return departement
     */
    public Departement getDepartement() {
        return departement;
    }

    /**
     * Setter
     *
     * @param departement departement
     */
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    /**
     * Getter
     *
     * @return regionId
     */
    public Integer getRegionId() {
        return regionId;
    }

    /**
     * Setter
     *
     * @param regionId regionId
     */
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
