package fr.diginamic.hello.services;

import fr.diginamic.hello.dtos.VilleDto;
import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import fr.diginamic.hello.services.VilleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")

@SpringBootTest
class VilleServiceTest {

@Autowired
private VilleService villeService;

//@MockitoBean
//private VilleRepository villeRepository;
//
//@MockitoBean
//private DepartementRepository departementRepository;
//
//    @Test
//    void testGetAllVilles() {
//        Ville ville = new Ville("Angers", 142000);
//        ville.setDepartement(new Departement("49", "Maine-et-Loire"));
//
//        Mockito.when(villeRepository.findAll()).thenReturn(List.of(ville));
//
//        List<Ville> villes = villeService.getAllVilles(0, 10).getContent();
//        assertFalse(villes.isEmpty());
//        assertEquals("Angers", villes.get(0).getNom());
//    }

//    @Test
//    void testAddVille() {
//        Ville ville = new Ville("Angers", 151229, new Departement("49", "Maine-et-Loire"));
//        Mockito.when(villeRepository.save(ville)).thenReturn(ville);
//
//        Ville savedVille = villeService.addVille(ville);
//        assertNotNull(savedVille);
//        assertEquals("Angers", savedVille.getNom());
//        assertEquals(151229, savedVille.getNbHabitants());
//        assertEquals("49", savedVille.getDepartement().getCode());
//    }

    @Test
    void getVille() {
        Ville v = villeService.getVilleById(1);
        assertNotNull(v);
        assertEquals(v.getNom(), "Angers");
    }

}