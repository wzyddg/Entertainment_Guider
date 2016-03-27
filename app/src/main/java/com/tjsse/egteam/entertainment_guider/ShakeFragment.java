package com.tjsse.egteam.entertainment_guider;

import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Angel on 16/3/27.
 */
public class ShakeFragment extends Fragment implements SensorEventListener{

    SensorManager sensorManager=null;
    Vibrator vibrator=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
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
                vibrator.vibrate(500);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //当传感器精度改变时回调该方法，Do nothing.
    }
}
