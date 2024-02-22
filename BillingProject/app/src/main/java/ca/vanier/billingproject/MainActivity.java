package ca.vanier.billingproject;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int index = 0;
    Billing[] bills = new Billing[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Button billingDetailsBtn = (Button) findViewById(R.id.billingDetailsBtn); // TODO: add on click event listener

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

        billingDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billingDetailsBtn.setOnClickListener(view -> {
                    Intent intent  = new Intent(MainActivity.this, BillingActivity.class);
                    Billing bill = bills[index];
                    intent.putExtra("intentClientId", bill.getClient_id());
                    intent.putExtra("intentClientName", bill.getClient_name());
                    intent.putExtra("intentProductName", bill.getProduct_Name());
                    intent.putExtra("intentProductPrice", bill.getPrd_Price());
                    intent.putExtra("intentProductQty", bill.getPrd_Qty());
                    billingActivityResultLauncher.launch(intent);
                });
            }
        });
    }

    ActivityResultLauncher<Intent> billingActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Handle the result
                    Intent data = result.getData();
                    if (data != null) {
                        int  updatedClientId = data.getIntExtra("updatedClientId", -1);
                        String updatedClientName = data.getStringExtra("updatedClientName");
                        String updatedProductName = data.getStringExtra("updatedProductName");
                        Double updatedProductPrice = data.getDoubleExtra("updatedProductPrice", -1);
                        int updatedPriceQty = data.getIntExtra("updatedProductQty", -1);
                        bills[index].setClient_id(updatedClientId);
                        bills[index].setClient_name(updatedClientName);
                        bills[index].setProduct_Name(updatedProductName);
                        bills[index].setPrd_Price(updatedProductPrice);
                        bills[index].setPrd_Qty(updatedPriceQty);
                    }
                }
            });
}