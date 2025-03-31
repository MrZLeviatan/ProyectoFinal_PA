package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.Usuario;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario,ObjectId> {

    Optional<Usuario> findByEmail(String email);
}
