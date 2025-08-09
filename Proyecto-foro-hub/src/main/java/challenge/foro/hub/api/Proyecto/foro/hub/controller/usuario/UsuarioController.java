package challenge.foro.hub.api.Proyecto.foro.hub.controller.usuario;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.DTO.DatosNuevoUsuario;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.DTO.DatosUsuario;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.Usuario;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosUsuario> crearUsuario(@RequestBody DatosNuevoUsuario datosUsuario, UriComponentsBuilder uricomp){
        var password = passwordEncoder.encode(datosUsuario.contrasena());
        var nuevoUser = new Usuario(null,datosUsuario.nombre(), datosUsuario.email(), datosUsuario.usuario(), password);
        Usuario usuario = usuarioRepository.save(nuevoUser);

        DatosUsuario usuarioRet = new DatosUsuario(usuario);

        URI url = uricomp.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(usuarioRet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosUsuario> buscarPorId(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
            DatosUsuario usuarioRet = new DatosUsuario(usuario.get());
            return ResponseEntity.ok(usuarioRet);
    }
}
