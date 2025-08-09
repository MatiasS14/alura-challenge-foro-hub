package challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.DTO.DatosNuevoUsuario;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.DTO.DatosUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
//Implementa interfaz UserDetails para security
public class Usuario implements UserDetails {
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
    }

    public Usuario(DatosNuevoUsuario nuevoUsuario) {
        nombre = nuevoUsuario.nombre();
        email = nuevoUsuario.email();
        usuario = nuevoUsuario.usuario();
        contrasena = nuevoUsuario.contrasena();
    }

    //Configuracion Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


}
