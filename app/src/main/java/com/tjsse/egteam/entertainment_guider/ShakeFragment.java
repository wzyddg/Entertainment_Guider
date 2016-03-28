package com.tjsse.egteam.entertainment_guider;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Angel on 16/3/27.
 */
public class ShakeFragment extends Fragment implements SensorEventListener{

    private Button btnFood;
    private Button btnFilm;
    private Button btnHotel;
    private Button btnMusic;
    private Button btnSports;
    private Button btnEntertainment;

    SensorManager sensorManager=null;
    Vibrator vibrator=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){

        sensorManager= (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        vibrator= (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        return inflater.inflate(R.layout.fragment_shake,container,false);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType=event.sensor.getType();
        //value[0]: X axes
        //value[1]: Y axes
        //value[2]: Z axes
        float[] values=event.values;
        if(sensorType==Sensor.TYPE_ACCELEROMETER)
        {
            if((Math.abs(values[0])>17)||
                    (Math.abs(values[1])>17)||
                    (Math.abs(values[2])>17))
            {
                //vibrate at 200ms, 400ms, 600ms
                long[] pattern={100,200,300,400,500,600,700};
                vibrator.vibrate(pattern,-1);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //当传感器精度改变时回调该方法，Do nothing.
    }

    @Override
    public void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

}
