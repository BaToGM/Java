package dam.parsercamaras;

public class CameraInfo {
    private String numero;
    private String nombre;
    private String imageUrl;
    private String coordinates;

    // Constructor, getters y setters

    public CameraInfo(String numero, String nombre, String imageUrl, String coordinates) {
        this.numero = numero;
        this.nombre = nombre;
        this.imageUrl = imageUrl;
        this.coordinates = coordinates;
    }

    // Getters y setters para cada propiedad

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Cámara " + numero + ": " + nombre + "\nURL: " + imageUrl + "\nCoordenadas: " + coordinates + "\n\n";
    }
}
