package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Alumno;

@Service
public interface IAlumnoService {
	public List<Alumno> getListaAlumnos();
	public Alumno findAlumnoByLu(String lu);
	public void agregarUnAlumno(Alumno alumno);
	public void actualizarAlumno(Alumno alumno);
	public void eliminarUnAlumno(String lu);
}
