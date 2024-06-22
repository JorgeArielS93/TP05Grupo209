package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
		/* @Column(name = "curso", nullable = false) */
		private String curso;
	 
	 @NonNull
	 @Column(name = "estado", nullable = false) 
	private boolean estado;
	 
	 @NonNull
	 @Column(name = "modalidad", nullable = false) 
	private String modalidad;
    
	
	@NonNull
	 @Column(name = "nombre", nullable = false) 
	private String nombre;
	

   
    

    
    
    

    

}
