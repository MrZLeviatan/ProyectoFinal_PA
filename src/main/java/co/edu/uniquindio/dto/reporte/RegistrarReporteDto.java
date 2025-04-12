package co.edu.uniquindio.dto.reporte;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.List;

/**
 * DTO utilizado para registrar un nuevo reporte.
 *
 * @param titulo      Título del reporte, máximo 100 caracteres.
 * @param idUsuario   ID del usuario que reporta.
 * @param ubicacion   Ubicación donde ocurre el incidente.
 * @param categoria   Categoría del reporte.
 * @param fotos       Lista de URLs con las imágenes asociadas al reporte.
 */
public record RegistrarReporteDto(

        @NotBlank @Length(max = 100) String titulo,
        @NotBlank String idUsuario,
        @NotNull UbicacionDTO ubicacion,
        @NotBlank CategoriaDTO categoria,
        @NotBlank @URL List<String> fotos

) {
}
