package com.labo.examin.FeingClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign Client pour le microservice Test Management
@FeignClient(name = "test-management-service", url = "http://localhost:8085/api/tests")
public interface TestAnalyseClient {

    @GetMapping("/{id}")
    TestAnalyseDTO getTestAnalyseById(@PathVariable("id") Long id);
}
