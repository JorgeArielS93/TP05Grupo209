package ar.edu.unju.fi.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class MateriaDTO {
	private Long codigo;
	private String nombre;
	private String curso;
	private int cantidadHoras;
	private String modalidad;
	private boolean estado;
	private List<Alumno> alumnos;
	private Carrera carrera;
	private Docente docente;
}
