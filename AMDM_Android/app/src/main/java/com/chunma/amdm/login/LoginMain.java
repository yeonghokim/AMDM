package com.chunma.amdm.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chunma.amdm.R;

public class LoginMain extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);

        SharedPreferences preferences = getSharedPreferences("AMDM_data",MODE_PRIVATE);

        if(!preferences.getString("loginType","").equals("")){
            //로그인한 기록이 있음
        }


    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.exebutton :
                    //간부로 로그인
                break ;
            case R.id.soldierbutton :
                    //병사로 로그인
                break ;
        }
    }
}