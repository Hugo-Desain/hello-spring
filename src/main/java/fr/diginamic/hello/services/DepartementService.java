package fr.diginamic.hello.services;

import fr.diginamic.hello.models.Departement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Departement> getAllDepartements() {
        return entityManager.createQuery("SELECT d FROM Departement d", Departement.class).getResultList();
    }

    public Departement getDepartementById(int id) {
        return entityManager.find(Departement.class, id);
    }

    @Transactional
    public Departement addDepartement(Departement departement) {
        entityManager.persist(departement);
        return departement;
    }

    @Transactional
    public void deleteDepartement(int id) {
        Departement departement = getDepartementById(id);
        if (departement != null) {
            entityManager.remove(departement);
        }
    }
}
