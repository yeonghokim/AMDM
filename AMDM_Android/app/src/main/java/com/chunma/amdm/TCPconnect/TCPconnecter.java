package com.chunma.amdm.TCPconnect;

import android.app.Activity;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPconnecter{
    private static final String TAG = "";

    private Socket socket;

    private Activity activity;

    private String HostIP;
    private int Port;

    private InputStream dataInputStream;
    private OutputStream dataOutputStream;

    public void Confirmdata(){
        int SDK_INT = android.os.Build.VERSION.SDK_INT;

        if (SDK_INT > 8){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public boolean CreateTcpSocket(String hostIP,int Port){
        try { //클라이언트 소켓 생성
            this.Port=Port;
            HostIP=hostIP;

            socket = new Socket(hostIP, Port);

            return true;
        }  catch (UnknownHostException uhe) {
// 소켓 생성 시 전달되는 호스트(www.unknown-host.com)의 IP를 식별할 수 없음.

            Log.e(TAG," 생성 Error : 호스트의 IP 주소를 식별할 수 없음.(잘못된 주소 값 또는 호스트이름 사용)");

        } catch (IOException ioe) {
// 소켓 생성 과정에서 I/O 에러 발생. 주로 네트워크 응답 없음.

            Log.e(TAG," 생성 Error : 네트워크 응답 없음");

        } catch (SecurityException se) {
// security manager에서 허용되지 않은 기능 수행.

            Log.e(TAG," 생성 Error : 보안(Security) 위반에 대해 보안 관리자(Security Manager)에 의해 발생. (프록시(proxy) 접속 거부, 허용되지 않은 함수 호출)");

        } catch (IllegalArgumentException le) {
// 소켓 생성 시 전달되는 포트 번호(65536)이 허용 범위(0~65535)를 벗어남.

            Log.e(TAG," 생성 Error : 메서드에 잘못된 파라미터가 전달되는 경우 발생. (0~65535 범위 밖의 포트 번호 사용, null 프록시(proxy) 전달)");

        }
        return false;
    }

    class ConnectThread extends Thread {

        public void run() {


            try {

                dataInputStream = socket.getInputStream();
                dataOutputStream = socket.getOutputStream();

            }catch (IOException e) {
                e.printStackTrace();
            }


            byte[] buffer = new byte[1024];
            int bytes;
            while(true){
                try{
                    bytes =dataInputStream.read(buffer);
                    String str = new String(buffer,0,bytes);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class SendThread extends Thread{
        private String sendstring;

        public void setSendstring(String str){
            sendstring=str;
        }

        public void run(){
            byte[] outbyte = sendstring.getBytes();
            try {
                dataOutputStream.write(outbyte);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
