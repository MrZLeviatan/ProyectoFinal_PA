package co.edu.uniquindio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginDto(
        @NotBlank @Email String email,
        @NotBlank @Length(min=7) String password
) {
}
