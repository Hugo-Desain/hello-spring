package fr.diginamic.hello.controleurs;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.services.VilleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VilleControleurTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VilleService villeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllVilles() throws Exception {
        // Création d'une entité Ville
        Departement departement = new Departement("49", "Maine-et-Loire");
        Ville ville = new Ville("Angers", 151229, departement);

        // Simulation du comportement de la méthode getAllVilles
//        Mockito.when(villeService.getAllVilles(0, 10)).thenReturn(List.of(ville));

        // Requête et assertions
        this.mockMvc.perform(MockMvcRequestBuilders.get("/villes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nom", is("Angers")))
                .andExpect(jsonPath("$.content[0].departement.code", is("49")))
                .andExpect(jsonPath("$.content[0].departement.nom", is("Maine-et-Loire")));
    }
}
