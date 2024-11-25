package fr.diginamic.hello.daos;

import fr.diginamic.hello.models.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VilleDao  {

    @PersistenceContext
    private EntityManager em;

    public List<Ville> findAll() {
        return em.createQuery("SELECT v FROM Ville v", Ville.class).getResultList();
    }

    public Ville findById(int id) {
        return em.find(Ville.class, id);
    }

    public Ville findByName(String nom) {
        return em.createQuery("SELECT v FROM Ville v WHERE v.nom = :nom", Ville.class)
                .setParameter("nom", nom)
                .getSingleResult();
    }

    public Ville save(Ville ville) {
        em.persist(ville);
        return ville;
    }

    public Ville update(Ville ville) {
        return em.merge(ville);
    }

    public void deleteById(int id) {
        Ville ville = findById(id);
        if (ville != null) {
            em.remove(ville);
        }
    }
}
