package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.IDocenteService;
@Service
public class DocenteServiceImp implements IDocenteService {

    @Autowired
    DocenteRepository docenteRepository;

    @Override
    public List<Docente> getListaDocentes() {
        return docenteRepository.findByEstado(true);
    }

    @Override
    public Docente findDocenteByLegajo(Long legajo) {
        return docenteRepository.findById(legajo).get();
    }

    @Override
    public void agregarUnDocente(Docente docente) {
        docente.setEstado(true);
        docenteRepository.save(docente);
    }

    @Override
    public void actualizarDocente(Docente docente) {
        docenteRepository.save(docente);
    }

    @Override
    public void eliminarUnDocente(Long legajo) {
        Docente docente = findDocenteByLegajo(legajo);
        if (docente != null) {
            docente.setEstado(false);
            docenteRepository.save(docente);
        } else {
            throw new RuntimeException("El docente con legajo " + legajo + " no existe.");
        }
    }
}