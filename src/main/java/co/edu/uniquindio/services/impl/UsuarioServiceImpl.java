package co.edu.uniquindio.services.impl;
import co.edu.uniquindio.constants.MensajesError;
import co.edu.uniquindio.dto.EliminarCuentaDto;
import co.edu.uniquindio.dto.EmailDto;
import co.edu.uniquindio.dto.RestablecerPasswordDto;
import co.edu.uniquindio.dto.reporte.ReporteDTO;
import co.edu.uniquindio.dto.usuario.ActivarCuentaDto;
import co.edu.uniquindio.dto.usuario.EditarUsuarioDto;
import co.edu.uniquindio.dto.usuario.RegistrarUsuarioDto;
import co.edu.uniquindio.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.exceptions.*;
import co.edu.uniquindio.mapper.ReporteMapper;
import co.edu.uniquindio.mapper.UsuarioMapper;
import co.edu.uniquindio.model.documentos.Reporte;
import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.EstadoUsuario;
import co.edu.uniquindio.model.enums.Rol;
import co.edu.uniquindio.model.vo.CodigoValidacion;
import co.edu.uniquindio.repositorios.UsuarioRepo;
import co.edu.uniquindio.services.EmailService;
import co.edu.uniquindio.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final EmailService emailService;;
    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final ReporteMapper reporteMapper;

    @Override
    public void eliminarUsuario(EliminarCuentaDto cuentaDto) throws ElementoNoEncontradoException,PermisoDenegadoException,CredencialesInvalidasException{
        Usuario usuarioActual = obtenerUsuarioAutenticado();
        Usuario usuarioObjetivo = obtenerPorId(cuentaDto.id());

        // Si es MODERADOR puede eliminar directamente a cualquier usuario
        if (usuarioActual.getRol() == Rol.MODERADOR) {
            usuarioObjetivo.setEstadoUsuario(EstadoUsuario.ELIMINADO);
            usuarioRepo.save(usuarioObjetivo);
            return;
        }

        // Si intenta eliminar a otro usuario diferente al suyo → NO PERMITIDO
        if (!usuarioActual.getId().equals(usuarioObjetivo.getId())) {
            throw new PermisoDenegadoException(MensajesError.PERMISO_DENEGADO);
        }

        // Verificar contraseña para eliminarse a sí mismo
        if (!passwordEncoder.matches(cuentaDto.password(), usuarioObjetivo.getPassword())) {
            throw new CredencialesInvalidasException(MensajesError.CREDENCIALES_INVALIDAS);
        }

        // Marcar como eliminado
        usuarioObjetivo.setEstadoUsuario(EstadoUsuario.ELIMINADO);
        usuarioRepo.save(usuarioObjetivo);

    }


    @Override
    public void actualizarUsuario(EditarUsuarioDto usuario) throws Exception {
        String idUsario = obtenerIdToken();
        Usuario cuentaModificada = obtenerPorId(usuario.id());
        if(!idUsario.equals(usuario.id())){
            throw new PermisoDenegadoException(MensajesError.PERMISO_DENEGADO);
        }
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
            Usuario usuario= obtenerUsuarioByEmail(usuarioDTO.email());
            if(usuario.getEstadoUsuario()==EstadoUsuario.ELIMINADO){
                usuario.setEstadoUsuario(EstadoUsuario.INACTIVO);
                usuarioRepo.save(usuario);
                return;
            }else {
                throw new ElementoRepetidoException(MensajesError.CORREO_REGISTRADO);
            }
        }
        //el usuarioDto se transforma en un usuario nuevo
        Usuario usuario = usuarioMapper.toDocument(usuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.password()));

        CodigoValidacion codigoValidacion = new CodigoValidacion();
        codigoValidacion.setCodigo(generarCodigoActivacion());
        codigoValidacion.setHoraCreacion(LocalDateTime.now());

        usuario.setCodigoValidacion(codigoValidacion);

        //guardamos el usuario en el repositorio
        usuarioRepo.save(usuario);
    }


    @Override
    public void restablecerPassword(RestablecerPasswordDto restablecerPasswordDto) throws ElementoNoEncontradoException {
        Optional<Usuario> usuario = usuarioRepo.findByEmail(restablecerPasswordDto.email());
        if (usuario.isPresent()) {
            usuario.get().setPassword(passwordEncoder.encode(restablecerPasswordDto.password()));
            usuarioRepo.save(usuario.get());
        }else {
            throw new ElementoNoEncontradoException(MensajesError.CORREO_NO_ENCONTRADO);
        }
    }

    @Override
    public void solicitarRestablecer(String email) throws Exception {
        if(!existeEmail(email)) {
            throw new ElementoNoEncontradoException(MensajesError.CORREO_NO_ENCONTRADO);
        }
        Usuario usuario= obtenerUsuarioByEmail(email);
        if(usuario.getEstadoUsuario().equals(EstadoUsuario.ELIMINADO)){
            throw new ElementoNoEncontradoException("El usuario fue eliminado");
        }
        if(usuario.getEstadoUsuario().equals(EstadoUsuario.ACTIVO)){
            //generamos el codigo de validado
            String codigo= generarCodigoActivacion();
            String asunto = "Solicitud cambio de contraseña";
            String cuerpo = "su codigoActivacion de confirmacion es: "+ codigo;
            emailService.enviarCorreo(new EmailDto(asunto,cuerpo,email));
            //creamos el codigo de validacion
            CodigoValidacion codigoValidacion = new CodigoValidacion();
            codigoValidacion.setHoraCreacion(LocalDateTime.now());
            codigoValidacion.setCodigo(codigo);
            usuario.setCodigoValidacion(codigoValidacion);
            usuarioRepo.save(usuario);


        }else {
            throw new PermisoDenegadoException(MensajesError.USUARIO_NO_ACTIVADO);
        }
    }




    @Override
    public void activarCuenta(ActivarCuentaDto activarCuentaDto) throws Exception {

        Usuario usuario = obtenerUsuarioByEmail(activarCuentaDto.email());
        CodigoValidacion codigoValidacion = usuario.getCodigoValidacion();

        if (codigoValidacion == null) {
            throw new ElementoNoEncontradoException("El código no existe en el sistema");
        }

        // Verificamos si el código de validación ha vencido
        if (isCodVen(codigoValidacion)) {
            throw new CodigoExpiradoException("El código ingresado ya venció");
        }

        // Verificamos si el código es correcto
        if (!codigoValidacion.getCodigo().equals(activarCuentaDto.codigoActivacion())){
            throw new CodigoIncorrectoException("El código de confirmación no es correcto");
        }

        // Eliminamos el código de validación del usuario
        eliminarCodigo(usuario);
    }

    @Override
    public List<ReporteDTO> obtenerReportesUsuario(String id) {
        Usuario usuario= obtenerPorId(id);
        List<Reporte> reportes= usuario.getReportes();
        List<ReporteDTO> reportesDTO= reporteMapper.toReporteDTOList(reportes);
        return reportesDTO;
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

    private String obtenerIdToken() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    private Usuario obtenerUsuarioAutenticado() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioRepo.findById(new ObjectId(user.getUsername()))
                .orElseThrow(() -> new ElementoNoEncontradoException("Usuario autenticado no encontrado"));
    }
}
