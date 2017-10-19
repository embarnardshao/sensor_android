package com.example.embshao.assignment2;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorMgrA, sensorMgrL, sensorMgrP;
    boolean accelerometer, light, proximity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView aInfo = (TextView)findViewById(R.id.a_i);
        TextView lInfo = (TextView)findViewById(R.id.l_i);
        TextView pInfo = (TextView)findViewById(R.id.p_i);

        //accelerometer
        sensorMgrA = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMgrA.registerListener(this,sensorMgrA.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 1);

        if(accelerometer)
        {
            aInfo.append("Accelerometer sensor is present");
        }else{
            aInfo.append("Accelerometer sensor is not present");
        }

        //light
        sensorMgrL = (SensorManager) getSystemService(SENSOR_SERVICE);
        light = sensorMgrL.registerListener(this,sensorMgrL.getDefaultSensor(Sensor.TYPE_LIGHT), 1);

        if(light)
        {
            lInfo.append("Light sensor is present");
        }else{
            lInfo.append("Light sensor is not present");
        }

        //proximity
        sensorMgrP = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximity = sensorMgrP.registerListener(this,sensorMgrP.getDefaultSensor(Sensor.TYPE_PROXIMITY), 1);

        if(proximity)
        {
            pInfo.append("Proximity sensor is present");
        }else{
            pInfo.append("Proximity sensor is not present");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //buttons
    public void openA(View view){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }
    public void openL(View view){
        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
        startActivity(intent);
    }
    public void openP(View view){
        Intent intent = new Intent(MainActivity.this, Main4Activity.class);
        startActivity(intent);
    }

}
