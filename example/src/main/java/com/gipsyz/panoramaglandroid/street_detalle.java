package com.gipsyz.panoramaglandroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import static com.gipsyz.panoramaglandroid.street_principal.EXTRA_MESSAGE;

public class street_detalle extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView iv1;

    private String[] tituloMenu;
    private TextView titulo;
    private  WebView webView;

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



        tituloMenu= getResources().getStringArray(R.array.titulos);
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        titulo = findViewById(R.id.textView2);
        titulo.setText(message);



        iv1=(ImageView) findViewById(R.id.img_360);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

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
