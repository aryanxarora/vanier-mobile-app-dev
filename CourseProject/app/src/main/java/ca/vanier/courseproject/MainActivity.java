package ca.vanier.courseproject;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int index = 0;
    int len = 4;

    Course[] courses = new Course[len];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            index = savedInstanceState.getInt("currentIndex", 0);
        }

        courses[0] = new Course("MIS 101", "Intro to Info Systems", 140);
        courses[1] = new Course("MIS 301", "Systems Analysis", 35);
        courses[2] = new Course("MIS 441", "Database Management", 12);
        courses[3] = new Course("CS 155", "Programming in C++", 90);

        TextView currentCourseTextView = (TextView) findViewById(R.id.currentCourse);
        TextView totalFeesTextView = (TextView) findViewById(R.id.totalFees);
        Button calculateFeesButton = (Button) findViewById(R.id.totalFeesBtn);
        Button nextCouseButton = (Button) findViewById(R.id.nextCourseBtn);
        Button detailsBtn = (Button) findViewById(R.id.detailsBtn);

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

        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsBtn.setOnClickListener(view -> {
                    Intent intent = new Intent(MainActivity.this, CourseActivity.class);
                    intent.putExtra("intentId", courses[index].getCourse_no());
                    intent.putExtra("intentName", courses[index].getCourse_name());
                    intent.putExtra("intentEnroll", Integer.toString(courses[index].getMax_enrl()));
                    intent.putExtra("intentCredit", Integer.toString(Course.getCredits()));
                    courseActivityResultLauncher.launch(intent);
                });

            }
        });

    }

    ActivityResultLauncher<Intent> courseActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Handle the result
                    Intent data = result.getData();
                    if (data != null) {
                        String updatedCourseNo = data.getStringExtra("updatedCourseNo");
                        String updatedCourseName = data.getStringExtra("updatedCourseName");
                        int updatedMaxEnroll = data.getIntExtra("updatedMaxEnroll", -1);
                        courses[index].setCourse_no(updatedCourseNo);
                        courses[index].setCourse_name(updatedCourseName);
                        courses[index].setMax_enrl(updatedMaxEnroll);
                    }
                }
            });


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex", index);
    }
}