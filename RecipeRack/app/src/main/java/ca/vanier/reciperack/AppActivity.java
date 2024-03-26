package ca.vanier.reciperack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    Button home, category, favs;

    // DATA MEMBERS
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.recipe_toolbar);
        this.setSupportActionBar(toolbar);
        setTitle("");

        // INIT OBJECTS
        userEmailTextView = findViewById(R.id.displayUserEmail);

        // GET INTENT
        userId = getIntent().getStringExtra("userIdIntent");

        // GET FIRESTORE DATA
//        FirestoreHelper.fetchUserData(userId);

        // SET APP CONTENT
        userEmailTextView.setText(userId);
        home = findViewById(R.id.homeBtn);
        category = findViewById(R.id.categoryBtn);
        favs = findViewById(R.id.favBtn);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new HomeFragment()).commit();

        }

        home.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, HomeFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Home")
                    .commit();
        });

        category.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, CategoryFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Category")
                    .commit();
        });

        favs.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, FavortiesFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Favs")
                    .commit();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reciperack, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addoption){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, new AddRecipeFragment())
                    .commit();
        } else if(item.getItemId() == R.id.searchoption){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, new SearchFragment())
                    .commit();
        } else if (item.getItemId() == R.id.settingsoption){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, new SettingsFragment())
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}