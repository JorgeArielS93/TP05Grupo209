package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion; // Asegúrate de que esta propiedad existe

	@Column(name = "duracion", nullable = false)
	private int duracion; // duración en años

	@Column(name = "estado", nullable = false)
	private boolean estado;
}
