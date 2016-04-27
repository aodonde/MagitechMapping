package com.magitech.aodonde.magitechmapping;
/**
 * Created by aodonde on 20/04/16.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.sql.DataTruncation;
import java.util.Random;


public class Main extends AppCompatActivity {
    public static int MaxRandomNumber = 10;
    public static int MinRandomNumber = -10;
    public boolean DataLock = false;

    public int [][] DataArray;
    private LineGraphSeries<DataPoint> RealTime1Series;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Agoro-D", "Data lock flag: "+ Boolean.toString(DataLock));

        if(DataLock == false ) {
            Log.d("Agoro-D", "Main Breakpoint 1 - Executing the createArray method");
            DataArray = createArray();
        }

        DataLock = true;


            Log.i("Agoro-I", "Entering the Array Data debug loop");
                for (int i = 0; i < DataArray.length; i++) {
                Log.d("Agoro-D", "Array Y Data: " + Integer.toString(DataArray[i][1]));
                Log.d("Agoro-D", "Array X Data: " + Integer.toString(DataArray[i][0]));
                }

            Log.d("Agoro-D", "Main Breakpoint 2 - Dynamically creating the limits of the graph");
            int XAxisLimitFirst = sortArray(DataArray);
            int XAxisLimitSecond = sortArray(DataArray);
            int XAxisLimitFinal;

                if (XAxisLimitFirst > XAxisLimitSecond){
                    XAxisLimitFinal = XAxisLimitFirst;
                }
                else {
                XAxisLimitFinal = XAxisLimitSecond;
                }
            Log.d("Agoro-D", "Value of the X Axis limit: " + Integer.toString(XAxisLimitFinal));


            Log.d("Agoro-D", "Main Breakpoint 3 - Creating the GraphView object");
            GraphView RealTime1 = (GraphView) findViewById(R.id.realtime1);

            RealTime1Series = new LineGraphSeries<DataPoint>(generatePoints(DataArray));

            RealTime1.getViewport().setXAxisBoundsManual(true);
            RealTime1.getViewport().setMinX((-XAxisLimitFinal)-10);
            RealTime1.getViewport().setMaxX(XAxisLimitFinal+10);
            RealTime1.onDataChanged(false, false);

            Log.d("Agoro-D", "Main Breakpoint 4 - Formatting the labels");
            RealTime1.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
                @Override
                public String formatLabel(double value, boolean isValueX){
                    if(isValueX){
                        return super.formatLabel(value, isValueX) + "m";
                    }
                    else {
                        return super.formatLabel(value, isValueX) + "m";
                    }
                    }
                });

            RealTime1.addSeries(RealTime1Series);


        Log.d("Agoro-D", "Data lock flag: "+ Boolean.toString(DataLock));

        Log.d("Agoro-D", "End of Main");

    }


    private static int RandomNumber(int min, int max){
        Random r = new Random();
        int RN = r.nextInt((max - min) + 1 ) + min;
        return RN;
    }

    private int[][] createArray(){
        Log.i("Agoro-I", "createArray() Breakpoint 1 - Entering the for loop");
        int [][] testArray = new int[10][2];
        int row = 0;
            for(int ydata = 0; ydata < testArray.length; ydata++) {
                int xdata = RandomNumber(MinRandomNumber, MaxRandomNumber);
                testArray[row][0] = xdata;
                testArray[row][1] = ydata-3;
                row++;
            }
        Log.i("Agoro-I", "createArray() Breakpoint 2 - Exiting the For loop");
        return testArray;
    }

    private int sortArray(int[][]DataSort){
        int max = DataSort[0][0];
        for(int i = 1; i < DataSort.length; i++){
            if(DataSort[i][0] > max) max = DataSort[i][0];
        }
        return max;
    }

    private DataPoint[] generatePoints( int[][] DataPointArray){
        DataPoint[] values = new DataPoint[DataPointArray.length];
            for(int i = 0; i<DataPointArray.length; i++){
                int y = DataPointArray[i][1];
                int x = DataPointArray[i][0];
                DataPoint z = new DataPoint(x,y);
                values[i] = z;
            }
        return values;

    }



}
