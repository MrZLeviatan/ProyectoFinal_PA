package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.documentos.Comentario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, ObjectId> {
}
