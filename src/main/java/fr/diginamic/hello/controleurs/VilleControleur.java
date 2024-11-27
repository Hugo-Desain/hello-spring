package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.repositories.VilleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleRepository villeRepository;

    @GetMapping
    public List<Ville> getAllVilles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return villeRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable int id) {
        return villeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ville introuvable"));
    }

    @GetMapping("/by-name")
    public List<Ville> getVillesByName(@RequestParam String nom) {
        return villeRepository.findByNomStartingWith(nom);
    }

    @GetMapping("/by-population")
    public List<Ville> getVillesByPopulation(@RequestParam int min, @RequestParam(required = false) Integer max) {
        if (max == null) {
            return villeRepository.findByNbHabitantsGreaterThan(min);
        }
        return villeRepository.findByNbHabitantsBetween(min, max);
    }

    @GetMapping("/by-departement")
    public List<Ville> getVillesByDepartement(@RequestParam int departementId, @RequestParam int min, @RequestParam(required = false) Integer max) {
        if (max == null) {
            return villeRepository.findByDepartementAndPopulationGreaterThan(departementId, min);
        }
        return villeRepository.findByDepartementAndPopulationBetween(departementId, min, max);
    }

    @GetMapping("/top-by-departement")
    public List<Ville> getTopVillesByDepartement(@RequestParam int departementId, @RequestParam int n) {
        return villeRepository.findTopByDepartementOrderByPopulation(departementId, PageRequest.of(0, n));
    }

    @Transactional
    @PostMapping
    public Ville addVille(@RequestBody Ville ville) {
        return villeRepository.save(ville);
    }

    @Transactional
    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable int id, @RequestBody Ville villeDetails) {
        Ville ville = villeRepository.findById(id).orElseThrow(() -> new RuntimeException("Ville introuvable"));
        ville.setNom(villeDetails.getNom());
        ville.setNbHabitants(villeDetails.getNbHabitants());
        ville.setDepartement(villeDetails.getDepartement());
        ville.setRegionId(villeDetails.getRegionId());
        return villeRepository.save(ville);
    }

    @DeleteMapping("/{id}")
    public String deleteVille(@PathVariable int id) {
        villeRepository.deleteById(id);
        return "Ville supprimée avec succès";
    }
}
