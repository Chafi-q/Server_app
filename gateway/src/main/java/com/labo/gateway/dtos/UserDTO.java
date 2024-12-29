package com.labo.gateway.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String profession;
    private String numTel;
    private String signature;
    private Integer idLabo;
    private Set<String> roles;
}
