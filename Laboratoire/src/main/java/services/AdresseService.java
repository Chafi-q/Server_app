package services;

import entities.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repositories.AdresseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {

    private final AdresseRepository adresseRepository;

    @Autowired
    public AdresseService(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    public List<Adresse> getAllAdresses() {
        return adresseRepository.findAll();
    }

    public Optional<Adresse> getAdresseById(Long id) {
        return adresseRepository.findById(id);
    }

    public Adresse saveAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    public Adresse updateAdresse(Long id, Adresse updatedAdresse) {
        if (adresseRepository.existsById(id)) {
            updatedAdresse.setId(id);
            return adresseRepository.save(updatedAdresse);
        } else {
            throw new RuntimeException("Adresse not found with id " + id);
        }
    }

    public void deleteAdresse(Long id) {
        adresseRepository.deleteById(id);
    }
}
