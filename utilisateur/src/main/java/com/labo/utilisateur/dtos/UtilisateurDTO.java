package com.labo.utilisateur.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurDTO {
    private Integer id;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String nomComplet;
    private String profession;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp="^[0-9]*$", message = "Phone number must contain only digits")
    private String numTel;
    private String signature;
    @NotBlank(message = "Role is required")
    private String role;
    private Integer idLabo;
}
