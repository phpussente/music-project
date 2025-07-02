package br.com.abramus.api_musica.controller;

import br.com.abramus.api_musica.model.Musica;
import br.com.abramus.api_musica.repository.MusicaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaRepository repository;

    public MusicaController (MusicaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Musica> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Musica> cadastrar(@RequestBody Muisica musica) {
        Musica salva = repository.save(musica);
        return ResponseEntity.status(201).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable Long id, @RequestBody Musica atualizada) {
        return repository.findById(id)
                .map(m -> {
                    m.setTitulo(atualizada.getTitulo());
                    m.setArtistas(atualizada.getArtistas());
                    return ResponseEntity.ok(repository.save(m));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
