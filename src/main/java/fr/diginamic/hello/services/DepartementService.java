package fr.diginamic.hello.services;

import fr.diginamic.hello.dtos.DepartementDto;
import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.mappers.DepartementMapper;
import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.repositories.DepartementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public List<DepartementDto> getAllDepartements() {
        return departementRepository.findAll()
                .stream()
                .map(DepartementMapper::toDto)
                .toList();
    }
    public DepartementDto getDepartementById(int id) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département introuvable"));
        return DepartementMapper.toDto(departement);
    }

    public DepartementDto addDepartement(DepartementDto departementDto) {
        Departement departement = DepartementMapper.toEntity(departementDto);
        Departement savedDepartement = departementRepository.save(departement);
        return DepartementMapper.toDto(savedDepartement);
    }

    public void deleteDepartement(int id) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département introuvable"));
        departementRepository.delete(departement);
    }

    public String getNomDepartementApi(String codeDepartement) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "https://geo.api.gouv.fr/departements/" + codeDepartement + "?fields=nom,code,codeRegion";
            var response = restTemplate.getForObject(apiUrl, ApiResponse.class);
            return response != null ? response.getNom() : "Nom inconnu";
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du nom du département via l'API", e);
        }
    }

    private static class ApiResponse {
        private String nom;

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }
    }

}
