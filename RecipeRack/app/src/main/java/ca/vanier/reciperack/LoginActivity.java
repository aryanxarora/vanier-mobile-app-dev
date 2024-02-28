package ca.vanier.reciperack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.*;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextLoginEmail);
        password = findViewById(R.id.editTextLoginPassword);
        login = findViewById(R.id.buttonLoginSubmit);

        login.setOnClickListener(view -> loginUser());

    }

    public void loginUser(){
        String loginEmail = email.getText().toString();
        String loginPassword = password.getText().toString();
        mAuth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                Log.d("AUTH", "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                Intent appIntent = new Intent(this, AppActivity.class);
                assert user != null;
                appIntent.putExtra("userEmailIntent", user.getEmail());
                startActivity(appIntent);
            } else {
                Log.w("AUTH", "createUserWithEmail:failure", task.getException());
                Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}