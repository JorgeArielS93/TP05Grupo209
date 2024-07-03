package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity(name = "MATERIAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
	@Id
	@Column(name = "codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NonNull
	@Column(name = "cantidad_horas", nullable = false)
	private int cantidadHoras;

	@NonNull
	@Column(name = "curso", nullable = false)
	private String curso;

	@NonNull
	@Column(name = "estado", nullable = false)
	private boolean estado;

	@NonNull
	@Column(name = "modalidad", nullable = false)
	private String modalidad;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "docente_ID")
	private Docente profesor;

	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@NonNull
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;
}