package co.edu.uniquindio.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RestablecerPasswordDto(
        @NotBlank String email,
        @NotBlank @Length(min = 7, max = 20) String password
        ) {
}
