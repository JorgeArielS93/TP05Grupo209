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
@Entity(name = "DOCENTES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_Legajo")
    private Long legajo;

    @NonNull
    @Column(name = "doc_Nombre", nullable = false)
    private String nombre;

    @NonNull
    @Column(name = "doc_Apellido", nullable = false)
    private String apellido;

    @NonNull
    @Column(name = "doc_Email", nullable = false)
    private String email;

    @NonNull
    @Column(name = "doc_Telefono", nullable = false)
    private String telefono;
    
    @OneToMany(mappedBy = "profesor",cascade = CascadeType.ALL)
    private List<Materia> materias=new ArrayList<Materia>();

    @NonNull
    @Column(name = "doc_Estado", nullable = false)
    private boolean estado;
}