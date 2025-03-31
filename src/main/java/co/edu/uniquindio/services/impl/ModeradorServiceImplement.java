package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarModeradorDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.ModeradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeradorServiceImplement implements ModeradorService {

    UsuarioRepo usuarioRepo;

    @Override
    public void eliminarModerador(EliminarCuentaDto cuentaDto) throws Exception {

    }

    @Override
    public void actualizarModerador(EditarModeradorDto moderadorAct) throws Exception {

    }

    @Override
    public UsuarioDTO obtenerModeradorId(String id) throws Exception {
        return null;
    }

    @Override
    public UsuarioDTO obtenerModeradorEmail(String email) throws Exception {
        return null;
    }

    @Override
    public List<UsuarioDTO> listarUsuarios(String nombre, String ciudad, int pagina, int size) throws Exception {
        return List.of();
    }

    @Override
    public void crearCategoria(CrearCategoriaDto categoriaDto) throws Exception {

    }

    @Override
    public void editarCategoria(EditarCategoriaDto editarCategoriaDto) throws Exception {

    }

    @Override
    public void eliminarCategoria(String id) throws Exception {

    }

    @Override
    public CategoriaDTO obtenerCategoriaId(String id) throws Exception {
        return null;
    }

    @Override
    public List<CategoriaDTO> listarCategorias() throws Exception {
        return List.of();
    }
}
