package ca.vanier.billingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class BillingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        EditText clientId = (EditText) findViewById(R.id.editTextClientId);
        EditText clientName = (EditText) findViewById(R.id.editTextClientName);
        EditText productName = (EditText) findViewById(R.id.editTextProductName);
        EditText productPrice = (EditText) findViewById(R.id.editTextProductPrice);
        EditText productQty = (EditText) findViewById(R.id.editTextProductQty);
        Button updateBtn = (Button) findViewById(R.id.buttonUpdate);

        clientId.setText(Integer.toString(getIntent().getIntExtra("intentClientId", -1)));
        clientName.setText(getIntent().getStringExtra("intentClientName"));
        productName.setText(getIntent().getStringExtra("intentProductName"));
        productPrice.setText(Double.toString(getIntent().getDoubleExtra("intentProductPrice", -1)));
        productQty.setText(Integer.toString(getIntent().getIntExtra("intentProductQty", -1)));

        updateBtn.setOnClickListener(view -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedClientId", Integer.parseInt(clientId.getText().toString()));
            resultIntent.putExtra("updatedClientName", clientName.getText().toString());
            resultIntent.putExtra("updatedProductName", productName.getText().toString());
            resultIntent.putExtra("updatedProductPrice", Double.parseDouble(productPrice.getText().toString()));
            resultIntent.putExtra("updatedProductQty", Integer.parseInt(productQty.getText().toString()));
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

    }
}