package fr.diginamic.hello.mappers;

import fr.diginamic.hello.dtos.VilleDto;
import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.models.Ville;

public class VilleMapper {

    public static VilleDto toDto(Ville ville) {
        if (ville == null) {
            return null;
        }
        VilleDto dto = new VilleDto();
        dto.setNomVille(ville.getNom());
        dto.setNbHabitants(ville.getNbHabitants());
        dto.setCodeDepartement(ville.getDepartement() != null ? ville.getDepartement().getCode() : null);
        dto.setNomDepartement(ville.getDepartement() != null ? ville.getDepartement().getNom() : null);
        return dto;
    }

    public static Ville toEntity(VilleDto dto) {
        if (dto == null) {
            return null;
        }
        Ville ville = new Ville();
        ville.setNom(dto.getNomVille());
        ville.setNbHabitants(dto.getNbHabitants());

        Departement departement = new Departement();
        departement.setCode(dto.getCodeDepartement());
        ville.setDepartement(departement);

        return ville;
    }
}
