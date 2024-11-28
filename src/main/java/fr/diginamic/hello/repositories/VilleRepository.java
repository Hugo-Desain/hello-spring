package fr.diginamic.hello.repositories;

import fr.diginamic.hello.models.Ville;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, Integer> {

    List<Ville> findByDepartementId(int departementId);

    List<Ville> findByDepartementCode(String departementCode);

    List<Ville> findByNomStartingWith(String nom);

    List<Ville> findByNbHabitantsGreaterThan(int min);

    List<Ville> findByNbHabitantsBetween(int min, int max);

    @Query("SELECT v FROM Ville v WHERE v.departement.id = :departementId AND v.nbHabitants > :min")
    List<Ville> findByDepartementAndPopulationGreaterThan(int departementId, int min);

    @Query("SELECT v FROM Ville v WHERE v.departement.id = :departementId AND v.nbHabitants BETWEEN :min AND :max")
    List<Ville> findByDepartementAndPopulationBetween(int departementId, int min, int max);

    @Query("SELECT v FROM Ville v WHERE v.departement.id = :departementId ORDER BY v.nbHabitants DESC")
    List<Ville> findTopByDepartementOrderByPopulation(int departementId, Pageable pageable);

}

