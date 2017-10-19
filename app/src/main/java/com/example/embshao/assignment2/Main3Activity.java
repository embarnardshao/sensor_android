package com.example.embshao.assignment2;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity{

    private PlotViewLight vv;
    private SensorManager mySensorManager;
    private Sensor LightSensor;
    //TIME TEXT
    private TextView x0, x1, x2, x3, x4;
    TextView textReading;
    int timeStp, counter = 0;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //LIGHT
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        LightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mySensorManager.registerListener(LightSensorListener, LightSensor, SensorManager.SENSOR_DELAY_NORMAL);

        vv = (PlotViewLight) findViewById(R.id.customView2);
        vv.bringToFront();

        textReading = (TextView)findViewById(R.id.test);
    }

    public void timeStep() {
        x0 = (TextView) findViewById(R.id.x00);
        x1 = (TextView) findViewById(R.id.x11);
        x2 = (TextView) findViewById(R.id.x22);
        x3 = (TextView) findViewById(R.id.x33);
        x4 = (TextView) findViewById(R.id.x44);

        timeStp = 2 * counter;
        x0.setText(Integer.toString(Integer.parseInt(x0.getText().toString()) + timeStp));
        x1.setText(Integer.toString(Integer.parseInt(x1.getText().toString()) + timeStp));
        x2.setText(Integer.toString(Integer.parseInt(x2.getText().toString()) + timeStp));
        x3.setText(Integer.toString(Integer.parseInt(x3.getText().toString()) + timeStp));
        x4.setText(Integer.toString(Integer.parseInt(x4.getText().toString()) + timeStp));
        counter++;
    }

    private final SensorEventListener LightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float reading = event.values[0];

            vv.addPoint(reading);
            timeStep();
            if(event.sensor.getType()==Sensor.TYPE_LIGHT){
                float currentReading = event.values[0];
                textReading.setText(String.valueOf(currentReading));
            }
            mImageView = (ImageView) findViewById(R.id.image);

            if(reading < 100)
            {
                mImageView.setBackgroundResource(R.drawable.animation);
            }else{
                mImageView.setBackgroundResource(R.drawable.animation2);
            }
            ((AnimationDrawable) mImageView.getBackground()).start();
        }

    };
}

