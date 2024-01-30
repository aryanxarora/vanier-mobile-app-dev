package ca.vanier.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button reverseStringBtn = (Button) findViewById(R.id.reverseStringBtn);
        Button mathOperationsBtn = (Button) findViewById(R.id.mathOperationsBtn);
        Button tempProjectBtn = (Button) findViewById(R.id.tempProjectBtn);
        Button storyProjectBtn = (Button) findViewById(R.id.storyProjectBtn);

        reverseStringBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReverseStringActivity.class);
                startActivity(intent);
            }
        });

        mathOperationsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MathOperationsActivity.class);
                startActivity(intent);
            }
        });

        tempProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TempProjectActivity.class);
                startActivity(intent);
            }
        });

        storyProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StoryProjectActivity.class);
                startActivity(intent);
            }
        });
    }
}