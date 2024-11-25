package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.services.VilleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<Ville> getVilles() {
        return villeService.extractVilles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
        Ville ville = villeService.extractVille(id);
        return ResponseEntity.ok(ville);
    }

    @GetMapping("/search")
    public ResponseEntity<Ville> getVilleByName(@RequestParam String nom) {
        Ville ville = villeService.extractVille(nom);
        return ville != null ? ResponseEntity.ok(ville) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Ville addVille(@RequestBody Ville newVille) {
        return villeService.insertVille(newVille);
    }

    @PutMapping("/{id}")
    public List<Ville> updateVille(@PathVariable int id, @RequestBody Ville updatedVille) {
        return villeService.modifierVille(id, updatedVille);
    }

    @DeleteMapping("/{id}")
    public List<Ville> deleteVille(@PathVariable int id) {
        return villeService.supprimerVille(id);
    }


}