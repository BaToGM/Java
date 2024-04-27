package dam.camarasmadrid;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dam.camarasmadrid.modelo.Camara;

public class DetalleCamaraFragmento extends Fragment {

    private TextView nombre;
    private TextView coordenadas;
    private TextView url;

    public DetalleCamaraFragmento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_camara_fragmento, container, false);
        nombre = view.findViewById(R.id.nombre);
        coordenadas = view.findViewById(R.id.coordenadas);
        url = view.findViewById(R.id.url);

        // Recuperar el país de los argumentos
        if (getArguments() != null && getArguments().containsKey("camara")) {
            Camara camara = (Camara) getArguments().getSerializable("camara");
            showCamara(camara);
        }
        return view;
    }

    @SuppressLint("SetTextI18n")
    public void showCamara(Camara camara) {
        if (camara != null) {
            nombre.setText("Nombre: "+ camara.getNombre());
            coordenadas.setText("Coordenadas:" + camara.getCoordenadas());
            url.setText("Url:" + camara.getURL());
        }
    }
}