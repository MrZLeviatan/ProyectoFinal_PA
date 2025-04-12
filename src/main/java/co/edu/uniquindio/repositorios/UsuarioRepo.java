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

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario,ObjectId> {

    Optional<Usuario> findByEmail(String email);


    @Query("{'nombre':?0 , 'ciudad' :?1}")
    List<Usuario> findByNombreYCiudad(String nombre, Ciudad ciudad, Pageable pageable);


}
