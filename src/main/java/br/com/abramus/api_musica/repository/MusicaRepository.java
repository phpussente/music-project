package br.com.abramus.api_musica.repository;

import br.com.abramus.api_musica.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long>{}
