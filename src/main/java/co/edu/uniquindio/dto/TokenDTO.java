package co.edu.uniquindio.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {

    private String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
