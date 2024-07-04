package ar.edu.unju.fi.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class MateriaDTO {
	private Long codigo;
	@NotBlank(message = "Debe ingresar el DNI")
	@Size(min=6, max=40, message = "El nombre de la carrera debe contener entre 6 y 40 caracteres")
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
