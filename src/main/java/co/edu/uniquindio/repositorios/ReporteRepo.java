package co.edu.uniquindio.repositorios;

import co.edu.uniquindio.model.documentos.Reporte;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para la gestión de reportes en la base de datos MongoDB.
 * Extiende MongoRepository para operaciones CRUD básicas.
 * Incluye métodos personalizados para obtener reportes dentro de un radio específico,
 * obtener reportes por ID, y obtener todos los reportes.
 */
@Repository
public interface ReporteRepo extends MongoRepository<Reporte, ObjectId> {

    // 1. Obtener los reportes dentro de un radio específico de una ubicación
    @Query("{'ubicacion.latitud': ?0, 'ubicacion.altitud': ?1, 'ubicacion.radio': {$gte: ?2}}")
    List<Reporte> findByUbicacionWithinRadius(float latitud, float altitud, float radio);

    // 2. Obtener un reporte específico por su ID
    Optional<Reporte> findById(ObjectId id);

    // 3. Obtener todos los reportes (aunque MongoRepository ya lo tiene por defecto)
    List<Reporte> findAll();
}
