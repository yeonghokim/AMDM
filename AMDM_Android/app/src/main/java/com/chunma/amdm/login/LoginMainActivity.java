package com.chunma.amdm.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.chunma.amdm.MainActivity;
import com.chunma.amdm.R;
import com.chunma.amdm.SplashActivity;

public class LoginMainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;

        if (SDK_INT > 8){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.exebutton :
                    //간부로 로그인
                Intent intent =new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break ;
            case R.id.soldierbutton :
                    //병사로 로그인
                break ;
        }
    }
}