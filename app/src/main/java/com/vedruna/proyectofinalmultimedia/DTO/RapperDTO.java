package com.vedruna.proyectofinalmultimedia.DTO;


/**
 * Esta clase representa un objeto de transferencia de datos (DTO) para un rapero.
 * Contiene información básica sobre un rapero, incluido su nombre, ciudad, álbum y URL de imagen.
 */
public class RapperDTO {

    private String name;
    private String city;
    private String album;
    private String imageURL;

    public RapperDTO(){

    }

    public RapperDTO(String name, String city, String album, String imageURL) {
        this.name = name;
        this.city = city;
        this.album = album;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
