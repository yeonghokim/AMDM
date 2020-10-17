package com.chunma.amdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chunma.amdm.mainfragment.MainLockFragment;
import com.chunma.amdm.mainfragment.MainSetupFragment;
import com.chunma.amdm.mainfragment.MainStaticsFragment;

public class MainActivity extends AppCompatActivity {

    MainLockFragment mainLockFragment = new MainLockFragment();
    MainSetupFragment mainSetupFragment = new MainSetupFragment();
    MainStaticsFragment mainStaticsFragment = new MainStaticsFragment();

    protected FragmentManager fragmentManager;
    protected FragmentTransaction transaction;

    int nowPage=2;//main

    ImageButton staticsbutton;
    ImageButton mainbutton;
    ImageButton setupbutton;

    TextView staticsText;
    TextView mainText;
    TextView setupText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmanager,mainLockFragment);
        transaction.commit();

        mainbutton=(ImageButton)findViewById(R.id.mainbutton);
        mainText=(TextView)findViewById(R.id.mainbuttontext);

        staticsbutton=(ImageButton)findViewById(R.id.staticsbutton);
        staticsText=(TextView)findViewById(R.id.staticsbuttontext);

        setupbutton=(ImageButton)findViewById(R.id.setupbutton);
        setupText=(TextView)findViewById(R.id.setupbuttontext);

        mainText.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.mainNavigaionButtonTextSelect));
        mainbutton.setImageResource(R.drawable.night_mode_select);
    }

    public void onClickSetup(View v){
        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmanager,mainSetupFragment);
        transaction.commit();
        changeImage(1);
    }
    public void onClickLock(View v){

        fragmentManager =getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmanager,mainLockFragment);
        transaction.commit();
        changeImage(2);

    }
    public void onClickStatics(View v){

        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragmentmanager,mainStaticsFragment);
        transaction.commit();
        changeImage(3);
    }
    public void changeImage(int chagepage){
        switch (nowPage){
            case 1:
                staticsText.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.mainNavigaionButtonText));
                staticsbutton.setImageResource(R.drawable.night_mode);
            case 2:
                mainText.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.mainNavigaionButtonText));
                mainbutton.setImageResource(R.drawable.night_mode);
            case 3:
                setupText.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.mainNavigaionButtonText));
                setupbutton.setImageResource(R.drawable.night_mode);
        }
        switch (chagepage){
            case 1:
                staticsText.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.mainNavigaionButtonTextSelect));
                staticsbutton.setImageResource(R.drawable.night_mode_select);
            case 2:
                mainText.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.mainNavigaionButtonTextSelect));
                mainbutton.setImageResource(R.drawable.night_mode_select);
            case 3:
                setupText.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.mainNavigaionButtonTextSelect));
                setupbutton.setImageResource(R.drawable.night_mode_select);
        }
        nowPage=chagepage;
    }
}