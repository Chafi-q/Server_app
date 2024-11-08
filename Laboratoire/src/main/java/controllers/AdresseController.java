package controllers;

import entities.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AdresseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adresses")
public class AdresseController {

    private final AdresseService adresseService;

    @Autowired
    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @GetMapping
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        List<Adresse> adresses = adresseService.getAllAdresses();
        return new ResponseEntity<>(adresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdresseById(@PathVariable Long id) {
        Optional<Adresse> adresse = adresseService.getAdresseById(id);
        return adresse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) {
        Adresse savedAdresse = adresseService.saveAdresse(adresse);
        return new ResponseEntity<>(savedAdresse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAdresse(@PathVariable Long id, @RequestBody Adresse updatedAdresse) {
        try {
            Adresse adresse = adresseService.updateAdresse(id, updatedAdresse);
            return new ResponseEntity<>(adresse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        adresseService.deleteAdresse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
