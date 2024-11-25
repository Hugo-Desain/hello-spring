package fr.diginamic.hello.servives;

import fr.diginamic.hello.models.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleService {
    public Ville ville;

    public List<Ville> getAllCities() {
        List<Ville> villes = new ArrayList<>();
        villes.add(new Ville("Paris", 1000000));
        villes.add(new Ville("Lyon", 500000));
        villes.add(new Ville("Marseille", 800000));
        villes.add(new Ville("Bordeaux", 250000));
        villes.add(new Ville("Lille", 300000));
        villes.add(new Ville("Toulouse", 450000));

        return villes;
    }
}