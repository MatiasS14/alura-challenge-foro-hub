package challenge.foro.hub.api.Proyecto.foro.hub.controller.topico;

import challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.DTO.DatosListadoTopico;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.DTO.DatosNuevoTopico;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.DTO.DatosTopico;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.Topico;
import challenge.foro.hub.api.Proyecto.foro.hub.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosTopico> nuevoTopico(@RequestBody @Valid DatosNuevoTopico datosNuevoTopico, UriComponentsBuilder uriComp){
        Topico topico = topicoRepository.save(new Topico(datosNuevoTopico));
        DatosTopico datosTopico = new DatosTopico(topico);
        URI url = uriComp.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosTopico> findById(@PathVariable Long id){
        Topico topico = topicoRepository.findById(id).get();

        return ResponseEntity.ok(new DatosTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> getTopicos(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosTopico> updateTopico(@RequestBody @Valid DatosNuevoTopico topico, @PathVariable Long id){
        var topicoUp = topicoRepository.findById(id);
        if (topicoUp.isPresent()){
            var topicoEnt = topicoUp.get();
            topicoEnt.actualizarDatos(topico);
            return ResponseEntity.ok(new DatosTopico(topicoEnt));
        }else {
            throw new RuntimeException("No se encontro el topico");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopico(@PathVariable Long id){
        if(topicoRepository.findById(id).isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
