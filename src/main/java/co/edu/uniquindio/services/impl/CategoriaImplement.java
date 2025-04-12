package co.edu.uniquindio.services.impl;

import co.edu.uniquindio.dto.moderador.CategoriaDTO;
import co.edu.uniquindio.dto.moderador.CrearCategoriaDto;
import co.edu.uniquindio.dto.moderador.EditarCategoriaDto;
import co.edu.uniquindio.exceptions.ElementoNoEncontradoException;
import co.edu.uniquindio.exceptions.ElementoRepetidoException;
import co.edu.uniquindio.mapper.CategoriaMapper;
import co.edu.uniquindio.model.documentos.Categoria;
import co.edu.uniquindio.repositorios.CategoriaRepo;
import co.edu.uniquindio.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaImplement implements CategoriaService {

    private final CategoriaRepo categoriaRepo;

    private final CategoriaMapper categoriaMapper;

    @Override
    public void crearCategoria(CrearCategoriaDto categoriaDto) throws ElementoNoEncontradoException {

        if (categoriaRepo.existsByNombre(categoriaDto.nombre())) {
            throw new ElementoRepetidoException("La categoría con el nombre " + categoriaDto.nombre() + " ya existe");
        }
        Categoria categoria = categoriaMapper.toCategoria(categoriaDto);
        categoriaRepo.save(categoria);
    }

    @Override
    public void editarCategoria(EditarCategoriaDto editarCategoriaDto) throws ElementoNoEncontradoException {
        Categoria categoria = buscarCategoriaPorId(editarCategoriaDto.id());
        categoria.setNombre(editarCategoriaDto.nombre());
        categoria.setDescripcion(editarCategoriaDto.descripcion());
        if (categoriaRepo.existsByNombre(categoria.getNombre())) {
            throw new IllegalArgumentException("La categoría con el nombre " + categoria.getNombre() + " ya existe");
        }
        categoriaRepo.save(categoria);
    }

    @Override
    public void eliminarCategoria(String id) {
        Categoria categoria = buscarCategoriaPorId(id);
        categoriaRepo.delete(categoria);
    }

    @Override
    public CategoriaDTO obtenerCategoriaId(String id) {
        return categoriaMapper.toCategoriaDTO(buscarCategoriaPorId(id));
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        return categoriaMapper.toCategoriaDTOList(
                categoriaRepo.findAll().stream()
                        .sorted(Comparator.comparing(Categoria::getNombre))
                        .toList()
        );
    }

    private Categoria buscarCategoriaPorId(String id) {
        return categoriaRepo.findById(new ObjectId(id))
                .orElseThrow(() -> new ElementoNoEncontradoException("La categoría con id " + id + " no existe"));
    }
}
