package ca.vanier.billingproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainFragment extends Fragment {

    int index = 0;
    Billing[] bills = new Billing[3];
    EditText id, name, product, price, qty;
    TextView bill;
    Button input, record, prev, next, billingDetailsBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bills[0] = new Billing(105, "Johnston Jane", "Chair", 99.99, 2);
        bills[1] = new Billing(108, "Fikhali Samuel", "Table", 139.99, 1);
        bills[2] = new Billing(113, "Samson Amina", "KeyUSB", 14.99, 2);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        id = (EditText) v.findViewById(R.id.editTextID);
        name = (EditText) v.findViewById(R.id.editTextName);
        product = (EditText) v.findViewById(R.id.editTextProduct);
        price = (EditText) v.findViewById(R.id.editTextPrice);
        qty = (EditText) v.findViewById(R.id.editTextQty);
        bill = (TextView) v.findViewById(R.id.textViewBill);
        input = (Button) v.findViewById(R.id.inputBillButton);
        record = (Button) v.findViewById(R.id.recordBillButton);
        prev = (Button) v.findViewById(R.id.prevButton);
        next = (Button) v.findViewById(R.id.nextButton);
        billingDetailsBtn = (Button) v.findViewById(R.id.billingDetailsBtn);

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

        billingDetailsBtn.setOnClickListener(view -> {
            Billing current = bills[index];
            Intent intent = BillingFragment.newIntent(getActivity(), current.getClient_id(), current.getClient_name(), current.getProduct_Name(), current.getPrd_Price(), current.getPrd_Qty());
            startActivity(intent);
        });

        return v;
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