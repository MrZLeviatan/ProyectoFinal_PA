package co.edu.uniquindio.dto.reporte;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public record EditarReporteDto(
        @NotBlank String idReporte,
        @NotBlank @Length(max = 100) String titulo,
        @NotNull UbicacionDTO ubicacion,
        @NotBlank CategoriaDTO categoria,
        @NotBlank @URL List<String> fotos // URL de la imagen

) {
}
