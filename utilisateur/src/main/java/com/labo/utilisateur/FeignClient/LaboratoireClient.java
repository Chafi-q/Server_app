package com.labo.utilisateur.FeignClient;
import com.labo.utilisateur.dtos.LaboratoireDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Déclaration du client Feign pour communiquer avec le service Laboratory Management
@FeignClient(name = "laboratory-service", url = "http://localhost:8081/api/laboratoires")
public interface LaboratoireClient {

    @GetMapping("/{id}")
    LaboratoireDTO getLaboratoireById(@PathVariable("id") Long id);
}

