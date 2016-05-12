package com.magitech.aodonde.magitechmapping;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Random;



public class Mapping extends AppCompatActivity {
    public static int MaxRandomNumber = 10;
    public static int MinRandomNumber = -10;
    public int ydatacounter = -3;
    public ArrayList<Integer> DataList = new ArrayList();
    private LineGraphSeries<DataPoint> RealTime1Series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapping);
        ydatacounter = -3;
        XDataIntoList(DataList);
        int[][] DataArray = new int[DataList.size()][2];
        PutListInto2DArrayX(DataList, DataArray);
        PutListInto2DArrayY(DataList, DataArray);
        int XAxisLimitFirst = sortArray(DataArray);
        int XAxisLimitSecond = sortArray(DataArray);
        int XAxisLimitFinal;

        if (XAxisLimitFirst > XAxisLimitSecond) {
            XAxisLimitFinal = XAxisLimitFirst;
        } else {
            XAxisLimitFinal = XAxisLimitSecond;
        }
        GraphView RealTime1 = (GraphView) findViewById(R.id.realtime1);

        RealTime1Series = new LineGraphSeries<DataPoint>(generatePoints(DataArray));

        RealTime1.getViewport().setXAxisBoundsManual(true);
        RealTime1.getViewport().setMinX((-XAxisLimitFinal) - 10);
        RealTime1.getViewport().setMaxX(XAxisLimitFinal + 10);
        RealTime1.onDataChanged(false, false);
        RealTime1.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return super.formatLabel(value, isValueX) + "m";
                } else {
                    return super.formatLabel(value, isValueX) + "m";
                }
            }
        });
        RealTime1.addSeries(RealTime1Series);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ydatacounter = -3;
                XDataIntoList(DataList);
                int[][] DataArray = new int[DataList.size()][2];
                PutListInto2DArrayX(DataList, DataArray);
                PutListInto2DArrayY(DataList, DataArray);
                int XAxisLimitFirst = sortArray(DataArray);
                int XAxisLimitSecond = sortArray(DataArray);
                int XAxisLimitFinal;
                if (XAxisLimitFirst > XAxisLimitSecond) {
                    XAxisLimitFinal = XAxisLimitFirst;
                } else {
                    XAxisLimitFinal = XAxisLimitSecond;
                }
                GraphView RealTime1 = (GraphView) findViewById(R.id.realtime1);

                RealTime1Series = new LineGraphSeries<DataPoint>(generatePoints(DataArray));

                RealTime1.getViewport().setXAxisBoundsManual(true);
                RealTime1.getViewport().setMinX((-XAxisLimitFinal) - 10);
                RealTime1.getViewport().setMaxX(XAxisLimitFinal + 10);
                RealTime1.onDataChanged(false, false);
                RealTime1.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            return super.formatLabel(value, isValueX) + "m";
                        } else {
                            return super.formatLabel(value, isValueX) + "m";
                        }
                    }
                });
                RealTime1.addSeries(RealTime1Series);

            }
        });
    }
    private static int RandomNumber(int min, int max){
        Random r = new Random();
        int RN = r.nextInt((max - min) + 1 ) + min;
        return RN;
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
    public void XDataIntoList(ArrayList<Integer> X){
        for(int i = 0; i < 10; i++){
            int xdata = RandomNumber(MinRandomNumber, MaxRandomNumber);
            X.add(xdata);
        }
    }
    public void PutListInto2DArrayX(ArrayList<Integer> X, int[][] Y){
        for(int i = 0; i < Y.length; i++){
            int x = X.get(i);
            Y[i][1]= x;
        }
    }
    public void PutListInto2DArrayY(ArrayList<Integer> X, int[][] Y){
        for(int i = 0; i < Y.length; i++){
            int y = ydatacounter;
            Y[i][0] = y;
            ydatacounter++;
        }
    }

}


