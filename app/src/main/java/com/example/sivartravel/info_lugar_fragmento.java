package com.example.sivartravel;

import android.media.Image;
import android.media.tv.TvView;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.sivartravel.user.entitys.LugaresEntity;
import com.example.sivartravel.util.JsonUtil;

import java.util.function.LongUnaryOperator;

public class info_lugar_fragmento extends Fragment {
    ViewFlipper carrusel;
    private  LugaresEntity lugarSelect;
    TextView tvLugar, tvInformacion, tvUbicacion, tvDescripcion, tvHorario;
    public info_lugar_fragmento() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info_lugar_fragmento, container, false);
        carrusel = root.findViewById(R.id.carrusel);
        int imagenes[] = {R.drawable.apaneca,
                          R.drawable.rutaflores,
                          R.drawable.suchitoto};
        tvLugar=root.findViewById(R.id.tvLugar);
        tvInformacion=root.findViewById(R.id.tvInformacion);
        tvUbicacion =root.findViewById(R.id.tvUbicacion);
        tvDescripcion= root.findViewById(R.id.tvDescripcion);
        tvHorario=root.findViewById(R.id.tvHorario);
        for(int img : imagenes)
        {
           cargarImagenes(img);
        }
        Bundle argumentos=this.getArguments();
        String lugar =argumentos.getString("LugaresEntity");
        System.out.println("lugar = " + lugar);
        lugarSelect= JsonUtil.getGsonParser().fromJson(lugar, LugaresEntity.class);
        cargarInfo();
        return root;

    }
public void cargarInfo(){
    tvLugar.setText(lugarSelect.getLugar());
    tvInformacion.setText(lugarSelect.getInformacion());
    tvUbicacion.setText("Ubicacion: "+lugarSelect.getDepartamento());
    tvDescripcion.setText("Descripcion: "+lugarSelect.getDescripcion());
    tvHorario.setText("Horario: "+lugarSelect.getHorario());

}
    public void cargarImagenes(int img)
    {
        ImageView imagen= new ImageView(getContext());
        imagen.setBackgroundResource(img);

        carrusel.addView(imagen);
        carrusel.setFlipInterval(3000);
        carrusel.setAutoStart(Boolean.TRUE);

        carrusel.setInAnimation(getContext(), android.R.anim.slide_out_right);
        carrusel.setOutAnimation(getContext(), android.R.anim.slide_out_right);


    }
}