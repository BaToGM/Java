package dam.camarasmadrid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import dam.camarasmadrid.modelo.Camara;
import dam.camarasmadrid.modelo.DescargarKLM;
import dam.camarasmadrid.modelo.ProcesarXML;

public class MainActivity extends AppCompatActivity implements ListadoCamarasFragmento.Callbacks {
    private boolean descargado;
    private FragmentContainerView fragmentoLista;
    private FragmentContainerView framentoDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        descargado = false;
        DescargarKLM descargarKLM= new DescargarKLM(MainActivity.this);
        new Thread(descargarKLM).start();

    }

    public void descargado(){descargado = true;}

    @Override
    public void onItemSelected(Camara camara) {
        // Lanza DetailActivity y pasa el objeto Pais como un extra
        if (camara != null) {
            fragmentoLista = findViewById(R.id.listadoCamarasFragmento);
            framentoDetalle = findViewById(R.id.detalleCamaraFragmento);

            LinearLayout.LayoutParams paramsLista = (LinearLayout.LayoutParams)fragmentoLista.getLayoutParams();
            LinearLayout.LayoutParams paramsDetalle = (LinearLayout.LayoutParams)framentoDetalle.getLayoutParams();
            paramsDetalle.weight = 2;
            paramsLista.weight = 1;
            fragmentoLista.setLayoutParams(paramsLista);
            framentoDetalle.setLayoutParams(paramsDetalle);

            DetalleCamaraFragmento detailFragment = new DetalleCamaraFragmento();
            Bundle args = new Bundle();
            args.putSerializable("camara", camara);
            detailFragment.setArguments(args);
            if (getSupportFragmentManager().findFragmentById(R.id.detalleCamaraFragmento)!= null){
                Fragment idFragmento = getSupportFragmentManager().findFragmentById(R.id.detalleCamaraFragmento);
                getSupportFragmentManager().beginTransaction().remove(idFragmento).commit();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.detalleCamaraFragmento, detailFragment);
                transaction.commit();
            }else{
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.detalleCamaraFragmento, detailFragment);
                transaction.commit();
            }



        }
    }

}