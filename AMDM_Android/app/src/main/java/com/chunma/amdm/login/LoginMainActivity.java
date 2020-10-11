package com.chunma.amdm.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chunma.amdm.MainActivity;
import com.chunma.amdm.R;
import com.chunma.amdm.SplashActivity;

public class LoginMainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);



    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.exebutton :
                    //간부로 로그인

                startActivity(new Intent(this, MainActivity.class));
                break ;
            case R.id.soldierbutton :
                    //병사로 로그인
                break ;
        }
    }
}