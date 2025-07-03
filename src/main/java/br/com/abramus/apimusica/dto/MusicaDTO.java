package br.com.abramus.apimusica.dto;

import br.com.abramus.apimusica.model.Musica;

public class MusicaDTO {

    private Long id;
    private String titulo;
    private String artista;

    public MusicaDTO() {}

    public MusicaDTO(Long id, String titulo, String artista) {
        this.artista = artista;
        this.titulo = titulo;
        this.id = id;
    }

    MusicaDTO dto = MusicaDTO.fromEntity(musica);

    public static MusicaDTO fromEntity(Musica musica) {
        return new MusicaDTO(musica.getId(), musica.getArtista(), musica.getTitulo());
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getArtista() {
        return this.artista;
    }
}
