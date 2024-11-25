package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.servives.VilleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<Ville> getAllCities() {
        return villeService.getAllVilles();
    }

    @PostMapping
    public ResponseEntity<String> addCity(@Valid @RequestBody Ville newVille) {
        boolean isAdded = villeService.addVille(newVille);

        if (isAdded) {
            return ResponseEntity.status(HttpStatus.OK).body("Ville insérée avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ville existe déjà");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCity(@PathVariable int id, @Valid @RequestBody Ville updatedVille) {
        boolean isUpdated = villeService.updateVille(id, updatedVille);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("Ville mise à jour avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ville avec l'id " + id + " non trouvée");
        }
    }

}