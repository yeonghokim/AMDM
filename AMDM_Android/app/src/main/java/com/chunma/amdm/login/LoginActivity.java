package com.chunma.amdm.login;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

        import com.chunma.amdm.MainActivity;
        import com.chunma.amdm.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void btnClick(View view) {

        startActivity(new Intent(this, MainActivity.class));
    }
}