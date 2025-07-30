package challenge.foro.hub.api.Proyecto.foro.hub.domain.curso.DTO;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.curso.Curso;

public record DatosCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DatosCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
