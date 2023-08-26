package com.ousl.lab4;

import androidx.appcompat.app.AppCompatActivity;
        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.media.MediaPlayer;
        import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private MediaPlayer mediaPlayer;
    private float changedValue;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mediaPlayer = MediaPlayer.create(this, R.raw.s92060419);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy){
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener( this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        boolean isRunning=false;
        changedValue=event.values[0];

        if(event.values[0]>19 && isRunning==false) {

            mediaPlayer.start();
        } else {
            onPause();
        }
    }
}