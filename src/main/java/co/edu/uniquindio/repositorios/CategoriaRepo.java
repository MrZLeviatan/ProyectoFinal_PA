package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.Categoria;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepo extends MongoRepository<Categoria, ObjectId> {

}
