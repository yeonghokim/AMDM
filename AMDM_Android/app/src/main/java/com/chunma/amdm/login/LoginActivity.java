package com.chunma.amdm.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chunma.amdm.MainActivity;
import com.chunma.amdm.R;
import com.chunma.amdm.TCPconnect.TCPconnecter;

import static java.lang.Thread.sleep;

public class LoginActivity extends AppCompatActivity {
    EditText ID_text;
    EditText PW_text;

    String ID;
    String PW;

    LinearLayout loading_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ID_text = (EditText)findViewById(R.id.login_ID);
        PW_text = (EditText)findViewById(R.id.login_PW);

        FrameLayout loginLayout = (FrameLayout) findViewById(R.id.loginlayout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.loading_dialog, loginLayout, true);

        loading_layout = (LinearLayout)findViewById(R.id.loadinglayout);
    }
    public void btnClick(View view) {
        ID = ID_text.getText().toString();
        PW = PW_text.getText().toString();

        LoginConnecter connecter = new LoginConnecter();
        connecter.setRequestString("login ID PW");
        connecter.start();
    }

    public class LoginConnecter extends TCPconnecter{
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading_layout.setAlpha(1.0f);
                }
            });
            final ImageView imgFrame = (ImageView) loading_layout.findViewById(R.id.iv_frame_loading);
            final AnimationDrawable frameAnimation = (AnimationDrawable) imgFrame.getBackground();

            imgFrame.post(new Runnable() {
                @Override
                public void run() {
                    frameAnimation.start();
                }
            });

            super.run();
            frameAnimation.stop();

            //if(this.getAnswerString().equals("")){
            if(ID.equals("admin")&&PW.equals("admin")){
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this,"아이디나 비밀번호가 맞지 않습니다.",Toast.LENGTH_LONG).show();
                    }
                });
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading_layout.setAlpha(0.0f);
                }
            });
        }
    }
}