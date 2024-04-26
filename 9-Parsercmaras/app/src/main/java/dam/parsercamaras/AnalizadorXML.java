package dam.parsercamaras;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class AnalizadorXML extends DefaultHandler {
    private String nombre;
    private String imageUrl;
    private String coordinates;
    private StringBuilder contenido;
    private boolean esNombre, esCoordinates;
    private StringBuilder resultado;

    public String getResultado() {
        return resultado.toString();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        contenido = new StringBuilder();
        resultado = new StringBuilder();
        esNombre = false;
        esCoordinates = false;
    }

    @Override
    public void startElement(String namespaceURI, String nombreLocal, String nombreCualif, Attributes atributos) throws SAXException {
        super.startElement(namespaceURI, nombreLocal, nombreCualif, atributos);
        contenido.setLength(0); // Preparar el StringBuilder para un nuevo elemento
        if ("Data".equals(nombreLocal)) {
            if ("Nombre".equals(atributos.getValue("name"))) {
                esNombre = true;
            }
        } else if ("Point".equals(nombreLocal)) {
            esCoordinates = true;
        }
    }

    @Override
    public void characters(char ch[], int comienzo, int longitud) {
        contenido.append(ch, comienzo, longitud);
    }

    @Override
    public void endElement(String namespaceURI, String nombreLocal, String nombreCualif) throws SAXException {
        super.endElement(namespaceURI, nombreLocal, nombreCualif);
        if ("Value".equals(nombreLocal) && esNombre) {
            nombre = contenido.toString().trim();
            esNombre = false;
        } else if ("description".equals(nombreLocal)) {
            imageUrl = contenido.toString().trim();
        } else if ("coordinates".equals(nombreLocal) && esCoordinates) {
            coordinates = contenido.toString().trim();
            esCoordinates = false;
        } else if ("Placemark".equals(nombreLocal)) {
            resultado.append("Nombre: ").append(nombre)
                    .append("\nURL: ").append(imageUrl)
                    .append("\nCoordenadas: ").append(coordinates)
                    .append("\n\n");
        }
        contenido.setLength(0); // Limpiar el StringBuilder después de procesar un elemento
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
