package dam.parsercamaras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.widget.TextView;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvCameraData;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCameraData = findViewById(R.id.tvCameraData);
        tvTitle = findViewById(R.id.tvTitle);
        loadKMLData();
    }

    private void loadKMLData() {
        try {
            String fileName = "CamarasMadridReducido.kml";  // Nombre del archivo que estás leyendo
            InputStream is = getAssets().open(fileName);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            AnalizadorXML handler = new AnalizadorXML();
            saxParser.parse(is, handler);

            String resultado = handler.getResultado();
            runOnUiThread(() -> {
                tvCameraData.setText(resultado);
                tvTitle.setText("Contenido del fichero: " + fileName); // Establece el título con el nombre del archivo
            });

        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(() -> tvCameraData.setText("Error al leer el archivo KML"));
        }
    }
}