package com.gipsyz.panoramaglandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class p_principal extends AppCompatActivity implements View.OnClickListener {


    public static final String EXTRA_MESSAGE = "com.example.administrador.myapplication.MESSAGE";
    private ImageView ivSA,ivCC, ivRC, ivCTi;
    public TextView btn_internet;

    private String[] titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_principal);

        titulo= getResources().getStringArray(R.array.titulos);

        ivSA=(ImageView) findViewById(R.id.icono1);
        ivCC=(ImageView) findViewById(R.id.icono2);
        ivRC=(ImageView) findViewById(R.id.icono3);
        ivCTi=(ImageView) findViewById(R.id.icono5);
        btn_internet = (TextView) findViewById(R.id.hilos);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {



        } else {
            btn_internet.setText("No tienes conexión a Internet, la funciomnes 360 no se podrán ejecutar");

        }

        ivSA.setOnClickListener(this);
        ivCC.setOnClickListener(this);
        ivRC.setOnClickListener(this);
        ivCTi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id) {
            case R.id.icono1:
                Intent intent1 = new Intent(this, street_principal.class);
                intent1.putExtra(EXTRA_MESSAGE, titulo[0].toString());
                startActivity(intent1);
                break;
            case R.id.icono2:
                Intent intent2 = new Intent(this, street_principal.class);
                intent2.putExtra(EXTRA_MESSAGE, titulo[1].toString());
                startActivity(intent2);
                break;
            case R.id.icono3:
                Intent intent3 = new Intent(this, street_principal.class);
                intent3.putExtra(EXTRA_MESSAGE, titulo[2].toString());
                startActivity(intent3);
                break;
            case R.id.icono5:
                Toast.makeText(this, "Próximamente... ", Toast.LENGTH_LONG).show();
                /*
                Intent intent5 = new Intent(this, tienda.class);
                intent5.putExtra(EXTRA_MESSAGE, titulo[3].toString());
                startActivity(intent5);*/
                break;

        }
    }
}
