package com.chunma.amdm.TurnOnPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chunma.amdm.R;

public class TurnOnActivity extends AppCompatActivity {

    private TextView timeTextView;
    private TextView dialogTimeTextView;
    int timesecond=60;
    Context context;
    Thread timeThread;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_on);

        timeTextView = (TextView)findViewById(R.id.broad_timeTextview);
        Button button = (Button)findViewById(R.id.broad_delaytimebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timesecond=60;
                Message msg = new Message();
                msg.arg1 = timesecond;
                handler.sendMessage(msg);
            }
        });

        context = this;

        //비행기 모드 온
        if(!isAirModeOn()) {
            Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
            startActivity(intent);
        }

        //타임 시작
        timeThread = new Thread(new TimeThread());
        timeThread.start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) { timeTextView.setText(msg.arg1+"초 뒤에\n휴대폰이 꺼집니다.");
        }
    };

    @SuppressLint("HandlerLeak")
    Handler subhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) { dialogTimeTextView.setText(msg.arg1+"초 뒤에 휴대폰이 꺼집니다.\""); }
    };

    public class TimeThread implements Runnable {
        @Override
        public void run() {
            try {
                while (timesecond>=0) { //일시정지를 누르면 멈춤
                    Message msg = new Message();
                    msg.arg1 = timesecond--;
                    handler.sendMessage(msg);
                        Thread.sleep(1000);
                }
                builder = new AlertDialog.Builder(context);
                builder.setTitle("다이얼로그 제목");

                LayoutInflater inflater =getLayoutInflater();
                View view = inflater.inflate(R.layout.broadcast_dialog,null);
                builder.setView(view);

                dialogTimeTextView = view.findViewById(R.id.dialog_textview);

                DialongListener listener = new DialongListener();
                builder.setPositiveButton("시간 연장",listener);
                builder.setNegativeButton("휴대폰 종료",listener);

                builder.show();

                int subtime=10;
                while (subtime>=0) { //일시정지를 누르면 멈춤
                    Message msg = new Message();
                    msg.arg1 = subtime--;
                    subhandler.sendMessage(msg);

                    Thread.sleep(1000);

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //휴대폰 꺼짐
        }
    }

    class DialongListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_NEGATIVE:
                    //휴대폰 꺼짐
                    break;
                case DialogInterface.BUTTON_POSITIVE:
                    timeThread.interrupt();
                    timeThread = new Thread(new TimeThread());
                    timesecond=60;
                    timeThread.start();
                    break;
            }
        }
    }

    private Boolean isAirModeOn() {
        Boolean isAirplaneMode;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1){  // 젤리빈 이하버전
            isAirplaneMode = Settings.System.getInt(getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) == 1;
        }else{
            isAirplaneMode = Settings.Global.getInt(getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
        }
        return isAirplaneMode;
    }
}