package ca.vanier.facultymobileproject;

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
    int LEN = 5;
    Faculty[] faculties = new Faculty[LEN];
    TextView num, name, salary, rate, bonus;
    Button prev, next, facultyDetails, allFaculties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = (TextView) findViewById(R.id.faculty_num);
        name = (TextView) findViewById(R.id.textViewName);
        salary = (TextView) findViewById(R.id.textViewSalary);
        rate = (TextView) findViewById(R.id.textViewRate);
        bonus = (TextView) findViewById(R.id.textViewAmount);
        prev = (Button) findViewById(R.id.prevbutton);
        next = (Button) findViewById(R.id.nextbutton);
        facultyDetails = (Button) findViewById(R.id.buttonFacultyDetails);
        allFaculties = (Button) findViewById(R.id.buttonAllFaculties);

        faculties[0] = new Faculty(101,"Robertson", "Myra",60000.00,2.50);
        faculties[1] = new Faculty(212,"Smith","Neal",40000.00,3.00);
        faculties[2] = new Faculty(315,"Arlec", "Lisa", 55000.00, 1.50);
        faculties[3] = new Faculty(857,"Fillipo", "Paul", 30000.00, 5.00);
        faculties[4] = new Faculty(370,"Denkan", "Anais", 95000.00, 1.50);
        setValues();
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index-1)%LEN;
                setValues();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index+1)%5;
                setValues();
            }
        });


        facultyDetails.setOnClickListener(view -> {
            Intent intent  = new Intent(MainActivity.this, FacultyActivity.class);
            Faculty faculty = faculties[index];
            intent.putExtra("intentFacultyNo", faculty.getF_id());
            intent.putExtra("intentFacultyFirstName", faculty.getF_fname());
            intent.putExtra("intentFacultyLastName", faculty.getF_lname());
            intent.putExtra("intentFacultySalary", faculty.getF_salary());
            intent.putExtra("intentFacultyRate", faculty.getF_bonus());
            facultyActivityResultLauncher.launch(intent);
        });
    }

    public void setValues(){
        num.setText(String.format("Faculty no: %d", faculties[index].getF_id()));
        name.setText(String.format("Name: %s %s", faculties[index].getF_fname(), faculties[index].getF_lname()));
        salary.setText(String.format("Salary: %.2f", faculties[index].getF_salary()));
        rate.setText(String.format("Bonus Rate: %.2f", faculties[index].getF_bonus()));
        bonus.setText(String.format("Amount Bonus: %.2f", faculties[index].calcBonus()));
    }

    ActivityResultLauncher<Intent> facultyActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Handle the result
                    Intent data = result.getData();
                    if (data != null) {
                        int  updatedFacultyNo = data.getIntExtra("updatedFacultyNo", -1);
                        String updatedFacultyFirstName = data.getStringExtra("updatedFacultyFirstName");
                        String updatedFacultyLastName = data.getStringExtra("updatedFacultyLastName");
                        Double updatedFacultySalary = data.getDoubleExtra("updatedFacultySalary", -1);
                        Double updatedFacultyRate = data.getDoubleExtra("updatedFacultyRate", -1);
                        faculties[index].setF_id(updatedFacultyNo);
                        faculties[index].setF_fname(updatedFacultyFirstName);
                        faculties[index].setF_lname(updatedFacultyLastName);
                        faculties[index].setF_salary(updatedFacultySalary);
                        faculties[index].setF_bonus(updatedFacultyRate);
                        setValues();
                    }
                }
            });

}