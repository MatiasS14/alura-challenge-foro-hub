package challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.perfil.Perfil;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.DTO.DatosUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String usuario;
    private String contrasena;

    public Usuario(DatosUsuario autor) {
        id = autor.id();
        nombre = autor.nombre();
        email = autor.email();
        usuario = autor.usuario();
        contrasena = autor.contrasena();
    }
}
