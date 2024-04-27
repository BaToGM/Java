package dam.camarasmadrid.modelo;

import java.io.Serializable;

public class Camara implements Serializable {
    private String nombre;
    private String url;
    private String coordenadas;
    public Camara (String nombre, String url, String coordenadas) {
        this.nombre=nombre;
        this.url=url;
        this.coordenadas=coordenadas;
    }
    public String getNombre(){
        return nombre;
    }
    public String getCoordenadas() {
        return coordenadas;
    }
    public String getURL(){ return url;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setURL(String url){this.url =   url;}
    public void setCoordenadas(String coordenadas){this.coordenadas = coordenadas;}

    public String toString() {
        return nombre;
    }
}
