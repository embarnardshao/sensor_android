package com.example.embshao.assignment2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.hardware.SensorEventListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements SensorEventListener{

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private PlotView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //acc
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_UI);

        //make PlotView
        vv = (PlotView) findViewById(R.id.customView);
        vv.bringToFront();

    }

    //TIME TEXT
    private TextView x0, x1, x2, x3, x4;
    int timeStp, counter = 0;

    public void timeStep()
    {
        x0 = (TextView)findViewById(R.id.x0);
        x1 = (TextView)findViewById(R.id.x1);
        x2 = (TextView)findViewById(R.id.x2);
        x3 = (TextView)findViewById(R.id.x3);
        x4 = (TextView)findViewById(R.id.x4);

        timeStp = 2*counter;
        x0.setText( Integer.toString(Integer.parseInt(x0.getText().toString())+timeStp) );
        x1.setText( Integer.toString(Integer.parseInt(x1.getText().toString())+timeStp) );
        x2.setText( Integer.toString(Integer.parseInt(x2.getText().toString())+timeStp) );
        x3.setText( Integer.toString(Integer.parseInt(x3.getText().toString())+timeStp) );
        x4.setText( Integer.toString(Integer.parseInt(x4.getText().toString())+timeStp) );
        counter++;
    }

    private ImageView mImageView;

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float accP = (float) Math.sqrt( Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        vv.addPoint(accP);
        timeStep();

        mImageView = (ImageView) findViewById(R.id.image);

        if(accP < 12)
        {
            mImageView.setBackgroundResource(R.drawable.animation);
        }else{
            mImageView.setBackgroundResource(R.drawable.animation2);
        }
        ((AnimationDrawable) mImageView.getBackground()).start();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    //not in use
    }

    //pause when sleeps or not in use
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
