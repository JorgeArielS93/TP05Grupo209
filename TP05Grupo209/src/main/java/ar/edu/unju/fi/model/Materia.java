package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
	@Id
	private String codigo;
	
	private String nombre;
	private String curso;
	private int cantidadHoras;
	private String modalidad;
	private boolean estado;

}
