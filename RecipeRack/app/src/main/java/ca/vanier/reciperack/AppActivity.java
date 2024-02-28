package ca.vanier.reciperack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AppActivity extends AppCompatActivity {

    TextView userEmailTextView;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        userEmailTextView = findViewById(R.id.displayUserEmail);
        userEmail = getIntent().getStringExtra("userEmailIntent");
        userEmailTextView.setText(userEmail);
    }
}