package co.edu.uniquindio.dto.reporte;

import co.edu.uniquindio.dto.modeloDTO.UbicacionDTO;
import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public record EditarReporteDto(

        @NotBlank @Length(max = 100) String titulo,
        @NotNull UbicacionDTO ubicacion,
        @NotBlank CategoriaDTO categoria,
        @NotBlank @URL String foto // URL de la imagen

) {
}
