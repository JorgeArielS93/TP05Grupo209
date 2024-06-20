package ar.edu.unju.fi.service;

import java.util.List;


import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Materia;
@Service
public interface IMateriaService {
	public List<Materia> getListaMaterias();
	public Materia findMateriaByCodigo(String codigo);
	public void agregarUnaMateria(Materia materia);
	public void actualizarMateria(Materia materia);
	public void eliminarUnaMateria(String codigo);
}
