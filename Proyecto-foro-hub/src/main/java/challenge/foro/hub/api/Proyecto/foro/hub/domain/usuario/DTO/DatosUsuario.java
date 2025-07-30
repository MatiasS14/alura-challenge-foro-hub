package challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.DTO;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.Usuario;

public record DatosUsuario(
        Long id,
        String nombre,
        String email,
        String usuario,
        String contrasena
) {
    public DatosUsuario(Usuario autor) {
        this(autor.getId(),autor.getNombre(), autor.getEmail(),autor.getUsuario(),autor.getContrasena());
    }
}
