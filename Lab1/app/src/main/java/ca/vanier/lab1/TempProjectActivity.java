package ca.vanier.lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TempProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_project);

        EditText tempInput = (EditText) findViewById(R.id.tempInput);
        TextView tempOutput = (TextView) findViewById(R.id.tempOutput);
        Button tempBtn = (Button) findViewById(R.id.tempBtn);

        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double far = Double.parseDouble(tempInput.getText().toString());
                far = (far - 32) * 5.0/9.0;
                tempOutput.setText(String.format("Temp in Celcius is: %.2f", far));
            }
        });

    }

}
