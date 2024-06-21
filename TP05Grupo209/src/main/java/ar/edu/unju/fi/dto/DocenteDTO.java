package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
public class DocenteDTO {
	private Long legajo;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private boolean estado;
}
