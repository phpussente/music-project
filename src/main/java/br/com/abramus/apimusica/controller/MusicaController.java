package br.com.abramus.apimusica.controller;

import br.com.abramus.apimusica.dto.MusicaDTO;
import br.com.abramus.apimusica.model.Musica;
import br.com.abramus.apimusica.repository.MusicaRepository;
import br.com.abramus.apimusica.form.MusicaForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaRepository repository;

    public MusicaController (MusicaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MusicaDTO> listar() {
        return repository.findAll().stream().map(MusicaDTO::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MusicaDTO> cadastrar(@RequestBody @Valid MusicaForm form) {
        Musica musica = form.toEntity();
        Musica salva = repository.save(musica);
        return ResponseEntity.status(201).body(MusicaDTO.fromEntity(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable Long id, @RequestBody Musica atualizada) {
        return repository.findById(id)
                .map(m -> {
                    m.setTitulo(atualizada.getTitulo());
                    m.setArtista(atualizada.getArtista());
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
