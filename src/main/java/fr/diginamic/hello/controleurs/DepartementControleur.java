package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.services.DepartementService;
import fr.diginamic.hello.services.VilleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    @Autowired
    private DepartementService departementService;

    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<Departement> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable int id) {
        Departement departement = departementService.getDepartementById(id);
        if (departement != null) {
            return ResponseEntity.ok(departement);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Departement> addDepartement(@RequestBody Departement departement) {
        return ResponseEntity.ok(departementService.addDepartement(departement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable int id) {
        departementService.deleteDepartement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/villes/top")
    public ResponseEntity<List<Ville>> getTopNVilles(@PathVariable int id, @RequestParam int n) {
        Departement departement = departementService.getDepartementById(id);
        if (departement == null) {
            return ResponseEntity.notFound().build();
        }

        List<Ville> topVilles = villeService.extractVilles().stream()
                .filter(v -> v.getDepartement() != null && v.getDepartement().getId() == id)
                .sorted(Comparator.comparingInt(Ville::getNbHabitants).reversed())
                .limit(n)
                .collect(Collectors.toList());

        return ResponseEntity.ok(topVilles);
    }

    @GetMapping("/{id}/villes/population")
    public ResponseEntity<List<Ville>> getVillesByPopulationRange(
            @PathVariable int id,
            @RequestParam int min,
            @RequestParam int max
    ) {
        Departement departement = departementService.getDepartementById(id);
        if (departement == null) {
            return ResponseEntity.notFound().build();
        }

        List<Ville> villesInRange = villeService.extractVilles().stream()
                .filter(v -> v.getDepartement() != null && v.getDepartement().getId() == id)
                .filter(v -> v.getNbHabitants() >= min && v.getNbHabitants() <= max)
                .collect(Collectors.toList());

        return ResponseEntity.ok(villesInRange);
    }
}
