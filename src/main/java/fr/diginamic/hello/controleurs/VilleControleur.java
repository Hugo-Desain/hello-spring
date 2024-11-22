package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.models.Ville;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")

public class VilleControleur {

    List<Ville> villes = new ArrayList<>();

    public VilleControleur() {
        villes.add(new Ville("Paris", 2161000));
        villes.add(new Ville("Marseille", 861635));
        villes.add(new Ville("Lyon", 513275));
        villes.add(new Ville("Toulouse", 493465));
        villes.add(new Ville("Nice", 343895));
    }

    @GetMapping
    public List<Ville> getVilles() {
        return villes;
    }

    @PostMapping
    public ResponseEntity<String> addVille(@RequestBody Ville nouvelleVille){

        if (villes.stream().anyMatch(ville -> ville.getNom().equals(nouvelleVille.getNom()))){
            return ResponseEntity.badRequest().body("La ville existe déjà");
        } else {
            villes.add(nouvelleVille);
            return ResponseEntity.ok("Ville insérée avec succès");
        }
    }
}
