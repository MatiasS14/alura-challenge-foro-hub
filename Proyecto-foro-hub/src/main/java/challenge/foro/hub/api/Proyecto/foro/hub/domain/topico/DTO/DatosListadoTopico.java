package challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.DTO;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.curso.DTO.DatosCurso;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.Status;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.Topico;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.DTO.DatosUsuario;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        LocalDateTime fecha_creacion,
        Status status,
        DatosUsuario nombreAutor,
        DatosCurso nombreCurso
) {
    public DatosListadoTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(), topico.getFecha_creacion(),
             topico.getStatus(),new DatosUsuario(topico.getAutor()),
             new DatosCurso(topico.getCurso()));
    }
}
