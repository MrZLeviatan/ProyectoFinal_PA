package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.documentos.Categoria;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepo extends MongoRepository<Categoria, ObjectId> {

}
