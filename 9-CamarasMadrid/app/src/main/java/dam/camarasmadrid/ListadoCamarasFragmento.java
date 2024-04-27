package dam.camarasmadrid;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dam.camarasmadrid.modelo.Camara;
import dam.camarasmadrid.modelo.ListaCamaras;

public class ListadoCamarasFragmento extends Fragment {

    private String mParam1;
    private String mParam2;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ListaCamaras listaCamaras;
    private List<String> nombresCamaras;
    private Callbacks activity;

    public ListadoCamarasFragmento() {
        // Required empty public constructor
    }

    public interface Callbacks {
        void onItemSelected(Camara camara);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //listaCamaras = new ListaCamaras();
        //adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_single_choice, listaCamaras.getListaCamara());
        //adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_checked, listaCamaras.getListaCamara());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.listado_camaras_fragmento, container, false);
        // Obtener la referencia del ListView desde el diseño
        listaCamaras = new ListaCamaras();
        nombresCamaras = new ArrayList<>();
        for (Camara camara : listaCamaras.getListaCamara()) {
            nombresCamaras.add(camara.getNombre());
        }
        listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_checked, nombresCamaras);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> activity.onItemSelected(getCamara(adapter.getItem(position))));
        return view;
    }

    // Método para actualizar el ListView con la lista proporcionada
    public void actualizarLista(ListaCamaras lista) {
        ArrayList<Camara> listaCamara = lista.getListaCamara();
        listaCamaras.actualizarLista(listaCamara);
        actualizarListView();
    }

    // Método para actualizar el ListView
    private void actualizarListView() {
        List<String> nombresCamaras2 = new ArrayList<>();
        for (Camara camara : listaCamaras.getListaCamara()) {
            nombresCamaras2.add(camara.getNombre());
        }
        nombresCamaras.clear();
        nombresCamaras.addAll(nombresCamaras2);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Callbacks) context;
    }

    public Camara getCamara(String  nombre){
        Camara camara = new Camara(null, null,null);
        for(int i=0;i<listaCamaras.getListaCamara().size(); i++){
            if(listaCamaras.getListaCamara().get(i).getNombre().equals(nombre))
                camara = listaCamaras.getListaCamara().get(i);
        }
        return camara;
    }

}