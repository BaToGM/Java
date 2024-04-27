package dam.camarasmadrid.modelo;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaCamaras extends ViewModel {
    private ArrayList<Camara> listaCamaras = new ArrayList<>();
    public ArrayList<Camara> getListaCamara() {
        return listaCamaras;
    }
    public void appendListaCamara(Camara camara){
        listaCamaras.add(camara);
    }
    public void actualizarLista(ArrayList<Camara> nuevaLista) {
        listaCamaras.clear();
        listaCamaras.addAll(nuevaLista);
    }

}
