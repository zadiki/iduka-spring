package com.pos.iduka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Tutorial Id", example = "123")
    private Long id;

    @Schema(description = "user full name", example = "zadiki hassan")
    @NotBlank(message = "Please provide a name")
    private  String name;

    @Schema(description = "user email address", example = "zadiki@gmail.com")
    @Email(message = "Please provide a valid email address")
    @Column(unique = true)
    private String email;

    @Schema(description = "user secured password with numbers and letters minimum 4 character max 10 alphanumeric", example = "zadiki832")
    @Size(min = 8,max = 10, message = "Password must be at least 4 and max 10 characters long")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String roles;

}
