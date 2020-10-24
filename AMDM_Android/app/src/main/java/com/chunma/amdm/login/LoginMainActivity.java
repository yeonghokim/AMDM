package com.chunma.amdm.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.chunma.amdm.MainActivity;
import com.chunma.amdm.R;
import com.chunma.amdm.SplashActivity;

public class LoginMainActivity extends AppCompatActivity{
    private final int MY_PERMISSIONS_REQUEST_FOREGROUND_SERVICE=1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;

        if (SDK_INT > 8){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        int permssionCheck = PackageManager.PERMISSION_GRANTED;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE);
            if (permssionCheck!= PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"권한 승인이 필요합니다",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.FOREGROUND_SERVICE)) {
                    Toast.makeText(this,"사용을 위해 권한이 필요합니다.",Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.FOREGROUND_SERVICE},  MY_PERMISSIONS_REQUEST_FOREGROUND_SERVICE);
                    Toast.makeText(this,"사용을 위해 권한이 필요합니다.",Toast.LENGTH_SHORT).show();
                }
            }
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
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FOREGROUND_SERVICE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}