package fr.diginamic.hello.repositories;

import fr.diginamic.hello.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

    Departement findByCode(String code);
}
