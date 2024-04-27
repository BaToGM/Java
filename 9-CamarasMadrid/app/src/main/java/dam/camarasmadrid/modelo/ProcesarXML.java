package dam.camarasmadrid.modelo;

import android.annotation.SuppressLint;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import dam.camarasmadrid.ContadorCallback;
import dam.camarasmadrid.ListadoCamarasFragmento;
import dam.camarasmadrid.MainActivity;
import dam.camarasmadrid.R;

public class ProcesarXML implements  Runnable, ContadorCallback {

    private final MainActivity instanciaClasePrincipal;
    private ProgressBar progresoCircular;
    private TextView tvContadorCamaras;
    private TextView tvAnterior;
    private TextView tvCargandoCamaras;
    private  ProgressBar progressBarAnterior;
    private Boolean retardo;
    private Integer contador;
    private ListaCamaras listaCamaras;
    private Fragment fragment;
    private Camara camaraAux;
    private AnalizadorXML analizadorXML;
   // private ListadoCamarasFragmento fragmentoLista;


    public ProcesarXML(MainActivity instanciaClasePrincipal){
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

        doInBackground("CamarasMadridCompletoInterna.kml");

        instanciaClasePrincipal.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onPostExecute();
            }
        });

    }

    private void onPreExecute() {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;
        listaCamaras = new ViewModelProvider(instanciaClasePrincipal).get(ListaCamaras.class);
        progresoCircular = instanciaClasePrincipal.findViewById(R.id.barraProgresoCircular);
        progressBarAnterior = instanciaClasePrincipal.findViewById(R.id.barraProgreso);
        tvContadorCamaras = instanciaClasePrincipal.findViewById(R.id.tvCamarasProcesadas);
        tvAnterior = instanciaClasePrincipal.findViewById(R.id.tvEspereMientras);
        tvCargandoCamaras = instanciaClasePrincipal.findViewById(R.id.tvCargandoCamaras);

        tvAnterior.setVisibility(View.GONE);
        progressBarAnterior.setVisibility(View.GONE);
        progresoCircular.setVisibility(View.VISIBLE);
        tvContadorCamaras.setVisibility(View.VISIBLE);
        contador = 0;
        tvContadorCamaras.setText(contador.toString());
        tvCargandoCamaras.setVisibility(View.VISIBLE);
        retardo = false;

        FragmentManager fragmentManager = instanciaClasePrincipal.getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.listadoCamarasFragmento);

    }
    private void doInBackground(String nombreFichero) {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;
        camaraAux = new Camara("", "", "");
        try {
            final InputStream inputStream = instanciaClasePrincipal.openFileInput(nombreFichero);
            final BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));
            final SAXParserFactory fab = SAXParserFactory.newInstance();
            fab.setNamespaceAware(true);
            SAXParser analizadorSAX = fab.newSAXParser();
            analizadorXML = new AnalizadorXML(this);
            analizadorSAX.parse (inputStream, analizadorXML);
            inputStream.close();
        } catch (FileNotFoundException e) {
            Log.e ("Mensaje","El fichero no existe");
        } catch (IOException e) {
            Log.e ("Mensaje","Error al recuperar el fichero "+e.getMessage());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
    private void onProgressUpdate() {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;
        contador = contador+1;
        tvContadorCamaras.setText(contador.toString());
    }
    private void onPostExecute() {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;
        progresoCircular.setVisibility(View.GONE);
        tvContadorCamaras.setVisibility(View.GONE);
        tvCargandoCamaras.setVisibility(View.GONE);

        ListadoCamarasFragmento listadoCamarasFragmento = (ListadoCamarasFragmento) fragment;
        listadoCamarasFragmento.actualizarLista(listaCamaras);
        View fragmentView = listadoCamarasFragmento.getView();
        if (fragmentView != null) {
            fragmentView.setVisibility(View.VISIBLE);
        }

    }

    private void publishProgress() {
        if (instanciaClasePrincipal == null || instanciaClasePrincipal.isFinishing())
            return;

        // Se ejecuta en el hilo de trabajo
        instanciaClasePrincipal.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate();
            }
        });
    }

    @Override
    public void elementoEncontrado(String tipo) {
        if(tipo == "description"){
            camaraAux.setURL(analizadorXML.getContenido());
        } else if (tipo == "nombre") {
            camaraAux.setNombre(analizadorXML.getContenido());
        }
        if(tipo == "coordinates"){
            camaraAux.setCoordenadas(analizadorXML.getContenido());
            listaCamaras.appendListaCamara(camaraAux);
            publishProgress();
            camaraAux = new Camara(null, null, null);
        }
    }

}
