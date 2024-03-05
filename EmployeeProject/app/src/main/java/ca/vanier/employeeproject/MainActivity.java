package ca.vanier.employeeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView id, name, salary, tax;
    Button prev, next, calc;
    int index = 0;
    int LEN = 3;
    Employee[] employees = new Employee[LEN];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employees[0] = new Employee("1", "Aryan Arora", 50000);
        employees[1] = new Employee("2", "Jolson Cruz", 40000);
        employees[2] = new Employee("3", "Jerry Joy", 30000);

        id = findViewById(R.id.textViewEmpId);
        name = findViewById(R.id.textViewEmpName);
        salary = findViewById(R.id.textViewEmpSalary);
        tax = findViewById(R.id.textViewEmpTax);
        prev = findViewById(R.id.buttonPrev);
        next = findViewById(R.id.buttonNext);
        calc = findViewById(R.id.buttonTax);
        setValues();

        prev.setOnClickListener(view -> {
            index = (index-1)%LEN;
            setValues();
        });

        next.setOnClickListener(view -> {
            index = (index+1)%LEN;
            setValues();
        });

        calc.setOnClickListener(view -> {
            double calculated_tax = employees[index].calculateTotalTax();
//            tax.setText(String.format("Total Tax: %.2f", calculated_tax));
            Toast.makeText(this, "Total tax: " + Double.toString(calculated_tax), Toast.LENGTH_SHORT).show();
        });

    }

    public void setValues(){
        id.setText(employees[index].getEmp_id());
        name.setText(employees[index].getEmp_name());
        salary.setText(String.format("%.2f", employees[index].getEmp_salary()));
    }
}