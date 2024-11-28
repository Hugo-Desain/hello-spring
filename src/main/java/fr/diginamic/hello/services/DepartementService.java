package fr.diginamic.hello.services;

import fr.diginamic.hello.dtos.DepartementDto;
import fr.diginamic.hello.exceptions.FunctionalException;
import fr.diginamic.hello.mappers.DepartementMapper;
import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.repositories.DepartementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private void validateDepartement(DepartementDto departementDto) throws FunctionalException {
        if (departementDto.getCodeDepartement() == null || departementDto.getCodeDepartement().length() < 2 || departementDto.getCodeDepartement().length() > 3) {
            throw new FunctionalException("Le code du département doit contenir entre 2 et 3 caractères.");
        }
        if (departementDto.getNomDepartement() == null || departementDto.getNomDepartement().length() < 3) {
            throw new FunctionalException("Le nom du département est obligatoire et doit contenir au moins 3 lettres.");
        }
    }
}
