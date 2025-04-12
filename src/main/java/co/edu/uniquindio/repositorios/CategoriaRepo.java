package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.documentos.Categoria;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfaz para la gestión de categorías en la base de datos MongoDB.
 * Extiende MongoRepository para operaciones CRUD básicas.
 * Incluye un método adicional para buscar categorías por su nombre.
 */
@Repository
public interface CategoriaRepo extends MongoRepository<Categoria, ObjectId> {
    Optional<Categoria> findByNombre(String nombre);
    @NotNull Optional<Categoria> findById(@NotNull ObjectId id);
}
