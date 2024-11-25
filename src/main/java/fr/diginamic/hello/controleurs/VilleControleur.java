package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.servives.VilleService;
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
    public List<Ville> getAllVilles() {
        return villeService.getAllVilles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable int id) {
        return villeService.getVilleById(id);
    }

    @PostMapping
    public ResponseEntity<String> addVille(@Valid @RequestBody Ville newVille) {
        return villeService.addVille(newVille);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVille(@PathVariable int id, @Valid @RequestBody Ville updatedVille) {
        return villeService.updateVille(id, updatedVille);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable int id) {
        return villeService.deleteVille(id);
    }


}