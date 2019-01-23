package com.gipsyz.panoramaglandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.panoramagl.PLConstants;
import com.panoramagl.PLICamera;
import com.panoramagl.PLImage;
import com.panoramagl.PLManager;
import com.panoramagl.PLSphericalPanorama;
import com.panoramagl.utils.PLUtils;
import com.panoramagl.enumerations.PLSensorialRotationType;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.gipsyz.panoramaglandroid.street_principal.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity  {


    public String url_new;
    String[] arrayURL;
    public static final String[] urlIglesia = new String[]{
            "http://xipixapa360.site/Iglesia/iglesia.jpg"
    };

    public static final String[] urlMurales = new String[]{
            "http://xipixapa360.site/Mural/mural1.jpg",
            "http://xipixapa360.site/Mural/mural2.jpg",
            "http://xipixapa360.site/Mural/mural3.jpg"
    };

    public static final String[] urlCasas = new String[]{
            "http://xipixapa360.site/Casas/guaranda.jpg",
            "http://xipixapa360.site/Casas/nieto.jpg"
    };
    private ImageView imgImagen;


    String tituloPrincipal, lugar;

    private PLManager plManager;
    private int currentIndex = -1;

    private String[] tituloArray, titulosArray, muralesArray, casasArray, lugaresArray;



    private boolean mIsValidForSensorialRotation;
    private PLSensorialRotationType mSensorialRotationType;
    private long mSensorialRotationThresholdTimestamp;
    private boolean mSensorialRotationThresholdFlag;
    private float[] mSensorialRotationAccelerometerData;
    private float[] mSensorialRotationRotationMatrix;
    private float[] mSensorialRotationOrientationData;
    private boolean mHasFirstGyroscopePitch, mHasFirstAccelerometerPitch, mHasFirstMagneticHeading;
    private float mFirstAccelerometerPitch, mLastAccelerometerPitch, mAccelerometerPitch;
    private float mFirstMagneticHeading, mLastMagneticHeading, mMagneticHeading;
    private long mGyroscopeLastTime;
    private float mGyroscopeRotationX, mGyroscopeRotationY;



    //ARREGLO CON LAS IMÁGENES A MOSTRAR 2048*1024
    private int[] resourceIds = new int[]{
            R.raw.test_3,
            R.raw.test4};


   private SensorManager sensorManager;
   private Sensor gyroscopeSensor;
   private Sensor acelerometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        System.out.println("***************************** ON CREATE **********************************");


       //OCULTAR BARRA DE ESTADO PARA FULL SCREEN
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        plManager = new PLManager(this);
        plManager.setContentView((ViewGroup)findViewById(R.id.content_view));
        plManager.onCreate();

        plManager.setAccelerometerEnabled(false);
        plManager.setInertiaEnabled(false);
        plManager.setZoomEnabled(false);

        Intent intent = getIntent();
        tituloPrincipal = intent.getStringExtra(EXTRA_MESSAGE);

        Intent intent2 = getIntent();
        lugar = intent2.getStringExtra("lugar");

        muralesArray = getResources().getStringArray(R.array.lugares_murales);
        lugaresArray = getResources().getStringArray(R.array.lugares_ciudad);
        casasArray = getResources().getStringArray(R.array.lugares_casas);

        tituloArray= getResources().getStringArray(R.array.titulos);

        sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);




        for (int i = 0; i<tituloArray.length; i++){
            if (tituloPrincipal.equals(tituloArray[i].toString())){
                switch (i){
                    case 0:
                        arrayURL = urlIglesia;
                        titulosArray=lugaresArray;
                        break;
                    case 1:
                        arrayURL = urlMurales;
                        titulosArray=muralesArray;
                        break;
                    case 2:
                        arrayURL = urlCasas;
                        titulosArray=casasArray;
                        break;
                        default:
                            break;
                }
                for (int j = 0; j<titulosArray.length; j++){
                    if(lugar.equals(titulosArray[j])){
                        System.out.println("ENTRO");
                        url_new= arrayURL[j];

                        try{
                            Thread.sleep(1000); //Coge el hilo actual y lo pone a dormir, 1000 > 1seg, la tarea tardar un segundo en ejecutarse


                        }catch (InterruptedException e){

                        }
                        CargaImagenes nuevaTarea = new CargaImagenes();
                        nuevaTarea.execute(url_new);
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        plManager.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        plManager.onPause();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        plManager.onDestroy();

    }


    PLSphericalPanorama panorama;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return plManager.onTouchEvent(event);
    }

    private void changePanorama(int index) {
        if(currentIndex == index) return;


      plManager.startSensorialRotation();

       panorama = new PLSphericalPanorama();
        panorama.setImage(new PLImage(PLUtils.getBitmap(this, resourceIds[index]), false));
        float pitch = 5f;//Orientación en Y
        float yaw = 0f;
        float zoomFactor = 100f; //velocidad de desplazamiento

        if(currentIndex != -1) {
            PLICamera camera = plManager.getPanorama().getCamera();
            pitch = camera.getPitch();
            yaw = camera.getYaw();
            zoomFactor = camera.getZoomFactor();
        }

        panorama.getCamera().lookAtAndZoomFactor(pitch, yaw, zoomFactor, false);
        plManager.setPanorama(panorama);
        currentIndex = index;
        Toast.makeText(this, index+" ", Toast.LENGTH_LONG).show();
    }


    public void test (View view){
        System.out.println("ESTÁ APLASTANDO EL BOTÓN");
    }




    private class CargaImagenes extends AsyncTask<String, Void, Bitmap> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando Imagen");
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            Log.i("doInBackground" , "Entra en doInBackground");
            String url = params[0];
            Bitmap imagen = descargarImagen(url);
            return imagen;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            plManager.startSensorialRotation();

            panorama = new PLSphericalPanorama();

            panorama.setImage(new PLImage(result, false));
            float pitch = 5f;//Orientación en Y
            float yaw = 0f;
            float zoomFactor = 100f; //velocidad de desplazamiento

            if(currentIndex != -1) {
                PLICamera camera = plManager.getPanorama().getCamera();
                pitch = camera.getPitch();
                yaw = camera.getYaw();
                zoomFactor = camera.getZoomFactor();
            }

            panorama.getCamera().lookAtAndZoomFactor(pitch, yaw, zoomFactor, false);
            plManager.setPanorama(panorama);

            pDialog.dismiss();
        }

    }

    private Bitmap descargarImagen (String imageHttpAddress){
        URL imageUrl = null;
        Bitmap imagen = null;
        try{
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            imagen = BitmapFactory.decodeStream(conn.getInputStream());
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return imagen;
    }


}
