package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementControleur {

    @Autowired
    private DepartementService departementService;

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

    @PostMapping
    public ResponseEntity<Departement> addDepartement(@RequestBody Departement departement) {
        return ResponseEntity.ok(departementService.addDepartement(departement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable int id) {
        departementService.deleteDepartement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/villes/top/{n}")
    public ResponseEntity<List<Ville>> getTopNVilles(@PathVariable int id, @PathVariable int n) {
        return ResponseEntity.ok(departementService.getTopNVilles(id, n));
    }

    @GetMapping("/{id}/villes")
    public ResponseEntity<List<Ville>> getVillesByPopulationRange(
            @PathVariable int id,
            @RequestParam int min,
            @RequestParam int max
    ) {
        return ResponseEntity.ok(departementService.getVillesByPopulationRange(id, min, max));
    }
}
