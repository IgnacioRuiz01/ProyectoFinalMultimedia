package com.vedruna.proyectofinalmultimedia.Model;


/**
 * Esta clase representa un objeto Rapper que contiene información sobre un rapero, incluido su ID, nombre,
 * ciudad, álbum y URL de imagen.
 */
public class Rapper {
    private int idRapper;
    private String name;
    private String city;
    private String album;
    private String imageURL;

    public Rapper(){

    }

    public Rapper(int idRapper, String name, String city, String album, String imageURL) {
        this.idRapper = idRapper;
        this.name = name;
        this.city = city;
        this.album = album;
        this.imageURL = imageURL;
    }

    public int getIdRapper() {
        return idRapper;
    }

    public void setIdRapper(int idRapper) {
        this.idRapper = idRapper;
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

    @Override
    public String toString() {
        return "Rapper{" +
                "idRapper=" + idRapper +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", album='" + album + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
