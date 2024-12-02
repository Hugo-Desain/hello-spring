package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.services.VilleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

    @Autowired
    private VilleService villeService;

    @GetMapping
    public Page<Ville> getAllVilles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return villeService.getAllVilles(page, size);
    }

    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable int id) {
        return villeService.getVilleById(id);
    }

    @GetMapping("/by-name")
    public List<Ville> getVillesByName(@RequestParam String nom) throws FunctionalException {
        return villeService.getVillesByName(nom);
    }

    @GetMapping("/by-population")
    public List<Ville> getVillesByPopulation(@RequestParam int min, @RequestParam(required = false) Integer max) throws FunctionalException {
        return villeService.getVillesByPopulation(min, max);
    }

    @GetMapping("/by-departement")
    public List<Ville> getVillesByDepartement(@RequestParam int departementId) {
        return villeService.getVillesByDepartement(departementId);
    }

    @GetMapping("/top-by-departement")
    public List<Ville> getTopVillesByDepartement(@RequestParam int departementId, @RequestParam int n) {
        return villeService.getTopVillesByDepartement(departementId, n);
    }

    @PostMapping
    public Ville addVille(@RequestBody Ville ville) throws FunctionalException {
        return villeService.addVille(ville);
    }

    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable int id, @RequestBody Ville ville) {
        return villeService.updateVille(id, ville);
    }

    @DeleteMapping("/{id}")
    public String deleteVille(@PathVariable int id) {
        villeService.deleteVille(id);
        return "Ville supprimée avec succès";
    }

    @GetMapping("/export-csv")
    public void exportVillesToCsv(@RequestParam int minPopulation, HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment;filename=villes.csv");

            PrintWriter writer = response.getWriter();
            writer.println("Nom de la Ville,Nombre d'Habitants,Code Département,Nom Département");

            List<Ville> villes = villeService.getVillesByPopulation(minPopulation, null);

            for (Ville ville : villes) {
                writer.printf("%s,%d,%s,%s%n",
                        ville.getNom(),
                        ville.getNbHabitants(),
                        ville.getDepartement().getCode(),
                        ville.getDepartement().getNom());
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'exportation CSV", e);
        }
    }
}
