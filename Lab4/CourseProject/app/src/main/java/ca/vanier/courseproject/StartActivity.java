package ca.vanier.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button login, register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        login = findViewById(R.id.startLogin);
        register = findViewById(R.id.startRegister);

        login.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}