package com.chunma.amdm.TCPconnect;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.FragmentActivity;

import com.chunma.amdm.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPconnecter extends Thread {
    private static final String TAG = "";
    private static final boolean IS_SERVERON=false;

    public static String HostIP="127.0.0.1";
    public static int Port=12345;

    private Socket socket;
    private String requestString;

    private String answerString;

    public void setRequestString(String str){
        requestString=str;
    }

    public String getAnswerString(){ return answerString;}

    @Override
    public void run() {
        if(IS_SERVERON) {
            try { //클라이언트 소켓 생성

                socket = new Socket(HostIP, Port);
                Log.d(TAG, "Socket 생성, 연결.");

                InputStream dataInputStream = socket.getInputStream();
                OutputStream dataOutputStream = socket.getOutputStream();

                String OutData = requestString;
                byte[] data = OutData.getBytes();
                dataOutputStream.write(data);

                answerString = "";
                byte[] buffer = new byte[1024];
                int bytes;
                while (true) {
                    bytes = dataInputStream.read(buffer);
                    String string = new String(buffer, 0, bytes);
                    if (string.equals("")) {
                        break;
                    } else {
                        answerString += string;
                    }
                }
                socket.close();
            } catch (UnknownHostException uhe) {
                Log.e(TAG, " 생성 Error : 호스트의 IP 주소를 식별할 수 없음.(잘못된 주소 값 또는 호스트이름 사용)");
            } catch (IOException ioe) {
                Log.e(TAG, " 생성 Error : 네트워크 응답 없음");
            } catch (SecurityException se) {
                Log.e(TAG, " 생성 Error : 보안(Security) 위반에 대해 보안 관리자(Security Manager)에 의해 발생. (프록시(proxy) 접속 거부, 허용되지 않은 함수 호출)");
            } catch (IllegalArgumentException le) {
                Log.e(TAG, " 생성 Error : 메서드에 잘못된 파라미터가 전달되는 경우 발생. (0~65535 범위 밖의 포트 번호 사용, null 프록시(proxy) 전달)");
            }
        }else {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
