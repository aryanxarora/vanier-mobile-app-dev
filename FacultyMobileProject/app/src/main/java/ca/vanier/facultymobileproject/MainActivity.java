package ca.vanier.facultymobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int index = 0;
    Faculty[] faculties = new Faculty[5];
    TextView num, name, salary, rate, bonus;
    Button calc, prev, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = (TextView) findViewById(R.id.faculty_num);
        name = (TextView) findViewById(R.id.textViewName);
        salary = (TextView) findViewById(R.id.textViewSalary);
        rate = (TextView) findViewById(R.id.textViewRate);
        bonus = (TextView) findViewById(R.id.textViewAmount);
        calc = (Button) findViewById(R.id.buttonCalc);
        prev = (Button) findViewById(R.id.prevbutton);
        next = (Button) findViewById(R.id.nextbutton);

        faculties[0] = new Faculty(101,"Robertson", "Myra",60000.00,2.50);
        faculties[1] = new Faculty(212,"Smith","Neal",40000.00,3.00);
        faculties[2] = new Faculty(315,"Arlec", "Lisa", 55000.00, 1.50);
        faculties[3] = new Faculty(857,"Fillipo", "Paul", 30000.00, 5.00);
        faculties[4] = new Faculty(370,"Denkan", "Anais", 95000.00, 1.50);

        setValues();
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bonus.setText(String.format("Amount Bonus: %.2f", faculties[index].calcBonus()));
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index-1)%5;
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
    }

    public void setValues(){
        num.setText(String.format("Faculty no: %d", faculties[index].getF_id()));
        name.setText(String.format("Name: %s %s", faculties[index].getF_fname(), faculties[index].getF_lname()));
        salary.setText(String.format("Salary: %.2f", faculties[index].getF_salary()));
        rate.setText(String.format("Bonus Rate: %.2f", faculties[index].getF_bonus()));
    }
}