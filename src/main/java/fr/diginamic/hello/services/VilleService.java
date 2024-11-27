package fr.diginamic.hello.services;

import fr.diginamic.hello.models.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VilleService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Ville> extractVilles() {
        return entityManager.createQuery("SELECT v FROM Ville v", Ville.class).getResultList();
    }

    public Ville extractVille(int idVille) {
        return entityManager.find(Ville.class, idVille);
    }

    public Ville extractVille(String nom) {
        return entityManager.createQuery("SELECT v FROM Ville v WHERE v.nom = :nom", Ville.class)
                .setParameter("nom", nom)
                .getSingleResult();
    }

    @Transactional
    public List<Ville> insertVille(Ville ville) {
        entityManager.persist(ville);
        return extractVilles();
    }

    @Transactional
    public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
        Ville ville = extractVille(idVille);
        if (ville != null) {
            ville.setNom(villeModifiee.getNom());
            ville.setNbHabitants(villeModifiee.getNbHabitants());
        }
        return extractVilles();
    }

    public List<Ville> supprimerVille(int idVille) {
        Ville ville = extractVille(idVille);
        if (ville != null) {
            entityManager.remove(ville);
        }
        return extractVilles();
    }
}
