package fr.diginamic.hello.tests;

import fr.diginamic.hello.dtos.VilleDto;
import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.services.VilleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class VilleServiceTest {

    @Autowired
    private VilleService villeService;

    @Test
    void testAddVille_Success() throws FunctionalException {
        VilleDto villeDto = new VilleDto("Lyon", 500000, "01", "Ain");
        VilleDto savedVille = villeService.addVille(villeDto);
        assertNotNull(savedVille);
        assertEquals("Lyon", savedVille.getNomVille());
    }

    @Test
    void testAddVille_DuplicateNameInSameDepartement() {
        VilleDto villeDto = new VilleDto("Bourg-en-Bresse", 40000, "01", "Ain");
        assertThrows(FunctionalException.class, () -> villeService.addVille(villeDto));
    }
}
