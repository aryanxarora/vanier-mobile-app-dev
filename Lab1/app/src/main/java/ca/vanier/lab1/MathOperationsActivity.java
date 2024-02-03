package ca.vanier.lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MathOperationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_operations);

        EditText mathInput1 = (EditText) findViewById(R.id.mathInput1);
        EditText mathInput2 = (EditText) findViewById(R.id.mathInput2);
        TextView mathAdd = (TextView) findViewById(R.id.mathAdd);
        TextView mathSub = (TextView) findViewById(R.id.mathSub);
        TextView mathProd = (TextView) findViewById(R.id.mathProd);
        TextView mathDiv = (TextView) findViewById(R.id.mathDiv);
        Button mathCalc = (Button) findViewById(R.id.mathCalc);

        mathCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(mathInput1.getText().toString());
                double num2 = Double.parseDouble(mathInput2.getText().toString());
                mathAdd.setText(String.format("Addition: %s", num1 + num2));
                mathSub.setText(String.format("Subtraction: %s", num1 - num2));
                mathProd.setText(String.format("Product: %s", num1 * num2));
                mathDiv.setText(String.format("Division: %s", num1 / num2));
            }
        });


    }
}
