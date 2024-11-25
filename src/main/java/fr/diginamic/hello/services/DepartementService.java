package fr.diginamic.hello.services;

import fr.diginamic.hello.daos.DepartementDao;
import fr.diginamic.hello.models.Departement;
import fr.diginamic.hello.models.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartementService {

    @Autowired
    private DepartementDao departementDao;

    public List<Departement> getAllDepartements() {
        return departementDao.findAll();
    }

    public Departement getDepartementById(int id) {
        return departementDao.findById(id);
    }

    @Transactional
    public Departement addDepartement(Departement departement) {
        return departementDao.save(departement);
    }

    @Transactional
    public void deleteDepartement(int id) {
        departementDao.deleteById(id);
    }

    public List<Ville> getTopNVilles(int idDepartement, int n) {
        Departement departement = departementDao.findById(idDepartement);
        if (departement == null) {
            return List.of();
        }
        return departement.getVilles().stream()
                .sorted((v1, v2) -> Integer.compare(v2.getNbHabitants(), v1.getNbHabitants()))
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Ville> getVillesByPopulationRange(int idDepartement, int min, int max) {
        Departement departement = departementDao.findById(idDepartement);
        if (departement == null) {
            return List.of();
        }
        return departement.getVilles().stream()
                .filter(v -> v.getNbHabitants() >= min && v.getNbHabitants() <= max)
                .collect(Collectors.toList());
    }
}
