package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.dtos.VilleDto;
import fr.diginamic.hello.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {


    @Autowired
    private VilleService villeService;


    @GetMapping
    public Page<VilleDto> getAllVilles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return villeService.getAllVilles(page, size);
    }

    @GetMapping("/{id}")
    public VilleDto getVilleById(@PathVariable int id) {
        return villeService.getVilleById(id);
    }

    @GetMapping("/by-name")
    public List<VilleDto> getVillesByName(@RequestParam String nom) {
        return villeService.getVillesByName(nom);
    }

    @GetMapping("/by-population")
    public List<VilleDto> getVillesByPopulation(@RequestParam int min, @RequestParam(required = false) Integer max) {
        return villeService.getVillesByPopulation(min, max);
    }

    @GetMapping("/by-departement")
    public List<VilleDto> getVillesByDepartement(@RequestParam int departementId) {
        return villeService.getVillesByDepartement(departementId);
    }

    @GetMapping("/top-by-departement")
    public List<VilleDto> getTopVillesByDepartement(@RequestParam int departementId, @RequestParam int n) {
        return villeService.getTopVillesByDepartement(departementId, n);
    }

    @PostMapping
    public VilleDto addVille(@RequestBody VilleDto villeDto) {
        return villeService.addVille(villeDto);
    }

    @PutMapping("/{id}")
    public VilleDto updateVille(@PathVariable int id, @RequestBody VilleDto villeDto) {
        return villeService.updateVille(id, villeDto);
    }

    @DeleteMapping("/{id}")
    public String deleteVille(@PathVariable int id) {
        villeService.deleteVille(id);
        return "Ville supprimée avec succès";
    }
}
