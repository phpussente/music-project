package br.com.abramus.apimusica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório!")
    private String titulo;

    @NotBlank(message = "O artista é obrigatório!")
    private String artista;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getArtista() {
        return this.artista;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
