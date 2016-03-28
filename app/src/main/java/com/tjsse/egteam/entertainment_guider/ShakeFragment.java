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
import android.view.View.OnClickListener;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Angel on 16/3/27.
 */
public class ShakeFragment extends Fragment implements SensorEventListener,OnClickListener{

    private Button btnFood;
    private Button btnFilm;
    private Button btnHotel;
    private Button btnMusic;
    private Button btnSports;
    private Button btnEntertainment;

    SensorManager sensorManager=null;
    Vibrator vibrator=null;

    String type=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){

        //Get UI components and set onClick action.
        View view= inflater.inflate(R.layout.fragment_shake,container,false);

        btnFood= (Button) view.findViewById(R.id.btn_food);
        btnFilm= (Button) view.findViewById(R.id.btn_film);
        btnHotel= (Button) view.findViewById(R.id.btn_hotel);
        btnMusic= (Button) view.findViewById(R.id.btn_music);
        btnSports= (Button) view.findViewById(R.id.btn_music);
        btnEntertainment= (Button) view.findViewById(R.id.btn_entertainment);

        btnFood.setOnClickListener(this);
        btnFilm.setOnClickListener(this);
        btnHotel.setOnClickListener(this);
        btnMusic.setOnClickListener(this);
        btnSports.setOnClickListener(this);
        btnEntertainment.setOnClickListener(this);

        //Get Vibrate Sensor.
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_food:
                type="food";
                break;
            case R.id.btn_film:
                type="film";
                break;
            case R.id.btn_hotel:
                type="hotel";
                break;
            case R.id.btn_music:
                type="music";
                break;
            case R.id.btn_sports:
                type="sports";
                break;
            case R.id.btn_entertainment:
                type="entertainments";
                break;
        }

    }
}
