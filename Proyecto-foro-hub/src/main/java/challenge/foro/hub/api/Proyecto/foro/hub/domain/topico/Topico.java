package challenge.foro.hub.api.Proyecto.foro.hub.domain.topico;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.curso.Curso;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.DTO.DatosNuevoTopico;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(@Valid DatosNuevoTopico topico) {
        titulo = topico.titulo();
        mensaje = topico.mensaje();
        fecha_creacion = LocalDateTime.now();
        status = Status.ABIERTO;
        autor = new Usuario(topico.autor());
        curso = new Curso(topico.curso());
    }

    public void actualizarDatos(DatosNuevoTopico nuevoTopico){
        if(nuevoTopico.titulo() != null){
            this.titulo = nuevoTopico.titulo();
        }
        if(nuevoTopico.mensaje() != null){
            this.mensaje = nuevoTopico.mensaje();
        }
        if(nuevoTopico.autor() != null){
            this.autor = new Usuario(nuevoTopico.autor());
        }
        if(nuevoTopico.curso() != null){
            this.curso = new Curso(nuevoTopico.curso());
        }
    }
}
