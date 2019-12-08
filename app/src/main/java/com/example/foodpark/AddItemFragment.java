package com.example.foodpark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddItemFragment extends Fragment {

    FloatingActionButton f1,f2,f3;
    EditText item,price;
    Button ok,cancel;

    AdmindbHelper Myinput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.add_items_activity, container, false);

        f1 = view.findViewById(R.id.fab1);
        f2 = view.findViewById(R.id.fab2);
        f3 = view.findViewById(R.id.fab3);

        View v = inflater.inflate(R.layout.adminmenu_dialog_activity,container,false);
        item = v.findViewById(R.id.itemText);
        price = v.findViewById(R.id.priceText);
        ok = v.findViewById(R.id.OkButton);
        cancel = v.findViewById(R.id.CancelButton);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this.getContext());
        alertDialogBuilderUserInput.setView(v);
        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();

        Myinput = new AdmindbHelper(getContext());

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String bfitem = item.getText().toString();
                        int bfprice = Integer.parseInt(price.getText().toString());
                        Boolean result = Myinput.insertMealItems(bfitem,bfprice,"BREAKFAST");
                        if(result == true){
                            Toast.makeText(getContext(), "Data Successfully Inserted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Error While Data Insertion", Toast.LENGTH_LONG).show();
                        }
                        alertDialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String lnitem = item.getText().toString();
                        int lnprice = Integer.parseInt(price.getText().toString());
                        Boolean result = Myinput.insertMealItems(lnitem,lnprice,"LUNCH");
                        if(result == true){
                            Toast.makeText(getContext(), "Data Successfully Inserted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Error While Data Insertion", Toast.LENGTH_LONG).show();
                        }
                        alertDialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String dnitem = item.getText().toString();
                        int dnprice = Integer.parseInt(price.getText().toString());
                        Boolean result = Myinput.insertMealItems(dnitem,dnprice,"DINNER");
                        if(result == true){
                            Toast.makeText(getContext(), "Data Successfully Inserted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Error While Data Insertion", Toast.LENGTH_LONG).show();
                        }
                        alertDialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });

        return view;
    }
}
