package fr.diginamic.hello.services;

import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private DepartementRepository departementRepository;

    public Page<Ville> getAllVilles(int page, int size) {
        return villeRepository.findAll(PageRequest.of(page, size));
    }

    public Ville getVilleById(int id) {
        return villeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ville introuvable"));
    }

    public List<Ville> getVillesByName(String nom) throws FunctionalException {
        List<Ville> villes = villeRepository.findByNomStartingWith(nom);
        if (villes.isEmpty()) {
            throw new FunctionalException("Aucune ville dont le nom commence par " + nom + " n’a été trouvée.");
        }
        return villes;
    }

    public List<Ville> getVillesByPopulation(int min, Integer max) throws FunctionalException {
        List<Ville> villes;
        if (max == null) {
            villes = villeRepository.findByNbHabitantsGreaterThan(min);
            if (villes.isEmpty()) {
                throw new FunctionalException("Aucune ville n’a une population supérieure à " + min);
            }
        } else {
            villes = villeRepository.findByNbHabitantsBetween(min, max);
            if (villes.isEmpty()) {
                throw new FunctionalException("Aucune ville n’a une population comprise entre " + min + " et " + max);
            }
        }
        return villes;
    }

    public List<Ville> getVillesByDepartement(int departementId) {
        return villeRepository.findByDepartementId(departementId);
    }

    public List<Ville> getVillesByDepartementCode(String codeDepartement) throws FunctionalException {
        if (codeDepartement == null || codeDepartement.length() != 2) {
            throw new FunctionalException("Le code département doit contenir exactement 2 caractères.");
        }
        List<Ville> villes = villeRepository.findByDepartementCode(codeDepartement);
        if (villes.isEmpty()) {
            throw new FunctionalException("Aucune ville trouvée pour le département avec le code : " + codeDepartement);
        }
        return villes;
    }

    public List<Ville> getTopVillesByDepartement(int departementId, int n) {
        return villeRepository.findTopByDepartementOrderByPopulation(departementId, PageRequest.of(0, n));
    }

    public Ville addVille(Ville ville) throws FunctionalException {
        if (ville.getNbHabitants() < 10) {
            throw new FunctionalException("Une ville doit avoir au moins 10 habitants");
        }
        if (ville.getNom() == null || ville.getNom().length() < 2) {
            throw new FunctionalException("Le nom de la ville doit contenir au moins 2 lettres");
        }
        Departement departement = departementRepository.findByCode(ville.getDepartement().getCode());
        if (departement == null) {
            throw new FunctionalException("Le département spécifié n'existe pas.");
        }
        ville.setDepartement(departement);
        return villeRepository.save(ville);
    }

    public Ville updateVille(int id, Ville ville) {
        Ville existingVille = villeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ville introuvable"));
        existingVille.setNom(ville.getNom());
        existingVille.setNbHabitants(ville.getNbHabitants());
        existingVille.setDepartement(ville.getDepartement());
        return villeRepository.save(existingVille);
    }

    public void deleteVille(int id) {
        villeRepository.deleteById(id);
    }
}
