package com.example.sivartravel.user.inicio;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sivartravel.MenuUser;
import com.example.sivartravel.R;
import com.example.sivartravel.Splash;
import com.example.sivartravel.entidades.Lugares;
import com.example.sivartravel.info_lugar_fragmento;
import com.example.sivartravel.restservice.RetrofitClient;
import com.example.sivartravel.restservice.ServicioApi;
import com.example.sivartravel.user.adaptador.InterfaceClickListener;
import com.example.sivartravel.user.adaptador.RecyclerViewAdapter;
import com.example.sivartravel.user.entitys.LugaresEntity;
import com.example.sivartravel.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Isaias Ortiz
 */
public class Inicio extends Fragment  implements InterfaceClickListener {
    RecyclerView rvLugares;
    ArrayList<LugaresEntity> all;
    List<Lugares> respuesta = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.user_inicio, container, false);

        rvLugares = (RecyclerView)root.findViewById(R.id.rvLugares);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ServicioApi service = RetrofitClient.getSOService();
        Call<List<com.example.sivartravel.entidades.Lugares>> repos = service.getLugares(4);
        try {
            repos.enqueue(new Callback<List<com.example.sivartravel.entidades.Lugares>>() {
                @Override
                public void onResponse(Call<List<Lugares>> call, Response<List<Lugares>> response) {
                    List<Lugares> lugaresLista = response.body();
                    getLugares(lugaresLista);
                    rvLugares.setLayoutManager(new LinearLayoutManager(getContext()));
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(all, Inicio.this);
                    rvLugares.setAdapter(adapter);
                }
                @Override
                public void onFailure(Call<List<Lugares>> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

       // lugares();



        for(Lugares l : respuesta){

            System.out.println(l.getIdMunicipio().getMunicipio());
            System.out.println(l.getDescripcion());
        }

        return root;
    }

    private ArrayList<LugaresEntity> getLugares(List<Lugares> lugaresLista){
        all= new ArrayList<>();
        for(Lugares lugar : lugaresLista)
        {
            all.add(new LugaresEntity(lugar.getImagen(), ""+lugar.getNombre(),""+lugar.getIdMunicipio().getIdDepartamentos().getDepartamentos(), ""+lugar.getDescripcion(),""+lugar.getLocalizacion(),"9:00 am - 17:00 pm", "",""));
        }


        //all.add(new LugaresEntity(R.drawable.main, "Suchitoto","Suchitoto", "Pueblo vivo","Suchitoto (en idioma Náhuat \"Shuchitutut\" (Flor-Pájaro) es un municipio del departamento de Cuscatlán, El Salvador.\n Su territorio ha sido habitado desde la época precolombina, y también fue el sitio donde se fundó la villa de San Salvador en 1528, que tuvo una breve existencia.","6:00 am - 20:00 pm", "",""));
        //all.add(new LugaresEntity(R.drawable.main, "Apaneca","Ahuachapan", "Pueblo vivo","Ahuachapán fue fundado en el siglo V por los mayas de la tribu pokomames, y sometida en el siglo XV por belicosos Náhuas de Kuskatan de los izalcos.\n Gradualmente la región fue invadida por los españoles.","24/7", "",""));
        return all;
    }

    @Override
    public void OnItemClick(int position) {
        
        if(all != null && all.size() > 0)
        {
            info_lugar_fragmento fr=new info_lugar_fragmento();
            Bundle args=new Bundle();
            String jsonLugares= JsonUtil.getGsonParser().toJson(all.get(position));

            args.putString("LugaresEntity", jsonLugares );
            fr.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, fr);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(null);
            transaction.commit();

            Toast.makeText(getContext(), all.get(position).getDepartamento() + " "  + all.get(position).getLugar() , Toast.LENGTH_SHORT).show();
        }
    }
}