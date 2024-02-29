package ca.vanier.reciperack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AppActivity extends AppCompatActivity {

    // APP ELEMENTS
    TextView userEmailTextView;
    ImageButton categoryButton, searchButton;

    // DATA MEMBERS
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        // INIT OBJECTS
        userEmailTextView = findViewById(R.id.displayUserEmail);
        categoryButton = findViewById(R.id.categoryButton);
        searchButton = findViewById(R.id.searchButton);
        // GET INTENT
        userId = getIntent().getStringExtra("userIdIntent");

        // GET FIRESTORE DATA
//        FirestoreHelper.fetchUserData(userId);

        // SET APP CONTENT
        userEmailTextView.setText(userId);


        categoryButton.setOnClickListener(view -> {
            Toast.makeText(this, "Category", Toast.LENGTH_SHORT).show();
        });

        searchButton.setOnClickListener(view -> {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        });
    }

}