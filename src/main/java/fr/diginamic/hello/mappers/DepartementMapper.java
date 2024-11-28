package fr.diginamic.hello.mappers;

import fr.diginamic.hello.dtos.DepartementDto;
import fr.diginamic.hello.models.Departement;

public class DepartementMapper {

    public static DepartementDto toDto(Departement departement) {
        if (departement == null) {
            return null;
        }
        DepartementDto dto = new DepartementDto();
        dto.setCodeDepartement(departement.getCode());
        dto.setNomDepartement(departement.getNom());
        dto.setNbHabitants(departement.getVilles() != null
                ? departement.getVilles().stream().mapToInt(v -> v.getNbHabitants()).sum()
                : 0);
        return dto;
    }

    public static Departement toEntity(DepartementDto dto) {
        if (dto == null) {
            return null;
        }
        Departement departement = new Departement();
        departement.setCode(dto.getCodeDepartement());
        departement.setNom(dto.getNomDepartement());
        return departement;
    }
}
