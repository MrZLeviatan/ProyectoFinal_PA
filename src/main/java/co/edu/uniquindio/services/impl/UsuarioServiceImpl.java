package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.TokenDTO;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;



    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        return null;
    }

    @Override
    public void eliminarUsuario(EliminarCuentaDto cuentaDto) throws Exception {

    }

    @Override
    public void actualizarUsuario(EditarUsuarioDto usuario) throws Exception {

    }

    @Override
    public UsuarioDTO obtenerUsuarioId(String id) throws Exception {
        return null;
    }

    @Override
    public UsuarioDTO obtenerUsuarioEmail(String email) throws Exception {
        return null;
    }


    @Override
    public void restablecerPassword(RestablecerPasswordDto restablecerPasswordDto) throws Exception {

    }

    @Override
    public void crearUsuario(RegistrarUsuarioDto usuarioDTO) {

    }

    @Override
    public void solicitarRestablecer(String email) {

    }

    @Override
    public void activarCuenta(ActivarCuentaDto activarCuentaDto) {

    }



}
