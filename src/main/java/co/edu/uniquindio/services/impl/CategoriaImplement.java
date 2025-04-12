package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.mapper.CategoriaMapper;
import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaImplement implements CategoriaService {

    private final CategoriaRepo categoriaRepo;

    private final CategoriaMapper categoriaMapper;

    @Override
    public void crearCategoria(CrearCategoriaDto categoriaDto) throws Exception {
        Categoria categoria = categoriaMapper.toCategoria(categoriaDto);
        categoriaRepo.save(categoria);
    }

    @Override
    public void editarCategoria(EditarCategoriaDto editarCategoriaDto) throws Exception {
        Categoria categoriaModificada = buscarCategoriaId(editarCategoriaDto.id());
        categoriaModificada.setNombre(editarCategoriaDto.nombre());
        categoriaModificada.setDescripcion(editarCategoriaDto.descripcion());
        categoriaRepo.save(categoriaModificada);
    }

    @Override
    public void eliminarCategoria(String id) throws ElementoNoEncontradoException {
        Categoria categoria= buscarCategoriaId(id);
        categoriaRepo.delete(categoria);
        // categoria.setEstadoCategoria.ELIMANADA;
    }

    @Override
    public CategoriaDTO obtenerCategoriaId(String id) throws Exception {
        return categoriaMapper.toCategoriaDTO(buscarCategoriaId(id));
    }

    @Override
    public List<CategoriaDTO> listarCategorias() throws Exception {
        return categoriaMapper.toCategoriaDTOList(obtenerCategorias());
    }

    private List<Categoria> obtenerCategorias() {
        return categoriaRepo.findAll();
    }

    private Categoria buscarCategoriaId(String id) {
        Optional<Categoria> categoria= categoriaRepo.findById(new ObjectId(id));
        if(categoria.isPresent()) {
            return categoria.get();
        }else {
            throw new ElementoNoEncontradoException("la categia con el id " + id + " no existe");
        }
    }
}
