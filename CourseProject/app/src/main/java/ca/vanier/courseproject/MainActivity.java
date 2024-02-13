package ca.vanier.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int index = 0;
    int len = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            index = savedInstanceState.getInt("currentIndex", 0);
        }

        Course[] courses = new Course[len];
        courses[0] = new Course("MIS 101", "Intro to Info Systems", 140);
        courses[1] = new Course("MIS 301", "Systems Analysis", 35);
        courses[2] = new Course("MIS 441", "Database Management", 12);
        courses[3] = new Course("CS 155", "Programming in C++", 90);

        TextView currentCourseTextView = (TextView) findViewById(R.id.currentCourse);
        TextView totalFeesTextView = (TextView) findViewById(R.id.totalFees);
        Button calculateFeesButton = (Button) findViewById(R.id.totalFeesBtn);
        Button nextCouseButton = (Button) findViewById(R.id.nextCourseBtn);

        currentCourseTextView.setText(String.format("%s %s", courses[index].getCourse_no(), courses[index].getCourse_name()));
        calculateFeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalFeesTextView.setText(String.format("Total course fees: %.2f", courses[index].calculateCourseTotalFees()));
            }
        });

        nextCouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index + 1)%len;
                currentCourseTextView.setText(String.format("%s %s", courses[index].getCourse_no(), courses[index].getCourse_name()));
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex", index);
    }
}