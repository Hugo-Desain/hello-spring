package fr.diginamic.hello.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.hello.controleurs.VilleControleur;
import fr.diginamic.hello.dtos.VilleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import fr.diginamic.hello.services.VilleService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VilleControleur.class)
class VilleControleurTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VilleService villeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateVille_Success() throws Exception {
        VilleDto villeDto = new VilleDto("Lyon", 500000, "01", "Ain");
        when(villeService.addVille(villeDto)).thenReturn(villeDto);

        mockMvc.perform(post("/villes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(villeDto)))
                .andExpect(status().isOk());
    }
}
