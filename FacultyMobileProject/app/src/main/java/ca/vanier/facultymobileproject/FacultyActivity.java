package ca.vanier.facultymobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FacultyActivity extends AppCompatActivity {

    EditText id, fName, lName, salary, rate;
    TextView bonus;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        id = findViewById(R.id.editTextFacultyId);
        fName = findViewById(R.id.editTextFacultyFirstName);
        lName = findViewById(R.id.editTextFacultyLastName);
        salary = findViewById(R.id.editTextFacultySalary);
        rate = findViewById(R.id.editTextFacultyRate);
        bonus = findViewById(R.id.textViewBonus);
        update = findViewById(R.id.buttonUpdate);
        setValues();

        update.setOnClickListener(view -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedFacultyNo", Integer.parseInt(id.getText().toString()));
            resultIntent.putExtra("updatedFacultyFirstName", fName.getText().toString());
            resultIntent.putExtra("updatedFacultyLastName", lName.getText().toString());
            resultIntent.putExtra("updatedFacultySalary", Double.parseDouble(salary.getText().toString()));
            resultIntent.putExtra("updatedFacultyRate", Double.parseDouble(rate.getText().toString()));
            setValues();
            setResult(Activity.RESULT_OK, resultIntent);
        });
    }

    public void setValues(){
        id.setText(Integer.toString(getIntent().getIntExtra("intentFacultyNo", -1)));
        fName.setText(getIntent().getStringExtra("intentFacultyFirstName"));;
        lName.setText(getIntent().getStringExtra("intentFacultyLastName"));
        salary.setText(Double.toString(getIntent().getDoubleExtra("intentFacultySalary", -1)));
        rate.setText(Double.toString(getIntent().getDoubleExtra("intentFacultyRate", -1)));
        bonus.setText(String.format("Amount Bonus: %.2f", Double.parseDouble(salary.getText().toString()) *
                Double.parseDouble(rate.getText().toString()) / 100));
    }

}