package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.documentos.Categoria;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepo extends MongoRepository<Categoria, ObjectId> {
    Categoria findByNombre(String nombre);
    @NotNull Optional<Categoria> findById(@NotNull ObjectId id);
}
