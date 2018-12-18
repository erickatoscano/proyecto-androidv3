package com.gipsyz.panoramaglandroid;

import android.content.Intent;
import android.graphics.Typeface;
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

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class p_principal extends AppCompatActivity implements View.OnClickListener {


    public static final String EXTRA_MESSAGE = "com.example.administrador.myapplication.MESSAGE";
    private ImageView ivSA,ivCC, ivRC, ivCT, ivT, ivCTi;

    private String[] titulo;

    private ImageView street, casas,ruta,ciudad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_principal);

        titulo= getResources().getStringArray(R.array.titulos);

        ivSA=(ImageView) findViewById(R.id.icono1);
        ivCC=(ImageView) findViewById(R.id.icono2);
        ivRC=(ImageView) findViewById(R.id.icono3);
        ivCT=(ImageView) findViewById(R.id.icono4);
        ivCTi=(ImageView) findViewById(R.id.icono5);

        ivSA.setOnClickListener(this);
        ivCC.setOnClickListener(this);
        ivRC.setOnClickListener(this);
        ivCT.setOnClickListener(this);
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
                Intent intent5 = new Intent(this, tienda.class);
                intent5.putExtra(EXTRA_MESSAGE, titulo[4].toString());
                startActivity(intent5);
                break;

        }
    }
}
