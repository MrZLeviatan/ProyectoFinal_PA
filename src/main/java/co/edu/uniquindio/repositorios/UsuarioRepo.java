package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.documentos.Usuario;
import co.edu.uniquindio.model.enums.Ciudad;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import  org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para la gestión de usuarios en la base de datos MongoDB.
 * Extiende MongoRepository para operaciones CRUD básicas.
 * Incluye un método adicional para buscar usuarios por su email y un método
 * para obtener usuarios por nombre y ciudad, con soporte de paginación.
 */
@Repository
public interface UsuarioRepo extends MongoRepository<Usuario,ObjectId> {

    Optional<Usuario> findByEmail(String email);

    @Query("{'nombre':?0 , 'ciudad' :?1}")
    List<Usuario> findByNombreYCiudad(String nombre, Ciudad ciudad, Pageable pageable);
}
