package com.labo.analyse.FeignClient;

import com.labo.analyse.dtos.LaboratoireDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign Client pour le microservice Laboratory Management
@FeignClient(name = "laboratory-service", url = "http://localhost:8081/api/laboratoires")
public interface LaboratoireClient {
    @GetMapping("/{id}")
    LaboratoireDTO getLaboratoireById(@PathVariable("id") Long id);
}
