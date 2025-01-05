package com.labo.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String profession;
    private String numTel;
    private String signature;
    private String role;
}