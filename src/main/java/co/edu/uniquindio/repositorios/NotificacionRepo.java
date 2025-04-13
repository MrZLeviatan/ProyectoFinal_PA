package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.documentos.Notificacion;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfaz para la gestión de notificaciones en la base de datos MongoDB.
 * Extiende MongoRepository para operaciones CRUD básicas.
 */
@Repository
public interface NotificacionRepo extends MongoRepository<Notificacion, ObjectId> {
    @NotNull Optional<Notificacion> findById(@NotNull ObjectId id);

}
