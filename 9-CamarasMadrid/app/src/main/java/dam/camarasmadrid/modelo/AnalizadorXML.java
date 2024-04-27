package dam.camarasmadrid.modelo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import dam.camarasmadrid.ContadorCallback;

public class AnalizadorXML extends DefaultHandler {
    private String nombre;
    private String imageUrl;
    private String coordinates;
    private StringBuilder contenido;
    private boolean esNombre;
    private boolean esCoordinates;
    private StringBuilder resultado;
    private String tipo;
    private ListaCamaras listaCamaras;
    private ContadorCallback contadorCallback;

    public AnalizadorXML(ContadorCallback contadorCallback) {
        this.contadorCallback = contadorCallback;
    }

    public String getResultado() {
        StringBuilder resultadoAux = resultado;
        resultado.setLength(0);
        return resultadoAux.toString();
    }
    public String getContenido(){return contenido.toString();}

    @Override
    public void startElement(String namespaceURI, String nombreLocal,
                             String nombreCualif, Attributes atributos) throws SAXException {
        try {
            Thread.sleep(1); // Detener el programa durante 10 milisegundos
        } catch (InterruptedException e) {
            // Manejar la excepción si ocurre algún error durante la pausa
            e.printStackTrace();
        }
        super.startElement(namespaceURI, nombreLocal, nombreCualif, atributos);
        resultado = new StringBuilder();
        contenido = new StringBuilder();
        contenido.setLength(0);
        tipo = null;
        if ("Data".equals(nombreLocal)) {
            if ("Nombre".equals(atributos.getValue("name"))) {
                esNombre = true;
            }
        } else if ("Point".equals(nombreLocal)) {
            esCoordinates = true;
        }
    }

    @Override
    public void characters(char ch[], int comienzo, int longitud) throws SAXException {
        super.characters(ch, comienzo, longitud);
        contenido.append(ch, comienzo, longitud);
    }

    @Override
    public void endElement(String namespaceURI, String nombreLocal, String nombreCualif) throws SAXException {
        super.endElement(namespaceURI, nombreLocal, nombreCualif);
        switch (nombreLocal) { // Procesar las etiquetas que nos interesan
            case "Value":
                if (esNombre) {
                    tipo = "nombre";
                    nombre = contenido.toString().trim();
                    resultado.append(nombre);
                    esNombre = false;
                    contadorCallback.elementoEncontrado("nombre");
                }
                break;
            case "description":
                tipo = "description";
                imageUrl = contenido.toString().trim();
                resultado.append(imageUrl);
                contadorCallback.elementoEncontrado("description");
                break;
            case "coordinates":
                tipo = "coordinates";
                coordinates = contenido.toString().trim();
                resultado.append(coordinates);
                esCoordinates = false;
                contadorCallback.elementoEncontrado("coordinates");
                break;
        }
        contenido.setLength(0);

    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

}
