package com.example.embshao.assignment2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;

public class PlotView extends View
{
    ArrayList<Float> list = new ArrayList<Float>(
            Arrays.asList(0.0f, 0.0f, 0.0f, 0.0f, 0.0f));

    public PlotView(Context context) {
        super(context);
    }

    public PlotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PlotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //COLORS
        Paint paintRed = new Paint();
        paintRed.setStyle(Paint.Style.FILL);
        paintRed.setColor(Color.parseColor("#CD5C5C"));

        Paint paintBlue = new Paint();
        paintBlue.setStyle(Paint.Style.FILL);
        paintBlue.setColor(Color.parseColor("#05bdd7"));

        Paint paintGreen = new Paint();
        paintGreen.setStyle(Paint.Style.FILL);
        paintGreen.setColor(Color.parseColor("#05740d"));

        Paint paintYellow = new Paint();
        paintYellow.setStyle(Paint.Style.FILL);
        paintYellow.setColor(Color.parseColor("#fec63b"));

        //VARIABLES
        int x = canvas.getWidth()/5;
        int xx = canvas.getWidth()/10;
        float y = canvas.getHeight()/60;
        int radius = 7;
        int x2, x1= 0;
        float y2, y1= 0;
        int ax = 0;
        float ay = 0;
        float ch = canvas.getHeight();

        //GRID V-lines
        canvas.drawLine( x, 0, x, canvas.getHeight(), paintBlue);
        canvas.drawLine( x*2, 0, x*2, canvas.getHeight(), paintBlue);
        canvas.drawLine( x*3, 0, x*3, canvas.getHeight(), paintBlue);
        canvas.drawLine( x*4, 0, x*4, canvas.getHeight(), paintBlue);
        //GRID H-lines
        canvas.drawLine( 0, yAx(y*10, ch), canvas.getWidth(), yAx(y*10, ch), paintBlue);
        canvas.drawLine( 0, yAx(y*20, ch), canvas.getWidth(), yAx(y*20, ch), paintBlue);
        canvas.drawLine( 0, yAx(y*30, ch), canvas.getWidth(), yAx(y*30, ch), paintBlue);
        canvas.drawLine( 0, yAx(y*40, ch), canvas.getWidth(), yAx(y*40, ch), paintBlue);
        canvas.drawLine( 0, yAx(y*50, ch), canvas.getWidth(), yAx(y*50, ch), paintBlue);
        canvas.drawLine( 0, yAx(y*60, ch), canvas.getWidth(), yAx(y*60, ch), paintBlue);

        float avg[] = new float[list.size()];
        float average;
        float stDev;

        //adds all the values in the list
        for(int z = 0; z < list.size(); z++)
        {
            //VALUE
            x2 = z*xx;
            y2 = yAx(list.get(z)*y, ch);
            canvas.drawCircle( x2, y2, radius, paintGreen);
            if(z != 0){canvas.drawLine( x1, y1, x2, y2, paintGreen);}
            x1 = x2;
            y1 = y2;
            avg[z] = y2;//put in array for avg and std, dev
        }
        //AVERAGE
        for(int z = 0; z < list.size(); z++)
        {
            x2 = z*xx;
            average = (avg[0] + avg[1] + avg[2] + avg[3] + avg[4]) / 5;
            canvas.drawCircle(x2, average, radius, paintRed);
            if(z != 0){canvas.drawLine( x1, y1, x2, average, paintRed);}
            x1 = x2;
            y1 = average;
        }
        //Std. Deviation
        for(int z = 0; z < list.size(); z++)
        {
            x2 = z*xx;
            stDev = sD(avg);
            canvas.drawCircle(x2, stDev, radius, paintYellow);
            if(z != 0){canvas.drawLine( x1, y1, x2, stDev, paintYellow);}
            x1 = x2;
            y1 = stDev;
        }

        invalidate();
    }

    public static float sD(float numArr[])
    {
        float sum = 0;
        float sDev = 0;

        for(float num : numArr) {
            sum += num;
        }

        float mean = sum/7;

        for(float num: numArr) {
            sDev += Math.pow(num - mean, 2);
        }

        return (float) Math.sqrt(sDev/7);
    }

    public float yAx(float num, float canvasHeight)
    {
        return canvasHeight - num;
    }

    public void clearList(ArrayList<Float> al)
    {
        for(int x = 0; x < al.size(); x++ )
        {
            al.remove(x);
        }
    }


    public ArrayList<Float> addPoint(Float num)
    {
        if(list.size()>8)
        {
            list.remove(8);
        }
        list.add(0,num);
        return list;
    }
}
