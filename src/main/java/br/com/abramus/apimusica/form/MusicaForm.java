package br.com.abramus.apimusica.form;

import br.com.abramus.apimusica.model.Musica;
import jakarta.validation.constraints.NotBlank;

public class MusicaForm {

    @NotBlank
    private String artista;

    @NotBlank
    private String titulo;


    public Musica toEntity(){
        Musica musica = new Musica();
        musica.setTitulo(this.titulo);
        musica.setArtista(this.artista);
        return musica;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getArtista() {
        return this.artista;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
