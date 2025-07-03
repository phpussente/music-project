package br.com.abramus.apimusica.controller;

import br.com.abramus.apimusica.dto.MusicaDTO;
import br.com.abramus.apimusica.model.Musica;
import br.com.abramus.apimusica.repository.MusicaRepository;
import br.com.abramus.apimusica.form.MusicaForm;
import jakarta.validation.Valid;
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
    public List<MusicaDTO> listar() {
        return repository.findAll()
                .stream()
                .map(MusicaDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaDTO> buscarPorId(@PathVariable Long id) {
        Optional<Musica> musicaOptional = repository.findById(id);
        if (musicaOptional.isPresent()){
            MusicaDTO dto = MusicaDTO.fromEntity(musicaOptional.get());
            return ResponseEntity.ok(dto);
        } else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<MusicaDTO> cadastrar(@RequestBody @Valid MusicaForm form) {
        Musica musica = form.toEntity();
        Musica salva = repository.save(musica);
        return ResponseEntity.status(201).body(MusicaDTO.fromEntity(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MusicaForm form) {
        Optional<Musica> musicaOptional = repository.findById(id);
        if(musicaOptional.isPresent()) {
            Musica musica = musicaOptional.get();
            musica.setTitulo(form.getTitulo());
            musica.setArtista(form.getArtista());

            Musica atualizada = repository.save(musica);
            return ResponseEntity.ok(MusicaDTO.fromEntity(atualizada));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Musica> musicaOptional = repository.findById(id);
        if (musicaOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
