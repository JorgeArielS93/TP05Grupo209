package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.IDocenteService;
@Service
public class DocenteServiceImp implements IDocenteService {
	private static int nextId = 1; // Inicializamos el id
	
	@Autowired
	DocenteRepository docenteRepository;

	@Override
	public List<Docente> getListaDocentes() {
		return docenteRepository.findByEstado(true);
	}
	
	private static String generateCodigo() {
        return "Legajo-" + nextId++;
    }
	
	@Override
	public Docente findDocenteByLegajo(String legajo) {
		
		return docenteRepository.findById(legajo).get();
	}

	@Override
	public void agregarUnDocente(Docente docente) {
		docente.setEstado(true);
		docente.setLegajo(generateCodigo());
		docenteRepository.save(docente);
		
	}

	@Override
	public void actualizarDocente(Docente docente) {
		docenteRepository.save(docente);
		
	}

	@Override
	public void eliminarUnDocente(String legajo) {
		Docente docente= findDocenteByLegajo(legajo);
		docente.setEstado(false);
		docenteRepository.save(docente);
	}
	
}