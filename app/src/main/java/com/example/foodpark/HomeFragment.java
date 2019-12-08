package com.example.foodpark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    String reg,name;
    int balance;
    TextView tname,tcredits;
    Button bf,ln,dn,add;
    AdmindbHelper db;

    BreakfastFragment fragmentbf;
    LunchFragment fragmentln;
    DinnerFragment fragmentdn;
    AddItemFragment fragmentAdd;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    HomeFragment(int cutBalance){

    }
    HomeFragment(String r){
        this.reg = r;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.home_fragment_activity,container,false);

        db = new AdmindbHelper(getContext());

        tname = view.findViewById(R.id.displayname);
        tcredits = view.findViewById(R.id.balanceCredits);
        bf = view.findViewById(R.id.breakfastButton);
        ln = view.findViewById(R.id.lunchButton);
        dn = view.findViewById(R.id.dinnerButton);
        add = view.findViewById(R.id.GoToAdminButton);

        if(reg.charAt(0) == 'T'){
            name = db.getAdminName(reg);
            tname.setText("" + name);
            tcredits.setText("Admin");
        }
        else {
            name = db.getstudentName(reg);
            balance = db.getstudentCredits(reg);
            showBalance();
        }

        fragmentbf = new BreakfastFragment(reg);
        fragmentln = new LunchFragment(reg);
        fragmentdn = new DinnerFragment(reg);
        fragmentAdd = new AddItemFragment();

        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.main_container, fragmentbf).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.main_container, fragmentln).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.main_container, fragmentdn).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.main_container, fragmentAdd).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;

    }

    public void showBalance()
    {
        tname.setText("NAME: " + name);
        tcredits.setText("BALANCE CREDITS: " + balance);
    }

}
