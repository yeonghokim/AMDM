package com.chunma.amdm.TCPconnect;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialog;

import com.chunma.amdm.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPconnecter extends Thread {
    private static final String TAG = "";

    private Socket socket;

    public Activity activity;

    private String HostIP="127.0.0.1";
    private int Port;

    private InputStream dataInputStream;
    private OutputStream dataOutputStream;

    Dialog progressDialog;

    public void SetTcpSocket(String hostIP,int Port){
        this.Port=Port;
        HostIP=hostIP;
    }

    String requestString;

    public void setRequestString(String str){
        requestString=str;
    }

    @Override
    public void run() {
        /*try { //클라이언트 소켓 생성

            socket = new Socket(HostIP, Port);
            Log.d(TAG, "Socket 생성, 연결.");

            dataInputStream = socket.getInputStream();
            dataOutputStream = socket.getOutputStream();

            String OutData = requestString;
            byte[] data = OutData.getBytes();
            dataOutputStream.write(data);

            byte[] buffer = new byte[1024];
            int bytes;
            while(true){
                bytes =dataInputStream.read(buffer);
                String str = new String(buffer,0,bytes);
            }

            socket.close();

        }  catch (UnknownHostException uhe) {
            Log.e(TAG," 생성 Error : 호스트의 IP 주소를 식별할 수 없음.(잘못된 주소 값 또는 호스트이름 사용)");
        } catch (IOException ioe) {
            Log.e(TAG," 생성 Error : 네트워크 응답 없음");
        } catch (SecurityException se) {
            Log.e(TAG," 생성 Error : 보안(Security) 위반에 대해 보안 관리자(Security Manager)에 의해 발생. (프록시(proxy) 접속 거부, 허용되지 않은 함수 호출)");
        } catch (IllegalArgumentException le) {
            Log.e(TAG," 생성 Error : 메서드에 잘못된 파라미터가 전달되는 경우 발생. (0~65535 범위 밖의 포트 번호 사용, null 프록시(proxy) 전달)");
        }*/
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //서비스 실행
    }
    public void progressON(String message) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (progressDialog != null && progressDialog.isShowing()) {
            progressSET(message);
        } else {
            progressDialog = new Dialog(activity);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.loading_dialog);
            progressDialog.show();
        }

        /*final ImageView img_loading_frame = (ImageView) progressDialog.findViewById(R.id.iv_frame_loading);
        final AnimationDrawable frameAnimation = (AnimationDrawable) img_loading_frame.getBackground();
        img_loading_frame.post(new Runnable() {
            @Override
            public void run() {
                frameAnimation.start();
            }
        });

        TextView tv_progress_message = (TextView) progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.setText(message);
        }*/
    }
    public void progressSET(String message) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        TextView tv_progress_message = (TextView) progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.setText(message);
        }
    }
    public void progressOFF() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
