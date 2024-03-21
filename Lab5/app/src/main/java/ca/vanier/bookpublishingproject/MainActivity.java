package ca.vanier.bookpublishingproject;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView pubId, pubName, pubAdd;
    Button prev, next, details, show;
    int index = 0;
    ArrayList<Publisher> publishers = new ArrayList<Publisher>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pubId = findViewById(R.id.textViewPubID);
        pubName = findViewById(R.id.textViewPubName);
        pubAdd = findViewById(R.id.textViewPubAdd);
        prev = findViewById(R.id.buttonPrev);
        next = findViewById(R.id.buttonNext);
        details = findViewById(R.id.buttonPubDetails);
        show = findViewById(R.id.buttonShowBook);
        publishers.add(new Publisher("1", "OReilly Media USA", "Canada"));
        publishers.add(new Publisher("2", "Penguin Random House", "USA"));
        publishers.add(new Publisher("3", "HarperCollins", "UK"));
        publishers.add(new Publisher("4", "Simon & Schuster", "USA"));
        publishers.add(new Publisher("5", "Hachette Book Group", "France"));
        publishers.add(new Publisher("6", "Macmillan Publishers", "USA"));
        setValues();

        prev.setOnClickListener(view -> {
            index--;
            if(index < 0){
                index = publishers.size() - 1;
            }
            setValues();
        });

        next.setOnClickListener(view -> {
            index++;
            if(index >= publishers.size()){
                index = 0;
            }
            setValues();
        });

        details.setOnClickListener(view -> {
            Intent intent = new Intent(this, PublisherActivity.class);
            startActivity(intent);
        });

    }

    public void setValues(){
        Publisher publisher = publishers.get(index);
        pubId.setText(publisher.getId());
        pubName.setText(publisher.getName());
        pubAdd.setText(publisher.getAddress());
    }
}