package ar.edu.unju.fi.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class CarreraDTO {
	private Long id;
	private String codigo;
	private String nombre;
	private String descripcion; // Asegúrate de que esta propiedad existe
	private int duracion;
	private boolean estado;
	private List<MateriaDTO> materias=new ArrayList<MateriaDTO>();
}
