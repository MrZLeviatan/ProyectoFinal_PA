package co.edu.uniquindio;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.reporte.RegistrarReporteDto;
import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.dto.reporte.UbicacionDTO;
import co.edu.uniquindio.services.ImagenServicio;
import co.edu.uniquindio.services.impl.ReporteImplement;
import co.edu.uniquindio.services.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatosPrueba {

    @Autowired
    private ReporteImplement reporteService;

    @Autowired
    private UsuarioServiceImpl us;

    @Test
    void test() throws Exception {

//        RegistrarReporteDto dto = new RegistrarReporteDto(
//                "Reporte de prueba",
//                "682fa04d5c4a1201c762bb9f",
//                new UbicacionDTO(4,6, 4),
//                new CategoriaDTO("682f90168ba66c7d5bc6bc80", "mi nueva cat","\"esta si es una categoria nueva dios"),
//                List.of("http://res.cloudinary.com/du1jgzq6m/image/upload/v1748031961/reportes/evskesj8qp0hblc4yyov.jpg")
//        );
//
//        reporteService.agregarReporte(dto);

/*
//      List<ReporteDTO> lista = us.obtenerReportesUsuario("682fa04d5c4a1201c762bb9f");
//       for (ReporteDTO reporte : lista) {
//           System.out.println(reporte);
//       }
*/
//latitud=4.4498944, altitud=0.0, radio=10000.0
        //4.449894422531784, "ubicacion.altitud" : 0.0, "ubicacion.radio" : { "$gte" : 10000.0}}
        UbicacionDTO ubicacionDTO= new UbicacionDTO(4.449894428253174F,0.0F,10000.0F);
        System.out.println(ubicacionDTO.toString());
        List<ReporteDTO> lista = reporteService.obtenerReportesUbicacion(ubicacionDTO);
        for (ReporteDTO reporte : lista) {
            System.out.println(reporte.id());
        }





    }
}
