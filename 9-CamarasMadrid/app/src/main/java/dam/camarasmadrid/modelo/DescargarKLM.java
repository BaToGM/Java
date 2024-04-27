package dam.camarasmadrid.modelo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import dam.camarasmadrid.MainActivity;
import dam.camarasmadrid.R;

public class DescargarKLM implements  Runnable{

    private final MainActivity instanciaClasePrincipal;
    // El constructor recibe como parámetro la instancia de la clase principal para poder acceder a sus atributos
    // y métodos públicos, en onPreExecute, onProgressUpdate y onPostExecute

    private ProgressBar barraProgreso;
    private TextView tvEspere;


    public DescargarKLM(MainActivity instanciaClasePrincipal){
        this.instanciaClasePrincipal=instanciaClasePrincipal;
    }


    @Override
    public void run() {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;

        instanciaClasePrincipal.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onPreExecute();
            }
        });

        doInBackground();

        instanciaClasePrincipal.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onPostExecute();
            }
        });

        ProcesarXML procesarXML = new ProcesarXML(instanciaClasePrincipal);
        new Thread(procesarXML).start();
    }

    private void onPreExecute() {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;
        tvEspere = instanciaClasePrincipal.findViewById(R.id.tvEspereMientras);
        barraProgreso = instanciaClasePrincipal.findViewById(R.id.barraProgreso);
        tvEspere.setVisibility(View.VISIBLE);
        barraProgreso.setVisibility(View.VISIBLE);
    }
    private void doInBackground() {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;
        int totalSeconds = 3;
        int segundosUpdate = 0;
        for (int i = 1; i <= totalSeconds; i++) {
            try {
                Thread.sleep(1000); // Esperar 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            segundosUpdate = (i * 100) / totalSeconds;
            publishProgress(segundosUpdate); // Actualizar el progreso
        }
        escrituraFichero("CamarasMadridCompletoInterna.kml");

    }
    private void onProgressUpdate(Integer... values) {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;
        barraProgreso.setProgress(values[0]);
    }
    private void onPostExecute() {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;
        barraProgreso.setProgress(100); // Establecer el progreso al 100% al finalizar
        instanciaClasePrincipal.descargado();
    }

    private void publishProgress(int segundos) {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;

        // Se ejecuta en el hilo de trabajo
        instanciaClasePrincipal.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(segundos);
            }
        });
    }

    @SuppressLint("SdCardPath")
    private void escrituraFichero(String nombreFichero) {
        try {
            final FileOutputStream fileOutStream = instanciaClasePrincipal.openFileOutput(nombreFichero, Context.MODE_PRIVATE);
            final BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(fileOutStream));
            buffWriter.write(leerAssets("CamarasMadridCompleto.kml"));
            buffWriter.close();
            Log.d ("Mensaje","El fichero se ha guardado en "+ instanciaClasePrincipal.getFilesDir() + File.separator+nombreFichero);
        } catch (IOException e) {
            Log.e ("Mensaje","Error al guardar el fichero "+e.getMessage());
        }
    }

    private String leerAssets(String fichero){
        final StringBuilder respuesta= new StringBuilder();
        String linea;
        // Leer fichero Asset
        final AssetManager am = instanciaClasePrincipal.getAssets();
        try {
            final InputStream is= am.open(fichero);
            final BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(is));
            while ((linea = bufferedReader.readLine()) != null) {
                respuesta.append(linea).append("\n");
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d ("Mensaje","Error al recuperar el fichero"
                    +e.toString());
        }
        return respuesta.toString();
    }


}
