package ca.vanier.billingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Billing[] bills = new Billing[3];
        bills[0] = new Billing(105, "Johnston Jane", "Chair", 99.99, 2);
        bills[1] = new Billing(108, "Fikhali Samuel", "Table", 139.99, 1);
        bills[2] = new Billing(113, "Samson Amina", "KeyUSB", 14.99, 2);

        EditText id = (EditText) findViewById(R.id.editTextID);
        EditText name = (EditText) findViewById(R.id.editTextName);
        EditText product = (EditText) findViewById(R.id.editTextProduct);
        EditText price = (EditText) findViewById(R.id.editTextPrice);
        EditText qty = (EditText) findViewById(R.id.editTextQty);
        TextView bill = (TextView) findViewById(R.id.textViewBill);
        Button input = (Button) findViewById(R.id.inputBillButton);
        Button record = (Button) findViewById(R.id.recordBillButton);
        Button prev = (Button) findViewById(R.id.prevButton);
        Button next = (Button) findViewById(R.id.nextButton);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Billing temp = new Billing(Integer.parseInt(id.getText().toString()),
                        name.getText().toString(),
                        product.getText().toString(),
                        Double.parseDouble(price.getText().toString()),
                        Integer.parseInt(qty.getText().toString()));
                bill.setText(temp.toString());

            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill.setText(bills[index].toString());
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index-1)%3;
                bill.setText(bills[index].toString());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index+1)%3;
                bill.setText(bills[index].toString());
            }
        });
    }
}