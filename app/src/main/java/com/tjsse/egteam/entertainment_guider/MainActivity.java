package com.tjsse.egteam.entertainment_guider;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{
    private Button btnRecommend;
    private Button btnShake;
    private Button btnRoute;

    private Fragment recommendFragment;
    private Fragment shakeFragment;
    private Fragment routeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecommend=(Button)findViewById(R.id.button_recommend);
        btnShake=(Button)findViewById(R.id.button_shake);
        btnRoute=(Button)findViewById(R.id.button_route);

        btnRecommend.setOnClickListener(this);
        btnShake.setOnClickListener(this);
        btnRoute.setOnClickListener(this);

        btnRecommend.setTextColor(0xFF000000);

        setDefaultFragment();
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        btnRoute.setTextColor(0xFF488eb3);
        btnRecommend.setTextColor(0xFF488eb3);
        btnShake.setTextColor(0xFF488eb3);

        ((Button)v).setTextColor(0xFF000000);

        switch(v.getId()){
            case R.id.button_recommend:
                if(recommendFragment==null){
                    recommendFragment=new RecommendFragment();
                }
                ft.replace(R.id.id_content,recommendFragment);
                break;
            case R.id.button_shake:
                if(shakeFragment==null){
                    shakeFragment=new ShakeFragment();
                }
                ft.replace(R.id.id_content,shakeFragment);
                break;
            case R.id.button_route:
                if(routeFragment==null){
                    routeFragment=new RouteFragment();
                }
                ft.replace(R.id.id_content,routeFragment);
                break;
        }

        ft.commit();

    }

    private void setDefaultFragment() {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        RecommendFragment recommendFragment=new RecommendFragment();
        ft.replace(R.id.id_content, recommendFragment);
        ft.commit();
    }

}
