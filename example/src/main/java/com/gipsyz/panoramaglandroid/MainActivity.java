package com.gipsyz.panoramaglandroid;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.panoramagl.PLConstants;
import com.panoramagl.PLICamera;
import com.panoramagl.PLImage;
import com.panoramagl.PLManager;
import com.panoramagl.PLSphericalPanorama;
import com.panoramagl.utils.PLUtils;
import com.panoramagl.enumerations.PLSensorialRotationType;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity  {

    private PLManager plManager;
    private int currentIndex = -1;



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
    private int[] resourceIds = new int[]{ R.raw.test_3,
            R.raw.test4};

   /* private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button_1:
                    changePanorama(0);
                    break;
                case R.id.button_2:
                    changePanorama(1);
                    break;
                default:
                    break;
            }
        }
    };*/




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

        changePanorama(1);

       /* Button button1 = ((Button) findViewById(R.id.button_1));
        Button button2 = ((Button) findViewById(R.id.button_2));*/
      /*  button1.setOnClickListener(buttonClickListener);
        button2.setOnClickListener(buttonClickListener);*/

sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);





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









}
