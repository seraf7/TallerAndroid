package com.example.gridweb.model;

public class Imagen {
    int id;
    String url_img;

    public Imagen(int id, String url_img) {
        this.id = id;
        this.url_img = url_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
}
