package fr.diginamic.hello.controleurs;

import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.servives.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleControleur {
    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<Ville> getAllCities() {
        return villeService.getAllCities();
    }
}