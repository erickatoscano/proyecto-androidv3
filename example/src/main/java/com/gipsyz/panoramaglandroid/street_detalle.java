package com.gipsyz.panoramaglandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

import static com.gipsyz.panoramaglandroid.street_principal.EXTRA_MESSAGE;

public class street_detalle extends AppCompatActivity {
    private Toolbar toolbar;
    private String[] historiaContenedor, arrayContenedor, historiaMural, historiaIglesia, historiaCasa,tituloArray, muralesArray, casasArray, lugaresArray, tituloMenu;
    private TextView titulo, subTitulo, historia;
    private  WebView webView;
    private  LinearLayout linearArray;
    public TextView btn_internet;

    final ImageView[] imageViewArray = new ImageView[3];
    private int[] arrayCImagen;
    private int[] mural360 = {
            R.drawable.mural3360,
            R.drawable.mural2360,
            R.drawable.mural1360,
    };
    private int[] iglesia360 = {
            R.drawable.iglesia360,
    };
    private int[] casas360 = {
            R.drawable.casa1360,
            R.drawable.casa2360,

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_detalle);

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


        muralesArray = getResources().getStringArray(R.array.lugares_murales);
        lugaresArray = getResources().getStringArray(R.array.lugares_ciudad);
        casasArray = getResources().getStringArray(R.array.lugares_casas);

        tituloMenu= getResources().getStringArray(R.array.titulos);
        historiaIglesia = getResources().getStringArray(R.array.historia_ciudad);
        historiaMural = getResources().getStringArray(R.array.historia_murales);
        historiaCasa = getResources().getStringArray(R.array.historia_casas);

        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        titulo = findViewById(R.id.textView2);
        titulo.setText(message);

        Intent intent2 = getIntent();
        String message2 = intent2.getStringExtra("titulo");
        subTitulo = findViewById(R.id.subtitulo);
        subTitulo.setText(message2);

        historia = (TextView)findViewById(R.id.historia) ;

        tituloArray= getResources().getStringArray(R.array.titulos);
        accionar();
    }
    private void accionar(){
        linearArray = (LinearLayout) findViewById(R.id.linearLay2);
        for (int i = 0; i<tituloArray.length; i++){
            if (subTitulo.getText().equals(tituloArray[i].toString())){
                switch (i){
                    case 0:
                        arrayContenedor=lugaresArray;
                        arrayCImagen=iglesia360;
                        historiaContenedor=historiaIglesia;
                        break;
                    case 1:
                        arrayContenedor=muralesArray;
                        arrayCImagen=mural360;
                        historiaContenedor=historiaMural;
                        break;
                    case 2:
                        arrayContenedor=casasArray;
                        arrayCImagen=casas360;
                        historiaContenedor=historiaCasa;
                        break;
                }


                for (int j = 0; j<arrayContenedor.length; j++){
                    if(titulo.getText().equals(arrayContenedor[j])){
                        imageViewArray[j] = new ImageView(this);
                        imageViewArray[j].setImageResource(arrayCImagen[j]);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 450);
                        layoutParams.gravity=Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                        imageViewArray[j].setLayoutParams(layoutParams);
                        historia.setText(historiaContenedor[j]);
                        linearArray.addView(imageViewArray[j]);
                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                        if (networkInfo != null && networkInfo.isConnected()) {

                            imageViewArray[j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    btn360();
                                }
                            });

                        } else {
                            imageViewArray[j].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getApplicationContext(), R.string.mensaje, Toast.LENGTH_LONG).show();
                                }
                            });


                        }



                    }
                }
            }

        }
    }

    private void btn360() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, subTitulo.getText());
        intent.putExtra("lugar", titulo.getText());
        startActivity(intent);
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
}
