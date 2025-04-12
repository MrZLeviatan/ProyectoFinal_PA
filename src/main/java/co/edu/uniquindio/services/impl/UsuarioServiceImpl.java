package co.edu.uniquindio.services.impl;
import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.exceptions.*;
import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.vo.CodigoValidacion;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.EmailServicio;
import co.edu.uniquindio.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {


    private final EmailServicio emailServicio;;

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;



    @Override
    public void eliminarUsuario(EliminarCuentaDto cuentaDto) throws Exception {
        Usuario usuario= obtenerPorId(cuentaDto.id());
        if(usuario.getPassword().equals(cuentaDto.password())){
            usuario.setEstadoUsuario(EstadoUsuario.ELIMINADO);
            usuarioRepo.save(usuario);
        }else {
            throw new CredencialesInvalidasException("La contraseña ingresada no coincide");
        }
    }

    @Override
    public void actualizarUsuario(EditarUsuarioDto usuario) throws Exception {
        Usuario cuentaModificada = obtenerPorId(usuario.id());
        cuentaModificada.setNombre(usuario.nombre());
        cuentaModificada.setDireccion(usuario.direccion());
        cuentaModificada.setCiudad(usuario.ciudad());
        usuarioRepo.save(cuentaModificada);
    }

    @Override
    public UsuarioDTO obtenerUsuarioId(String id)  {
        Usuario usuario= obtenerPorId(id);
        return usuarioMapper.toUsuarioDTO(usuario);
    }

    @Override
    public UsuarioDTO obtenerUsuarioEmail(String email) {
        Usuario usuario= obtenerUsuarioByEmail(email);
        return usuarioMapper.toUsuarioDTO(usuario);
    }




    @Override
    public void crearUsuario(RegistrarUsuarioDto usuarioDTO) throws ElementoRepetidoException {
        if(existeEmail(usuarioDTO.email())){
//            Usuario usuario= obtenerUsuarioByEmail(usuarioDTO.email());
//            if(usuario.getEstadoUsuario()==EstadoUsuario.ELIMINADO){
//                // que hacemos
//            }else {
//                throw new ElementoRepetidoException("el correo se encuentra registrado");
//            }
            throw new ElementoRepetidoException("el correo se encuentra registrado");
        }
        //el usuarioDto se transforma en un usuario nuevo
        Usuario usuario = usuarioMapper.toDocument(usuarioDTO);
        //guardamos el usuario en el repositorio
        usuarioRepo.save(usuario);
    }

    @Override
    public void restablecerPassword(RestablecerPasswordDto restablecerPasswordDto) throws ElementoNoEncontradoException {
        Optional<Usuario> usuario = usuarioRepo.findByEmail(restablecerPasswordDto.email());
        if (usuario.isPresent()) {
            usuario.get().setPassword(restablecerPasswordDto.password());
            usuarioRepo.save(usuario.get());
        }else {
            throw new ElementoNoEncontradoException("el correo ingresado no existe");
        }
    }

    @Override
    public void solicitarRestablecer(String email) throws Exception {
        if(!existeEmail(email)) {
            throw new ElementoNoEncontradoException("el correo ingresado no existe");
        }
        Usuario usuario= obtenerUsuarioByEmail(email);
        if(usuario.getEstadoUsuario().equals(EstadoUsuario.ELIMINADO)){
            throw new ElementoNoEncontradoException("El usuario fue eliminado");
        }
        if(usuario.getEstadoUsuario().equals(EstadoUsuario.ACTIVO)){
            String codigo= generarCodigoActivacion();
           // EmailServicio enviarEmail = new EmailServicioImp();
            String asunto = "Solicitud cambio de contraseña";
            String cuerpo = "su codigoActivacion de confirmacion es: "+ codigo;
            emailServicio.enviarCorreo(new EmailDto(asunto,cuerpo,email));
            CodigoValidacion codigoValidacion = new CodigoValidacion();
            //guardamos el codigo en donde antes estaba el de activacion para no crear nuevas entidades
            codigoValidacion.setCodigo(codigo);
            usuario.setCodigoValidacion(codigoValidacion);
            usuarioRepo.save(usuario);


        }else {
            throw new PermisoDenegadoException("el usuario deberia activar su cuenta primero");
        }
    }




    @Override
    public void activarCuenta(ActivarCuentaDto activarCuentaDto) throws Exception {
        // Obtenemos el usuario y verificamos si existe en la base de datos
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(new ObjectId(activarCuentaDto.id()));
        if (usuarioOptional.isEmpty()) {
            throw new ElementoNoEncontradoException("El ID no existe en el sistema");
        }

        Usuario usuario = usuarioOptional.get();
        CodigoValidacion codigoValidacion = usuario.getCodigoValidacion();

        if (codigoValidacion == null) {
            throw new ElementoNoEncontradoException("El código no existe en el sistema");
        }

        // Verificamos si el código de validación ha vencido
        if (isCodVen(codigoValidacion)) {
            throw new CodigoExpiradoException("El código ingresado ya venció");
        }

        // Verificamos si el código es correcto
        if (!codigoValidacion.getCodigo().equals(activarCuentaDto.codigoActivacion().codigo())){
            throw new CodigoIncorrectoException("El código de confirmación no es correcto");
        }

        // Eliminamos el código de validación del usuario
        eliminarCodigo(usuario);
    }

    private void eliminarCodigo(Usuario usuario) {
        //eliminamos el atributo codigoActivacion ya no es necesario
        usuario.setCodigoValidacion(null);
        usuario.setEstadoUsuario(EstadoUsuario.ACTIVO);
        usuarioRepo.save(usuario);
    }

    private boolean isCodVen(CodigoValidacion codigoValidacion) {
        // Compara si el código ha vencido
        LocalDateTime limite = LocalDateTime.now().minusMinutes(15);
        return codigoValidacion.getHoraCreacion().isBefore(limite);
    }

    // metodo creado para generar El codigoActivacion de Activacion del usuario
    private String generarCodigoActivacion() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // Caracteres posibles
        Random rand = new Random();
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int indiceAleatorio = rand.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indiceAleatorio));
        }

        return codigo.toString();
    }

    private boolean existeEmail(String email){
        return usuarioRepo.findByEmail(email).isPresent();
    }

    private Usuario obtenerUsuarioByEmail(String email){
        if(usuarioRepo.findByEmail(email).isEmpty()){
            throw new ElementoNoEncontradoException("no se encontro el usuario con el email "+ email);
        }
        return usuarioRepo.findByEmail(email).get();
    }
    private Usuario obtenerPorId(String id) throws ElementoNoEncontradoException {
        // Buscamos el usuario que se quiere obtener
        if (!ObjectId.isValid(id)) {
            throw new ElementoNoEncontradoException("No se encontró el usuario con el id "+id);
        }
        Optional<Usuario> optionalCuenta = usuarioRepo.findById(new ObjectId(id));

        // Si no se encontró el usuario, lanzamos una excepción
        if(optionalCuenta.isEmpty()){
            throw new ElementoNoEncontradoException("No se encontró el usuario con el id "+id);
        }

        return optionalCuenta.get();
    }
}
