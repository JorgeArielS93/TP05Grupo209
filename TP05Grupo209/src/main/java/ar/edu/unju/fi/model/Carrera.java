package ar.edu.unju.fi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@Column(name = "id_carrera")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codigo")
	private String codigo;

	@NonNull
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@NonNull
	@Column(name = "descripcion")
	private String descripcion; // Asegúrate de que esta propiedad existe
	
	@NonNull
	@Column(name = "duracion", nullable = false)
	private int duracion; // duración en años
	
	@NonNull
	@Column(name = "estado", nullable = false)
	private boolean estado;
	
	@OneToMany(mappedBy="carrera", cascade=CascadeType.ALL)
    private List<Materia> materias = new ArrayList<Materia>();
	
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
	private List<Alumno> alumnos=new ArrayList<Alumno>();
}