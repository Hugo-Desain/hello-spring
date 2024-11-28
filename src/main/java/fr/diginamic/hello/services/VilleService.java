package fr.diginamic.hello.services;

import fr.diginamic.hello.dtos.VilleDto;
import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.mappers.VilleMapper;
import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.models.Ville;
import fr.diginamic.hello.repositories.DepartementRepository;
import fr.diginamic.hello.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private DepartementRepository departementRepository;

    public Page<VilleDto> getAllVilles(int page, int size) {
        Page<Ville> villesPage = villeRepository.findAll(PageRequest.of(page, size));
        return villesPage.map(VilleMapper::toDto);
    }

    public VilleDto getVilleById(int id) {
        Ville ville = villeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ville introuvable"));
        return VilleMapper.toDto(ville);
    }

    public List<VilleDto> getVillesByName(String nom) throws FunctionalException {
        List<Ville> villes = villeRepository.findByNomStartingWith(nom);
        if (villes.isEmpty()) {
            throw new FunctionalException("Aucune ville dont le nom commence par " + nom + " n’a été trouvée.");
        }
        return villes.stream().map(VilleMapper::toDto).toList();
    }

    public List<VilleDto> getVillesByPopulation(int min, Integer max) throws FunctionalException {
        List<Ville> villes;
        if (max == null) {
            villes = villeRepository.findByNbHabitantsGreaterThan(min);
            if (villes.isEmpty()) {
                throw new FunctionalException("Aucune ville n’a une population supérieure à " + min);
            }
        } else {
            villes = villeRepository.findByNbHabitantsBetween(min, max);
            if (villes.isEmpty()) {
                throw new FunctionalException("Aucune ville n’a une population comprise entre " + min + " et " + max);
            }
        }
        return villes.stream().map(VilleMapper::toDto).toList();
    }

    public List<VilleDto> getVillesByDepartement(int departementId) {
        List<Ville> villes;
        villes = villeRepository.findByDepartementId(departementId);
        return villes.stream()
                .map(VilleMapper::toDto)
                .toList();
    }

    public List<VilleDto> getTopVillesByDepartement(int departementId, int n) {
        return villeRepository.findTopByDepartementOrderByPopulation(departementId, PageRequest.of(0, n))
                .stream()
                .map(VilleMapper::toDto)
                .toList();
    }

    public VilleDto addVille(VilleDto villeDto) throws FunctionalException {

        if (villeDto.getNbHabitants() < 10) {
            throw new FunctionalException("Une ville doit avoir au moins 10 habitants");
        }

        if (villeDto.getNomVille() == null || villeDto.getNomVille().length() < 2) {
            throw new FunctionalException("Le nom de la ville doit contenir au moins 2 lettres");
        }

        Ville ville = VilleMapper.toEntity(villeDto);
        Departement departementd = departementRepository.findByCode(ville.getDepartement().getCode());
        if (departementd == null) {
            throw new FunctionalException("Le département spécifié n'existe pas.");
        }
        ville.setDepartement(departementd);
        Ville savedVille = villeRepository.save(ville);
        return VilleMapper.toDto(savedVille);
    }

    public VilleDto updateVille(int id, VilleDto villeDto) {
        Ville existingVille = villeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ville introuvable"));
        existingVille.setNom(villeDto.getNomVille());
        existingVille.setNbHabitants(villeDto.getNbHabitants());
        existingVille.getDepartement().setCode(villeDto.getCodeDepartement());
        existingVille.getDepartement().setNom(villeDto.getNomDepartement());
        Ville updatedVille = villeRepository.save(existingVille);
        return VilleMapper.toDto(updatedVille);
    }

    public void deleteVille(int id) {
        villeRepository.deleteById(id);
    }

}