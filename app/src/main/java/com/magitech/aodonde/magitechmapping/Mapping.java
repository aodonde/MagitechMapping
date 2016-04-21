package com.magitech.aodonde.magitechmapping;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.Random;



public class Mapping extends AppCompatActivity {
    private static int MaxRandomNumber = 10;
    private static int MinRandomNumber = 4;

    private PointsGraphSeries<DataPoint> RealTime1Series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapping);

        Log.d("Agoro", "Main Breakpoint 1 - testing out the RNG");
        int x = RandomNumber(MinRandomNumber,MaxRandomNumber);
        int y = RandomNumber(MinRandomNumber,MaxRandomNumber);
        int z = RandomNumber(MinRandomNumber,MaxRandomNumber);
        Log.d("Agoro", "1st RN: " + Integer.toString(x));
        Log.d("Agoro", "2nd RN: " + Integer.toString(y));
        Log.d("Agoro", "3rd RN: " + Integer.toString(z));

        Log.d("Agoro", "Main Breakpoint 2 - testing out the graphview");

        GraphView RealTime1 = (GraphView) findViewById(R.id.realtime1);
        RealTime1Series = new PointsGraphSeries<>();
        RealTime1.addSeries(RealTime1Series);


        Log.d("Agoro", "End of Main");


}



    private static int RandomNumber(int min, int max){
        Random r = new Random();
        int RN = r.nextInt((max - min) + 1 ) + min;
        return RN;
    }

    private int[][] createArray(){
        int [][] testArray = new int[10][2];
        for(int ydata = 0; ydata < 10; ydata++) {
            int xdata = 0;
            int row = 0;
            testArray[row][0] = xdata;
            testArray[row][1] = ydata;
            row++;
            xdata++;
        }
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