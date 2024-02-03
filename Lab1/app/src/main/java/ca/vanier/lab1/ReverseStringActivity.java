package ca.vanier.lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ReverseStringActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_string);

        EditText getString = (EditText) findViewById(R.id.reverseInput);
        TextView putString = (TextView) findViewById(R.id.reverseOutput);
        Button reverseBtn = (Button) findViewById(R.id.reverseBtn);

        reverseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putString.setText(new StringBuilder(getString.getText()).reverse());
            }
        });

    }

}
