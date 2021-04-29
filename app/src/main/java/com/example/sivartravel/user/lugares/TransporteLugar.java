package com.example.sivartravel.user.lugares;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.example.sivartravel.R;
import com.example.sivartravel.user.entitys.LugaresEntity;
import com.example.sivartravel.util.JsonUtil;

public class TransporteLugar extends Fragment {

    private  LugaresEntity lugarSelect;
    private TextView lb;

    public TransporteLugar() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_transporte_lugar, container, false);

        Bundle argumentos=this.getArguments();
        String transporte =argumentos.getString("Lugares");
        lugarSelect= JsonUtil.getGsonParser().fromJson(transporte, LugaresEntity.class);

        lb= root.findViewById(R.id.textLugar);
        lb.setText(lugarSelect.getLugar());

        return root;
    }
}