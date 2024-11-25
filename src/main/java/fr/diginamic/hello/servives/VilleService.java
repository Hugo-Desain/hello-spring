package fr.diginamic.hello.servives;
import fr.diginamic.hello.models.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleService {
    public Ville ville;

    List<Ville> villes = new ArrayList<>();

    public List<Ville> getAllVilles() {
        villes.add(new Ville("Paris", 1000000));
        villes.add(new Ville("Lyon", 500000));
        villes.add(new Ville("Marseille", 800000));
        villes.add(new Ville("Bordeaux", 250000));
        villes.add(new Ville("Lille", 300000));
        villes.add(new Ville("Toulouse", 450000));

        return villes;
    }

    public boolean addVille(Ville nouvelleVille) {
        if (villes.stream().anyMatch(ville -> ville.getNom().equalsIgnoreCase(nouvelleVille.getNom()))) {
            return false;
        }
        villes.add(nouvelleVille);
        return true;
    }

    public boolean updateVille(int id, Ville updatedVille) {
        Ville villeToUpdate = villes.stream()
                .filter(ville -> ville.getId() == id)
                .findFirst()
                .orElse(null);

        if (villeToUpdate != null) {
            villeToUpdate.setNom(updatedVille.getNom());
            villeToUpdate.setNbHabitants(updatedVille.getNbHabitants());
            return true;
        }
        return false;
    }

}