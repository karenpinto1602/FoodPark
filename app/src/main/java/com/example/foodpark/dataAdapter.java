package com.example.foodpark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;


public class dataAdapter extends ArrayAdapter<Contact>{

    Context context;

    //Button inc,dec,AddCart;
    Button proceed;
    TextView creditCount;//nf
    int n = 0;
    int totaltemp = 0;
    ArrayList<Contact> mcontact;
    String regno;

    View bfview;
    AdmindbHelper db;

    Holder viewHolder;


    public dataAdapter(Context context, ArrayList<Contact> contact, View view){
        super(context, R.layout.listmenu_activity, contact);
        this.context = context;
        this.mcontact = contact;
        this.bfview = view;

    }

    public  class  Holder{
        TextView idV;
        TextView itemV;
        TextView priceV;

        Button inc,dec,AddCart,proceed;
        TextView nf;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        final Contact data = getItem(position);


        viewHolder = new Holder();
        /*LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listmenu_activity, parent, false);*/
        db = new AdmindbHelper(getContext());

        /*inc = convertView.findViewById(R.id.incButton);
        dec = convertView.findViewById(R.id.decButton);
        AddCart = convertView.findViewById(R.id.AddCartButton);*/
        proceed = bfview.findViewById(R.id.proceedButton);
        /*nf = convertView.findViewById(R.id.NoOfButton);*/
        creditCount = bfview.findViewById(R.id.creditcountButton);


        if (convertView == null){

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listmenu_activity, parent, false);
            viewHolder.idV = convertView.findViewById(R.id.txtView1);
            viewHolder.itemV = convertView.findViewById(R.id.txtView2);
            viewHolder.priceV = convertView.findViewById(R.id.txtView3);
            viewHolder.inc = convertView.findViewById(R.id.incButton);
            viewHolder.dec = convertView.findViewById(R.id.decButton);
            viewHolder.AddCart = convertView.findViewById(R.id.AddCartButton);
            viewHolder.nf = convertView.findViewById(R.id.NoOfButton);

            convertView.setTag(viewHolder);
        } else{
            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.idV.setText("ID: "+data.getId());
        viewHolder.itemV.setText("ITEM: "+data.getITem());
        viewHolder.priceV.setText("PRICE: "+data.getPrice());

        int temp = Integer.parseInt(data.getPrice());

        viewHolder.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n = Integer.parseInt(viewHolder.nf.getText().toString());
                n++;
                viewHolder.nf.setText(""+n);
                if(viewHolder.AddCart.getText().toString().equals("ADDED")){
                    int temp = Integer.parseInt(data.getPrice());
                    totaltemp = totaltemp + temp ;
                }
            }
        });
        viewHolder.dec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                n = Integer.parseInt(viewHolder.nf.getText().toString());
                if(n>0)
                    n--;
                viewHolder.nf.setText(""+n);
                if(viewHolder.AddCart.getText().toString().equals("ADDED")){
                    int temp = Integer.parseInt(data.getPrice());
                    totaltemp = totaltemp - temp ;
                }
            }
        });

        viewHolder.AddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.AddCart.getText().toString().equals("ADD TO CART")) {
                    int temp = Integer.parseInt(data.getPrice());
                    totaltemp = Integer.parseInt(creditCount.getText().toString());
                    totaltemp += n * temp;
                    viewHolder.AddCart.setText("ADDED");
                }

            }
        });
        return convertView;
    }
    public int returntotal(){
        return totaltemp;
    }



}