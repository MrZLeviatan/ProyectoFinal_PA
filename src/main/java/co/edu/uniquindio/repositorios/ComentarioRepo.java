package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.documentos.Comentario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * Interfaz para la gestión de comentarios en la base de datos MongoDB.
 * Extiende MongoRepository para operaciones CRUD básicas.
 */
@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, ObjectId> {
    List<Comentario> findByIdComentario(ObjectId idComentario);

}
