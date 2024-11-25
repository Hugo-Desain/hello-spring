package fr.diginamic.hello.servives;
import fr.diginamic.hello.models.Ville;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleService {

    private List<Ville> villes = new ArrayList<>();

    public VilleService() {
        villes.add(new Ville(1, "Paris", 2200000));
        villes.add(new Ville(2, "Lyon", 500000));
        villes.add(new Ville(3, "Marseille", 850000));
    }

    public List<Ville> getAllVilles() {
        return villes;
    }

    public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
        return villes.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .map(ville -> ResponseEntity.ok(ville))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<String> addVille(@Valid @RequestBody Ville nouvelleVille) {

        if (villes.stream().anyMatch(ville -> ville.getNom().equalsIgnoreCase(nouvelleVille.getNom()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ville existe déjà");
        }
        villes.add(nouvelleVille);
        return ResponseEntity.status(HttpStatus.OK).body("Ville insérée avec succès");

    }

    public ResponseEntity<String> updateVille(@PathVariable int id, @Valid @RequestBody Ville updatedVille) {
        Ville villeToUpdate = villes.stream()
                .filter(ville -> ville.getId() == id)
                .findFirst()
                .orElse(null);

        if (villeToUpdate != null) {
            villeToUpdate.setNom(updatedVille.getNom());
            villeToUpdate.setNbHabitants(updatedVille.getNbHabitants());
            return ResponseEntity.status(HttpStatus.OK).body("Ville mise à jour avec succès");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ville avec l'id " + id + " non trouvée");

    }

    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        boolean removed = villes.removeIf(v -> v.getId() == id);
        if (removed) {
            return ResponseEntity.ok("Ville supprimée avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ville introuvable.");
        }
    }

}