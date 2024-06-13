package ar.edu.unju.fi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carrera {
	
@Id
	private String id;
	private String codigo;
	private String nombre;
	private Integer cantidadAnios;
	private Boolean estado;
	private int duracion;
}
