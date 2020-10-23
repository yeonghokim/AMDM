package com.chunma.amdm.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chunma.amdm.MainActivity;
import com.chunma.amdm.R;

import static java.lang.Thread.sleep;

public class LoginActivity extends AppCompatActivity {

    EditText ID_text;
    EditText PW_text;

    LinearLayout loading_layout;

    Thread loginThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ID_text = (EditText)findViewById(R.id.login_ID);
        PW_text = (EditText)findViewById(R.id.login_PW);

        loading_layout = (LinearLayout)findViewById(R.id.login_loadinglayout);
    }
    public void btnClick(View view) {
        loginThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //로딩창 보여준 후
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading_layout.setAlpha(1.0f);
                    }
                });

                /*final ImageView img_loading_frame = (ImageView) loading_layout.findViewById(R.id.iv_frame_loading);
                final AnimationDrawable frameAnimation = (AnimationDrawable) img_loading_frame.getBackground();

                img_loading_frame.post(new Runnable() {
                    @Override
                    public void run() {
                        frameAnimation.start();
                    }
                });
                */
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //frameAnimation.stop();
                //로딩창 끄고

                if(1==1){
                //if(ID_text.getText().equals("admin")&&PW_text.getText().equals("admin")){ 이게 잘못되었음
                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            goMain();
                        }
                    });*/
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading_layout.setAlpha(0.0f);
                    }
                });
            }
        });

        //로그인 하는 것 구현 하면됨
        //네트워크로 구현
        loginThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            synchronized (loginThread){
                loginThread.notifyAll();
            }
        }
        return true;
    }
}