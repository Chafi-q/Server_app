package Patient.src.main.java.com.labo.patient.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign Client pour le microservice Patient Management
@FeignClient(name = "patient-service", url = "http://localhost:8082/api/patients")
public interface PatientClient {
    @GetMapping("/{id}")
    PatientDTO getPatientById(@PathVariable("id") Long id);
}
