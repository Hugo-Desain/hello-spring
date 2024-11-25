package fr.diginamic.hello.services;

import fr.diginamic.hello.daos.VilleDao;
import fr.diginamic.hello.models.Ville;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    private VilleDao villeDao;

    public List<Ville> extractVilles() {
        return villeDao.findAll();
    }

    public Ville extractVille(int idVille) {
        return villeDao.findById(idVille);
    }

    public Ville extractVille(String nom) {
        return villeDao.findByName(nom);
    }

    @Transactional
    public Ville insertVille(Ville nouvelleVille) {
        return villeDao.save(nouvelleVille);
    }

    public List<Ville> modifierVille(int idVille, Ville villeModifiee) {
        Ville existingVille = villeDao.findById(idVille);
        if (existingVille != null) {
            existingVille.setNom(villeModifiee.getNom());
            existingVille.setNbHabitants(villeModifiee.getNbHabitants());
            villeDao.update(existingVille);
        }
        return villeDao.findAll();
    }

    public List<Ville> supprimerVille(int idVille) {
        villeDao.deleteById(idVille);
        return villeDao.findAll();
    }
}
