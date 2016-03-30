package com.tjsse.egteam.entertainment_guider;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.app.AlertDialog;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by Angel on 16/3/27.
 */
public class ShakeFragment extends Fragment implements SensorEventListener {

    private Button btnFood;
    private Button btnFilm;
    private Button btnHotel;
    private Button btnMusic;
    private Button btnSports;
    private Button btnEntertainment;

    private ImageButton buttonFood;
    private ImageButton buttonFilm;
    private ImageButton buttonHotel;
    private ImageButton buttonMusic;
    private ImageButton buttonSports;
    private ImageButton buttonEntertainment;

    private Boolean allowShake=true;

    private float lastX=0;
    private float lastY=0;
    private float lastZ=0;

    SensorManager sensorManager = null;
    Vibrator vibrator = null;

    //TODO: Use Intent to pass parameter-"type".
    //Intent intent=new Intent(getActivity(),MapActivity.class);
    String type = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {

        //Get UI components and set onClick action.
        View view = inflater.inflate(R.layout.fragment_shake, container, false);

        btnFood = (Button) view.findViewById(R.id.btn_food_fragShake);
        btnFilm = (Button) view.findViewById(R.id.btn_film_fragShake);
        btnHotel = (Button) view.findViewById(R.id.btn_hotel_fragShake);
        btnMusic = (Button) view.findViewById(R.id.btn_music_fragShake);
        btnSports = (Button) view.findViewById(R.id.btn_sports_fragShake);
        btnEntertainment = (Button) view.findViewById(R.id.btn_entertainment_fragShake);

        buttonFood = (ImageButton) view.findViewById(R.id.button_food_fragShake);
        buttonFilm = (ImageButton) view.findViewById(R.id.button_film_fragShake);
        buttonHotel = (ImageButton) view.findViewById(R.id.button_hotel_fragShake);
        buttonMusic = (ImageButton) view.findViewById(R.id.button_music_fragShake);
        buttonSports = (ImageButton) view.findViewById(R.id.button_sports_fragShake);
        buttonEntertainment = (ImageButton) view.findViewById(R.id.button_entertainment_fragShake);
//        Log.e("null", "onCreateView: " + buttonFood + buttonFilm + buttonHotel + buttonMusic + buttonSports + buttonEntertainment);

        OnClickListener commonOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFilm.setTextColor(0xFF488eb3);
                btnEntertainment.setTextColor(0xFF488eb3);
                btnFood.setTextColor(0xFF488eb3);
                btnHotel.setTextColor(0xFF488eb3);
                btnMusic.setTextColor(0xFF488eb3);
                btnSports.setTextColor(0xFF488eb3);

                Log.e("click", "onClick: common clicked");

                switch (v.getId()) {
                    case R.id.btn_food_fragShake:
                        type = "food";
                        btnFood.setTextColor(0xFF000000);
                        Log.e("btnf", "onClick: btn_food");
                        break;
                    case R.id.btn_film_fragShake:
                        type = "film";
                        btnFilm.setTextColor(0xFF000000);
                        break;
                    case R.id.btn_hotel_fragShake:
                        type = "hotel";
                        btnHotel.setTextColor(0xFF000000);
                        break;
                    case R.id.btn_music_fragShake:
                        type = "music";
                        btnMusic.setTextColor(0xFF000000);
                        break;
                    case R.id.btn_sports_fragShake:
                        type = "sports";
                        btnSports.setTextColor(0xFF000000);
                        break;
                    case R.id.btn_entertainment_fragShake:
                        type = "entertainment";
                        btnEntertainment.setTextColor(0xFF000000);
                        break;
                    case R.id.button_food_fragShake:
                        type = "food";
                        btnFood.setTextColor(0xFF000000);
                        break;
                    case R.id.button_film_fragShake:
                        type = "film";
                        btnFilm.setTextColor(0xFF000000);
                        break;
                    case R.id.button_hotel_fragShake:
                        type = "hotel";
                        btnHotel.setTextColor(0xFF000000);
                        break;
                    case R.id.button_music_fragShake:
                        type = "music";
                        btnMusic.setTextColor(0xFF000000);
                        break;
                    case R.id.button_sports_fragShake:
                        type = "sports";
                        btnSports.setTextColor(0xFF000000);
                        break;
                    case R.id.button_entertainment_fragShake:
                        type = "entertainment";
                        btnEntertainment.setTextColor(0xFF000000);
                        break;
                }
            }
        };

        btnFood.setOnClickListener(commonOnClickListener);
        btnFilm.setOnClickListener(commonOnClickListener);
        btnHotel.setOnClickListener(commonOnClickListener);
        btnMusic.setOnClickListener(commonOnClickListener);
        btnSports.setOnClickListener(commonOnClickListener);
        btnEntertainment.setOnClickListener(commonOnClickListener);

        buttonFood.setOnClickListener(commonOnClickListener);
        buttonFilm.setOnClickListener(commonOnClickListener);
        buttonHotel.setOnClickListener(commonOnClickListener);
        buttonMusic.setOnClickListener(commonOnClickListener);
        buttonSports.setOnClickListener(commonOnClickListener);
        buttonEntertainment.setOnClickListener(commonOnClickListener);

        //Get Vibrate Sensor.
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!allowShake) {
            return;
        }
        int sensorType = event.sensor.getType();
        //value[0]: X axes
        //value[1]: Y axes
        //value[2]: Z axes
        float[] values = event.values;
        // 获得x,y,z坐标

        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            if ((Math.abs(values[0]) > 15) ||
                    (Math.abs(values[1]) > 15) ||
                    (Math.abs(values[2]) > 15)) {
                //vibrate at 200ms, 400ms, 600ms
//                long[] pattern = {100, 200, 300, 400, 500, 600, 700};
//                vibrator.vibrate(pattern, -1);
                vibrator.vibrate(1000);
                Log.e("shake OK", "shake OK!!!" );
            }else {
                Log.e("shake", "x:"+values[0]+"y:"+values[1]+"z:"+values[2] );
                return;
            }
        }else {
            return;
        }

//        float x = values[0];
//        float y = values[1];
//        float z = values[2];
//        // 获得x,y,z的变化值
//        float deltaX = x - lastX;
//        float deltaY = y - lastY;
//        float deltaZ = z - lastZ;
//        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
//                * deltaZ);
//        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
//            if (speed>20)) {
//                //vibrate at 200ms, 400ms, 600ms
////                long[] pattern = {100, 200, 300, 400, 500, 600, 700};
////                vibrator.vibrate(pattern, -1);
//                vibrator.vibrate(1000);
//            }
//        }


        if (type == null) {
            allowShake = false;
            AlertDialog ad = new AlertDialog.Builder(getActivity()).setMessage("请选择一个项目")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            allowShake = true;
                        }
                    }).show();
        }else {
            AlertDialog ad = new AlertDialog.Builder(getActivity()).setMessage("good there")
                    .setPositiveButton("确定", null).show();
            //// TODO: 2016/3/31 do the jump
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //当传感器精度改变时回调该方法，Do nothing.
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
}
