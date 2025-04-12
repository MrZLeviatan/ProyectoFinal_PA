package co.edu.uniquindio.dto.reporte;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.List;

/**
 * DTO utilizado para editar un reporte existente en el sistema.
 * Contiene los datos que se pueden modificar del reporte.
 *
 * @param idReporte ID del reporte a editar. No puede estar vacío.
 * @param titulo Título del reporte. Máximo 100 caracteres.
 * @param ubicacion Objeto que representa la ubicación del reporte.
 * @param categoria Categoría del reporte.
 * @param fotos Lista de URLs de fotos asociadas al reporte.
 */
public record EditarReporteDto(
        @NotBlank String idReporte,
        @NotBlank @Length(max = 100) String titulo,
        @NotNull UbicacionDTO ubicacion,
        @NotBlank CategoriaDTO categoria,
        @NotBlank @URL List<String> fotos
) {
}

