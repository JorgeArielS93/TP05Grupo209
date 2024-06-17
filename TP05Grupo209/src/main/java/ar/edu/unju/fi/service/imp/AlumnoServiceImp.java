package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.IAlumnoService;

@Service
public class AlumnoServiceImp implements IAlumnoService{
	private static int nextLu = 1; 
	
	@Autowired
	AlumnoRepository alumnoRepository;

	@Override
	public List<Alumno> getListaAlumnos() {
		return alumnoRepository.findByEstado(true);
	}

	private static String generateLibreta() {
		return "LU-" + nextLu++;
	}
	 
	@Override
	public Alumno findAlumnoByLu(String lu) {
		return alumnoRepository.findById(lu).orElse(null);
	}

	@Override
	public void agregarUnAlumno(Alumno alumno) {
		alumno.setEstado(true);
		alumno.setLu(generateLibreta());
		alumnoRepository.save(alumno);
	}

	@Override
	public void actualizarAlumno(Alumno alumno) {
		Alumno existingAlumno = alumnoRepository.findById(alumno.getLu()).orElse(null);
		if (existingAlumno != null) {
			existingAlumno.setDni(alumno.getDni());
            existingAlumno.setNombre(alumno.getNombre());
            existingAlumno.setApellido(alumno.getApellido());
            existingAlumno.setEmail(alumno.getEmail());
            existingAlumno.setTelefono(alumno.getTelefono());
            existingAlumno.setFechaNac(alumno.getFechaNac());
            existingAlumno.setDomicilio(alumno.getDomicilio());
            
            alumnoRepository.save(existingAlumno);
        } else {
            throw new RuntimeException("El alumno con libreta " + alumno.getLu() + " no existe.");
        }
	}

	@Override
	public void eliminarUnAlumno(String lu) {
		Alumno alumno = findAlumnoByLu(lu);
        if (alumno != null) {
            alumno.setEstado(false);
            alumnoRepository.save(alumno);
        } else {
            throw new RuntimeException("El alumno con libreta " + lu + " no existe.");
        }
	}
}
