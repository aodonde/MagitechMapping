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
    public String PSTATE = "ORIGIN";
    public String PPSTATE = "ORIGIN";
    public String FACING = "U";
    public ArrayList<Integer> YList = new ArrayList();
    public ArrayList<Integer> XList = new ArrayList();
    private LineGraphSeries<DataPoint> RealTime;

    public String Up = "U";
    public String Right = "R";
    public String Left = "L";
    public String Down = "D";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
    }

    public void Forwards(View view){
        if (FACING.equals(Up)){
            YPlus();
        }
        else if (FACING.equals(Right)){
            XPlus();
        }
        else if (FACING.equals(Left)){
            XMinus();
        }
        else{
            YMinus();
        }

        Map(XSTEP,YSTEP,XList,YList);
        PPSTATE = PSTATE;
        PSTATE = Up;
    }

    public void Right(View view){
        if(FACING.equals(Up)){
            XPlus();
            FACING = Right;
        }
        else if(FACING.equals(Left))
        {
            YPlus();
            FACING =Up;
        }
        else if (FACING.equals(Right)){
            YMinus();
            FACING = Down;
        }
        else{
            XMinus();
            FACING = Left;
        }

        Map(XSTEP,YSTEP,XList,YList);

        PPSTATE = PSTATE;
        PSTATE = Right;

    }

    public void Left(View view) {
        if (FACING.equals(Up)) {
            XMinus();
            FACING = Left;
        }
        else if (FACING.equals(Left)){
            YMinus();
            FACING = Down;
        }
        else if (FACING.equals(Down)){
            XPlus();
            FACING = Right;
        }
        else{
            YPlus();
            FACING = Up;
        }

        Map(XSTEP, YSTEP, XList, YList);

        PPSTATE = PSTATE;
        PSTATE = Left;
    }

    public void Backwards(View view){
        if (FACING.equals(Up)){
            YMinus();
        }
        else if(FACING.equals(Left)){
            XPlus();
        }
        else if(FACING.equals(Right)){
            XMinus();
        }
        else{
            YPlus();
        }

        Map(XSTEP,YSTEP,XList,YList);
        PPSTATE = PSTATE;
        PSTATE = Down;
    }

    public void XPlus(){
        XSTEP = XSTEP + 1;
        YSTEP = YSTEP;
    }
    public void YPlus(){
        XSTEP = XSTEP;
        YSTEP = YSTEP + 1;
    }
    public void XMinus(){
        XSTEP = XSTEP - 1;
        YSTEP = YSTEP;
    }
    public void YMinus(){
        XSTEP = XSTEP;
        YSTEP = YSTEP - 1;
    }
    public void XPlusYPlus(){
        XSTEP = XSTEP + 1;
        YSTEP = YSTEP + 1;
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
