package ca.vanier.reciperack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.*;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password;
    Button register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextRegisterEmail);
        password = findViewById(R.id.editTextRegisterPassword);
        register = findViewById(R.id.buttonRegisterSubmit);
        register.setOnClickListener(view -> registerUser());
    }

    public void registerUser(){
        String registerEmail = email.getText().toString();
        String registerPassword = password.getText().toString();
        System.out.println(registerEmail);
        System.out.println(registerPassword);
        mAuth.createUserWithEmailAndPassword(registerEmail, registerPassword).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                Log.d("AUTH", "Registration Successful");
                FirebaseUser user = mAuth.getCurrentUser();
                Intent appIntent = new Intent(this, AppActivity.class);
                assert user != null;
                appIntent.putExtra("userEmailIntent", user.getEmail());
                startActivity(appIntent);
            } else {
                Log.w("AUTH", "createUserWithEmail:failure", task.getException());
                if (task.getException() instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(this, "Email is already in use", Toast.LENGTH_SHORT).show();
                } else if (task.getException() instanceof FirebaseAuthWeakPasswordException){
                    Toast.makeText(this, "Password is too weak", Toast.LENGTH_SHORT).show();
                } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                    Toast.makeText(this, "Incorrect email format", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}