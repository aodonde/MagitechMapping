package com.magitech.aodonde.magitechmapping;
/**
 * Created by aodonde on 20/04/16.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.Random;


public class Main extends AppCompatActivity {
    private static int MaxRandomNumber = 10;
    private static int MinRandomNumber = 4;

    private PointsGraphSeries<DataPoint> RealTime1Series;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Agoro-D", "Main Breakpoint 1 - Executing the createArray method");
        int [][] DataArray = createArray();

        Log.i("Agoro-I", "Entering the Array Data debug loop");
            for(int i=0; i < DataArray.length; i++){
                Log.d("Agoro", "Array Y Data: " + Integer.toString(DataArray[i][1]));
                Log.d("Agoro", "Array X Data: " + Integer.toString(DataArray[i][0]));
            }

        Log.d("Agoro-D", "Main Breakpoint 2 - testing out the graphview");

        GraphView RealTime1 = (GraphView) findViewById(R.id.realtime1);
        RealTime1Series = new PointsGraphSeries<>();
        RealTime1.addSeries(RealTime1Series);


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
            for(int ydata = 0; ydata < 10; ydata++) {
                int xdata = RandomNumber(MinRandomNumber, MaxRandomNumber);
                testArray[row][0] = xdata;
                testArray[row][1] = ydata;
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


}
