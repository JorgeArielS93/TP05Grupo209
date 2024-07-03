package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.AlumnoMapDTO;
import ar.edu.unju.fi.mapper.MateriaMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.IMateriaService;

@Service
public class AlumnoServiceImp implements IAlumnoService{
	
	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	MateriaMapDTO materiaMapDTO;
	
	@Autowired
	IMateriaService materiaService;

	@Override
	public List<AlumnoDTO> getListaAlumnos() {
		List<Alumno> alumnos = alumnoRepository.findByEstado(true);
		return alumnoMapDTO.listAlumnoToListAlumnoDTO(alumnos);
	}
	 
	@Override
	public AlumnoDTO findAlumnoByLu(Long lu) {
		return alumnoMapDTO.toDto(alumnoRepository.findById(lu).get());
	}

	@Override
	public void agregarUnAlumno(AlumnoDTO alumnoDTO) {
		alumnoDTO.setEstado(true);
        alumnoRepository.save(alumnoMapDTO.toEntity(alumnoDTO));
	}

	@Override
	public void actualizarAlumno(AlumnoDTO alumnoDTO) {
		alumnoRepository.save(alumnoMapDTO.toEntity(alumnoDTO));
	}

	@Override
	public void eliminarUnAlumno(Long lu) {
		AlumnoDTO alumnoDTO = findAlumnoByLu(lu);
        if (alumnoDTO != null) {
            alumnoDTO.setEstado(false);
            alumnoRepository.save(alumnoMapDTO.toEntity(alumnoDTO));
        } else {
            throw new RuntimeException("El alumno con lu " + lu + " no existe.");
        }
	}
	
	@Override
	public void inscribirAlumnoEnMateria(Long lu, Long materiaid) {
		AlumnoDTO alumnoDTO = findAlumnoByLu(lu);
		MateriaDTO materiaDTO = materiaService.findMateriaByCodigo(materiaid);
		
		Materia materia = materiaMapDTO.toEntity(materiaDTO);
		Alumno alumno = alumnoMapDTO.toEntity(alumnoDTO);
		
		alumno.getMaterias().add(materia);
	}
}