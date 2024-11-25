package fr.diginamic.hello.daos;

import fr.diginamic.hello.models.Departement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartementDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Departement> findAll() {
        return entityManager.createQuery("SELECT d FROM Departement d", Departement.class).getResultList();
    }

    public Departement findById(int id) {
        return entityManager.find(Departement.class, id);
    }

    @Transactional
    public Departement save(Departement departement) {
        entityManager.persist(departement);
        return departement;
    }

    @Transactional
    public void deleteById(int id) {
        Departement departement = findById(id);
        if (departement != null) {
            entityManager.remove(departement);
        }
    }
}
