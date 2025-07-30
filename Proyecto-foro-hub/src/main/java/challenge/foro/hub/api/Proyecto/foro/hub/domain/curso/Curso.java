package challenge.foro.hub.api.Proyecto.foro.hub.domain.curso;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.curso.DTO.DatosCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

    public Curso(DatosCurso curso) {
        id = curso.id();
        nombre = curso.nombre();
        categoria = curso.categoria();
    }
}