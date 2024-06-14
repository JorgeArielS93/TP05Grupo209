package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Docente;

@Service
public interface IDocenteService {
	public List<Docente> getListaDocentes();
	public Docente findDocenteByLegajo(String legajo);
	public void agregarUnDocente(Docente docente);
	public void actualizarDocente(Docente docente);
	public void eliminarUnDocente(String legajo);
}
