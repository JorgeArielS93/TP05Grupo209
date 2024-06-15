package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;
@Service
public class MateriaServiceImp implements IMateriaService {
	private static int nextId = 1; // Inicializamos el id

    @Autowired
    MateriaRepository materiaRepository;

	@Override
	public List<Materia> getListaMaterias() {
		 return materiaRepository.findByEstado(true);
	}
	
	 private static String generateCodigo() {
	        return "Codigo-" + nextId++;
	    }

	@Override
	public Materia findMateriaByCodigo(String codigo) {
		return materiaRepository.findById(codigo).orElse(null);
	}

	@Override
	public void agregarUnaMateria(Materia materia) {
		 	materia.setEstado(true);
	        materia.setCodigo(generateCodigo());
	        materiaRepository.save(materia);
		
	}

	@Override
	public void actualizarMateria(Materia materia) {
		Materia existingMateria = materiaRepository.findById(materia.getCodigo()).orElse(null);
        if (existingMateria != null) {
        	existingMateria.setNombre(materia.getNombre());
        	existingMateria.setCurso(materia.getCurso());
        	existingMateria.setCantidadHoras(materia.getCantidadHoras());
        	existingMateria.setModalidad(materia.getModalidad());
            // Actualizar otros campos si es necesario
            materiaRepository.save(existingMateria);
        } else {
            throw new RuntimeException("La materia con código " + materia.getCodigo() + " no existe.");
        }
	}

	@Override
	public void eliminarUnaMateria(String codigo) {
		 Materia materia = findMateriaByCodigo(codigo);
	        if (materia != null) {
	            materia.setEstado(false);
	            materiaRepository.save(materia);
	        } else {
	            throw new RuntimeException("La materia con código " + codigo + " no existe.");
	        }
	}

}
