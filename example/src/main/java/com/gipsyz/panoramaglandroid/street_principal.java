package com.gipsyz.panoramaglandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.gipsyz.panoramaglandroid.p_principal.EXTRA_MESSAGE;

public class street_principal extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "com.example.administrador.myapplication.MESSAGE";
    private Toolbar toolbar;

    private String[] tituloMenu;
    private ImageView iv1;
    private TextView titulo;
    private String[] tituloArray, muralesArray, casasArray, lugaresArray, desciudadArray, desArtArray, desCasaArray;
    final TextView[] textViewArray = new TextView[3];
    final TextView[] textViewArray2 = new TextView[3];
    final ImageView[] imageViewArray = new ImageView[3];
    private GridLayout gridLayout;
    private  LinearLayout linearArray;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_principal);


        tituloMenu= getResources().getStringArray(R.array.titulos);

        toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ImageView menuLogo = (ImageView) findViewById(R.id.logo);
        menuLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent0 = new Intent(getApplicationContext(), p_principal.class);
                startActivity(intent0);
            }
        });

        gridLayout = (GridLayout) findViewById(R.id.grid_layout);

        linearArray = (LinearLayout) findViewById(R.id.linearLay);

        tituloArray= getResources().getStringArray(R.array.titulos);
        muralesArray = getResources().getStringArray(R.array.lugares_murales);
        lugaresArray = getResources().getStringArray(R.array.lugares_ciudad);
        casasArray = getResources().getStringArray(R.array.lugares_casas);

        desciudadArray = getResources().getStringArray((R.array.des_ciudad));
        desArtArray = getResources().getStringArray((R.array.des_murales));
        desCasaArray = getResources().getStringArray((R.array.des_casas));


        // T I T U L O
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        titulo = findViewById(R.id.txtTitulo);
        titulo.setText(message);


        if (titulo.getText().equals(tituloArray[0].toString())){

            for (int i = 0; i<lugaresArray.length; i++){

                imageViewArray[i] = new ImageView(this);
                imageViewArray[i].setImageResource(R.drawable.asset05);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);

                layoutParams.gravity=Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                imageViewArray[i].setLayoutParams(layoutParams);
                imageViewArray[i].setPadding(0,20,0,10);

                textViewArray[i] = new TextView(this);
                textViewArray[i].setText(lugaresArray[i]);
                textViewArray[i].setGravity(Gravity.CENTER);
                textViewArray[i].setPadding(0,0,0,5);
                textViewArray[i].setTextAppearance(getApplicationContext(), R.style.titulos_act);

                TextView descripcionArray = new TextView (this);
                descripcionArray.setText(desciudadArray[i]);
                descripcionArray.setGravity(Gravity.CENTER);
                descripcionArray.setTextAppearance(getApplicationContext(), R.style.descripcion_act);

                linearArray.addView(imageViewArray[i]);
                linearArray.addView(textViewArray[i]);
                linearArray.addView(descripcionArray);

                final int finalI = i;
                imageViewArray[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getApplicationContext(), street_detalle.class);
                        intent1.putExtra(EXTRA_MESSAGE, textViewArray[finalI].getText());
                        startActivity(intent1);
                    }
                });

/*
                final TextView txtTiulo1 = (TextView) findViewById(R.id.txtTitulo1);
                txtTiulo1.setText(lugaresArray[i]);
                txtTiulo1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getApplicationContext(), street_detalle.class);
                        intent1.putExtra(EXTRA_MESSAGE, txtTiulo1.getText());
                        startActivity(intent1);
                    }
                });

*/
            }




        }
        if (titulo.getText().equals(tituloArray[1].toString())){
            TextView[] descripcionArray = new TextView[muralesArray.length];
            for (int i = 0; i<muralesArray.length; i++){

                imageViewArray[i] = new ImageView(this);
                imageViewArray[i].setImageResource(R.drawable.asset05);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);

                layoutParams.gravity=Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                imageViewArray[i].setLayoutParams(layoutParams);
                imageViewArray[i].setPadding(0,20,0,10);

                final int finalI = i;
                imageViewArray[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getApplicationContext(), street_detalle.class);
                        intent1.putExtra(EXTRA_MESSAGE, textViewArray[finalI].getText());
                        startActivity(intent1);
                    }
                });

                textViewArray[i] = new TextView(this);
                textViewArray[i].setText(muralesArray[i]);
                textViewArray[i].setGravity(Gravity.CENTER);
                textViewArray[i].setPadding(0,0,0,5);
                textViewArray[i].setTextAppearance(getApplicationContext(), R.style.titulos_act);

                descripcionArray[i] = new TextView(this);
                descripcionArray[i].setText(desArtArray[i]);
                descripcionArray[i].setGravity(Gravity.CENTER);
                descripcionArray[i].setTextAppearance(getApplicationContext(), R.style.descripcion_act);

                linearArray.addView(imageViewArray[i]);
                linearArray.addView(textViewArray[i]);
                linearArray.addView(descripcionArray[i]);

            }
        }
        if (titulo.getText().equals(tituloArray[2].toString())){

            TextView[] descripcionArray = new TextView[casasArray.length];
            for (int i = 0; i<casasArray.length; i++){

                imageViewArray[i] = new ImageView(this);
                imageViewArray[i].setImageResource(R.drawable.asset05);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);

                layoutParams.gravity=Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                imageViewArray[i].setLayoutParams(layoutParams);
                imageViewArray[i].setPadding(0,20,0,10);

                textViewArray[i] = new TextView(this);
                textViewArray[i].setText(desCasaArray[i]);
                textViewArray[i].setGravity(Gravity.CENTER);
                textViewArray[i].setPadding(0,0,0,5);
                textViewArray[i].setTextAppearance(getApplicationContext(), R.style.titulos_act);

                descripcionArray[i] = new TextView(this);
                descripcionArray[i].setText(desArtArray[i]);
                descripcionArray[i].setGravity(Gravity.CENTER);
                descripcionArray[i].setTextAppearance(getApplicationContext(), R.style.descripcion_act);

                linearArray.addView(imageViewArray[i]);
                linearArray.addView(textViewArray[i]);
                linearArray.addView(descripcionArray[i]);

                final int finalI = i;
                imageViewArray[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(getApplicationContext(), street_detalle.class);
                        intent1.putExtra(EXTRA_MESSAGE, textViewArray[finalI].getText());
                        startActivity(intent1);
                    }
                });

            }
        }
        if (titulo.getText().equals(tituloArray[3].toString())){
            Toast.makeText(getApplicationContext(),"Proximamente",2).show();
        }


        //ir a la otra pantalla
        /*
        iv1=(ImageView) findViewById(R.id.iconostreet1);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), street_detalle.class);
                startActivity(intent);
            }
        });
*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.menu_ciudad:
                Intent intent1 = new Intent(this, street_principal.class);
                intent1.putExtra(EXTRA_MESSAGE, tituloMenu[0].toString());
                startActivity(intent1);
                break;
            case R.id.menu_street:
                Intent intent2 = new Intent(this, street_principal.class);
                intent2.putExtra(EXTRA_MESSAGE, tituloMenu[1].toString());
                startActivity(intent2);
                break;
            case R.id.menu_casas:
                Intent intent3 = new Intent(this, street_principal.class);
                intent3.putExtra(EXTRA_MESSAGE, tituloMenu[2].toString());
                startActivity(intent3);
                break;
            case R.id.menu_tienda:
                Intent intent5 = new Intent(this, tienda.class);
                intent5.putExtra(EXTRA_MESSAGE, tituloMenu[4].toString());
                startActivity(intent5);
                break;



        }
        return true;
    }

    @Override
    public void onClick(View v) {


    }
}
