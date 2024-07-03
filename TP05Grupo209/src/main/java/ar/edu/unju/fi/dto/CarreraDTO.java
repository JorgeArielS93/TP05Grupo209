package ar.edu.unju.fi.dto;

import java.util.List;
import org.springframework.stereotype.Component;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class CarreraDTO {

    private Long id;

    @NotBlank(message = "Debe ingresar el código de la carrera")
    @Size(min=2, max=10, message = "El código debe contener como mínimo 2 caracteres y como máximo 10 caracteres")
    private String codigo;

    @NotBlank(message = "Debe ingresar el nombre de la carrera")
    @Size(min=2, max=100, message = "El nombre debe contener como mínimo 2 caracteres y como máximo 100 caracteres")
    private String nombre;

    @Min(value = 1, message = "La duración mínima es de 1 año")
    @Max(value = 5, message = "La duración máxima es de 5 años")
    private int duracion;

    private boolean estado;

    private List<AlumnoDTO> alumnos;

    private List<MateriaDTO> materias; 
}
