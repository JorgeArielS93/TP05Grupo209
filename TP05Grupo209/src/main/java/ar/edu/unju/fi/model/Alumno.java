package ar.edu.unju.fi.model;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
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
@Entity(name = "ALUMNOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
	
	@NonNull
    @Column(name = "alu_DNI", nullable = false)
	private String dni;
	
	@NonNull
    @Column(name = "alu_Nombre", nullable = false)
	private String nombre;
	
	@NonNull
    @Column(name = "alu_Apellido", nullable = false)
	private String apellido;
	
	@NonNull
    @Column(name = "alu_Email", nullable = false)
	private String email;
	
	@NonNull
    @Column(name = "alu_Telefono", nullable = false)
	private String telefono;
	
	@NonNull
    @Column(name = "alu_FechaNac", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNac;
	
	@NonNull
    @Column(name = "alu_Domicilio", nullable = false)
	private String domicilio;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alu_Lu")
	private Long lu;
	
	@NonNull
    @Column(name = "alu_Estado", nullable = false)
	private boolean estado;
	
	@Autowired
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CARRERA_ID")
	private Carrera carrera;
}