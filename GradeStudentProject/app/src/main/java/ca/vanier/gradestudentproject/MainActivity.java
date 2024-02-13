package ca.vanier.gradestudentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView studentView = (TextView) findViewById(R.id.studentTextView);
        TextView gradeView = (TextView) findViewById(R.id.gradeTextView);
        Button displayGradeButton = (Button) findViewById(R.id.gradeButton);
        Button prevButton = (Button) findViewById(R.id.prevButton);
        Button nextButton = (Button) findViewById(R.id.nextButton);

        Grade[] grades = new Grade[6];
        grades[0] = new Grade(1,"Graham","Bill",69,70,98,80,90);
        grades[1] = new Grade(2,"Sanchez","Jim",88,72,90,83,93 );
        grades[2] = new Grade(3,"White","Peter",85,80,45,93,70 );
        grades[3] = new Grade(4,"Phelp","David",70,60,60,90,70);
        grades[4] = new Grade(5,"Lewis","Sheila",50,76,87,59,72);
        grades[5] = new Grade(6,"James","Thomas",89,99,97,98,99);

        studentView.setText(String.format("Student: %s %s", grades[index].getFirstName(), grades[index].getLastName()));
        displayGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gradeView.setText("Grade average is: " + Double.toString(grades[index].calculateGradeAverage()));
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index-1)%6;
                studentView.setText(String.format("Student: %s %s", grades[index].getFirstName(), grades[index].getLastName()));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index+1)%6;
                studentView.setText(String.format("Student: %s %s", grades[index].getFirstName(), grades[index].getLastName()));
            }
        });

    }
}