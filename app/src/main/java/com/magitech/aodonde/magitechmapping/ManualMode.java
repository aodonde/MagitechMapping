package com.magitech.aodonde.magitechmapping;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by aodonde on 12/05/16.
 */
public class ManualMode extends Activity {
    public int XSTEP = 0;
    public int YSTEP = 0;
    public ArrayList<Integer> YList = new ArrayList();
    public ArrayList<Integer> XList = new ArrayList();
    private LineGraphSeries<DataPoint> RealTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
    }

    public void Up(View view){
        YSTEP = YSTEP + 1;
        XSTEP = XSTEP + 0;
        Map(XSTEP,YSTEP,XList,YList);
    }

    public void Right(View view){
        XSTEP = XSTEP + 1;
        YSTEP = YSTEP + 0;
        Map(XSTEP,YSTEP,XList,YList);
    }
    public void Left(View view){
        XSTEP = XSTEP - 1;
        YSTEP = YSTEP + 0;
        Map(XSTEP,YSTEP,XList,YList);
    }
    public void Down(View view){
        XSTEP = XSTEP - 0;
        YSTEP = YSTEP - 1;
        Map(XSTEP,YSTEP,XList,YList);

    }
    public void PutListInto2DArrayX(ArrayList<Integer> X, int[][] Y) {
        for (int i = 0; i < Y.length; i++) {
            int x = X.get(i);
            Y[i][1] = x;
        }
    }
    public void PutListInto2DArrayY(ArrayList<Integer> X, int[][] Y) {
        for (int i = 0; i < Y.length; i++) {
            int y = X.get(i);
            Y[i][0] = y;
        }
    }
    public int sortArray(int[][]DataSort){
        int max = DataSort[0][0];
        for(int i = 1; i < DataSort.length; i++){
            if(Math.abs(DataSort[i][0]) > max) max = DataSort[i][0];
        }
        return max;
    }
    public DataPoint[] generatePoints( int[][] DataPointArray){
        DataPoint[] values = new DataPoint[DataPointArray.length];
        for(int i = 0; i<DataPointArray.length; i++){
            int y = DataPointArray[i][0];
            int x = DataPointArray[i][1];
            DataPoint z = new DataPoint(x,y);
            values[i] = z;
        }
        return values;

    }
    public void Map (int X, int Y, ArrayList XX, ArrayList YY){
        YY.add(Y);
        XX.add(X);
        int[][] DataArray = new int [XX.size()][2];
        PutListInto2DArrayX(XX, DataArray);
        PutListInto2DArrayY(YY, DataArray);
        int XAxisLimitFirst = sortArray(DataArray);
        int XAxisLimitSecond = sortArray(DataArray);
        int XAxisLimitFinal;

        if (XAxisLimitFirst > XAxisLimitSecond) {
            XAxisLimitFinal = XAxisLimitFirst;
        } else {
            XAxisLimitFinal = XAxisLimitSecond;
        }

        GraphView Manual = (GraphView) findViewById(R.id.manual);

        RealTime = new LineGraphSeries<DataPoint>(generatePoints(DataArray));

        Manual.getViewport().setXAxisBoundsManual(true);
        Manual.getViewport().setMinX((-XAxisLimitFinal) - 10);
        Manual.getViewport().setMaxX(XAxisLimitFinal + 10);
        Manual.onDataChanged(false, false);
        Manual.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return super.formatLabel(value, isValueX) + "m";
                } else {
                    return super.formatLabel(value, isValueX) + "m";
                }
            }
        });
        Manual.addSeries(RealTime);
    }


}
