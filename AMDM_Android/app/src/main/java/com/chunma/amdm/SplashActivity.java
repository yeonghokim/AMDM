package com.chunma.amdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.chunma.amdm.login.LoginMainActivity;

public class SplashActivity extends AppCompatActivity {
    Animation anim;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        linearLayout=(LinearLayout)findViewById(R.id.activity_splash); // Declare an imageView to show the animation.
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in); // Create the animation.
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                //로그인 기록 있는지 확인
               /* SharedPreferences preferences = getSharedPreferences("AMDM_data",MODE_PRIVATE);

                if(!preferences.getString("loginHistory","").equals(""))
                    //로그인한 기록이 있음
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                else*/
                    //로그인한 기록이 없음
                    startActivity(new Intent(SplashActivity.this, LoginMainActivity.class));
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        linearLayout.startAnimation(anim);
    }
}