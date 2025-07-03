package br.com.abramus.apimusica.repository;

import br.com.abramus.apimusica.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long>{}
