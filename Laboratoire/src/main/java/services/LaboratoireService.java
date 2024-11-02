package services;



import entities.Laboratoire;
import Repositories.LaboratoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoireService {

    @Autowired
    private LaboratoireRepository laboratoireRepository;

    public Laboratoire createLaboratoire(Laboratoire laboratoire) {
        return laboratoireRepository.save(laboratoire);
    }

    // Obtenir un laboratoire par son ID
    public Optional<Laboratoire> getLaboratoireById(Long id) {
        return laboratoireRepository.findById(id);
    }

    // Mettre à jour un laboratoire existant
    public Laboratoire updateLaboratoire(Long id, Laboratoire laboratoireDetails) {
        Laboratoire laboratoire = laboratoireRepository.findById(id).orElseThrow(() -> new RuntimeException("Laboratoire not found"));
        laboratoire.setNom(laboratoireDetails.getNom());
        laboratoire.setLogo(laboratoireDetails.getLogo());
        laboratoire.setNrc(laboratoireDetails.getNrc());
        laboratoire.setActive(laboratoireDetails.isActive());
        laboratoire.setDateActivation(laboratoireDetails.getDateActivation());
        return laboratoireRepository.save(laboratoire);
    }

    
    public void deleteLaboratoire(Long id) {
        laboratoireRepository.deleteById(id);
    }

   
    public List<Laboratoire> getAllLaboratoires() {
        return laboratoireRepository.findAll();
    }
}
