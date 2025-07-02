package br.com.abramus.api_musica.model;

import jakarta.persistence.*;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String artistas;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public void setArtistas(String artistas) {
        this.artistas = artistas;
    }

    public String getArtistas() {
        return this.artistas;
    }
}
