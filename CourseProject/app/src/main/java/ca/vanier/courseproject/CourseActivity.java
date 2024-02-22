package ca.vanier.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        EditText courseNumber = (EditText) findViewById(R.id.editTextCourseNumber);
        EditText courseName = (EditText) findViewById(R.id.editTextCourseTitle);
        EditText courseEnroll = (EditText) findViewById(R.id.editTextMaxEnroll);
        EditText courseCredit = (EditText) findViewById(R.id.editTextCourseCredit);
        Button updateBtn = (Button) findViewById(R.id.updateBtn);

        courseNumber.setText(getIntent().getStringExtra("intentId"));
        courseName.setText(getIntent().getStringExtra("intentName"));
        courseEnroll.setText(getIntent().getStringExtra("intentEnroll"));
        courseCredit.setText(getIntent().getStringExtra("intentCredit"));

        updateBtn.setOnClickListener(view -> {
            Intent resultIntent = new Intent();
            // Assuming you have variables for updated values
            resultIntent.putExtra("updatedCourseNo", courseNumber.getText().toString());
            resultIntent.putExtra("updatedCourseName", courseName.getText().toString());
            resultIntent.putExtra("updatedMaxEnroll", Integer.parseInt(courseEnroll.getText().toString()));
            setResult(Activity.RESULT_OK, resultIntent);
            finish(); // Close the activity
        });

    }
}