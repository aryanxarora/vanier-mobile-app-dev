package ca.vanier.billingproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BillingFragment extends Fragment {

    public static Intent newIntent(Context packageContext, int client_id, String client_name,
                                   String prd_name, double prd_price, int prd_qty)
    {
        Intent intent = new Intent(packageContext, BillingActivity.class);
        intent.putExtra("intentClientId", client_id);
        intent.putExtra("intentClientName", client_name);
        intent.putExtra("intentProductName", prd_name);
        intent.putExtra("intentProductPrice", prd_price);
        intent.putExtra("intentProductQty", prd_qty);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_billing, container, false);
    }
}