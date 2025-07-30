package challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.DTO;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.curso.DTO.DatosCurso;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.Status;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.DTO.DatosUsuario;

import java.time.LocalDateTime;

public record DatosNuevoTopico(
        String titulo,
        String mensaje,
        DatosUsuario autor,
        DatosCurso curso
) {
}
