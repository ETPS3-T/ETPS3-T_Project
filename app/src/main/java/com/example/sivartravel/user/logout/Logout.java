package com.example.sivartravel.user.logout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.sivartravel.Login;
import com.example.sivartravel.MenuUser;
import com.example.sivartravel.R;

public class Logout extends Fragment {

    @Override
    public void onStart() {

        super.onStart();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Quieres cerrar sesion");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent out = new Intent(getContext(), Login.class);
                startActivity(out);
            }
        }); builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent inn = new Intent(getContext(), MenuUser.class);
                startActivity(inn);
            }
        });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }
}