package com.gipsyz.panoramaglandroid;

import android.content.Intent;
import android.graphics.Typeface;
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

import static com.gipsyz.panoramaglandroid.street_principal.EXTRA_MESSAGE;

public class street_detalle extends AppCompatActivity {
    private Toolbar toolbar;
    private String[] historiaMural, historiaIglesia, historiaCasa,tituloArray, muralesArray, casasArray, lugaresArray, tituloMenu;
    private TextView titulo, subTitulo, historia;
    private  WebView webView;
    private  LinearLayout linearArray;

    final ImageView[] imageViewArray = new ImageView[3];
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


        linearArray = (LinearLayout) findViewById(R.id.linearLay2);
        if (subTitulo.getText().equals(tituloArray[0].toString())){
            for (int i = 0; i<lugaresArray.length; i++){


                imageViewArray[i] = new ImageView(this);
                imageViewArray[i].setImageResource(iglesia360[i]);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 450);
                layoutParams.gravity=Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                imageViewArray[i].setLayoutParams(layoutParams);
                historia.setText(historiaIglesia[i]);
                linearArray.addView(imageViewArray[i]);
                imageViewArray[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn360();
                    }
                });
            }
        }
        if (subTitulo.getText().equals(tituloArray[1].toString())){
            for (int i = 0; i<muralesArray.length; i++){
                if(titulo.getText().equals(muralesArray[i])){

                    imageViewArray[i] = new ImageView(this);
                    imageViewArray[i].setImageResource(mural360[i]);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 450);
                    layoutParams.gravity=Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                    imageViewArray[i].setLayoutParams(layoutParams);
                    historia.setText(historiaMural[i]);
                    linearArray.addView(imageViewArray[i]);
                    imageViewArray[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn360();
                        }
                    });
                }
            }
        }
        if (subTitulo.getText().equals(tituloArray[2].toString())){
            for (int i = 0; i<casasArray.length; i++){
                if(titulo.getText().equals(casasArray[i])){

                    imageViewArray[i] = new ImageView(this);
                    imageViewArray[i].setImageResource(casas360[i]);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 450);
                    layoutParams.gravity=Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                    imageViewArray[i].setLayoutParams(layoutParams);
                    historia.setText(historiaCasa[i]);
                    linearArray.addView(imageViewArray[i]);
                    imageViewArray[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn360();
                        }
                    });
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
