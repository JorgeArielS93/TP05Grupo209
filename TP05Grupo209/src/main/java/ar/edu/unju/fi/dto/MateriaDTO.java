package ar.edu.unju.fi.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	@Autowired
	private Docente docente;
	@Autowired
	private Carrera carrera;
}
