package com.example.foodpark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class LunchFragment extends Fragment {
    AdmindbHelper db;

    ListView lv;
    dataAdapter data;
    Contact dataModel;
    View view;

    String reg;
    int totalspent;

    LunchFragment(String reg){
        this.reg = reg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.lunch_fragment_activity, container, false);

        db = new AdmindbHelper(this.getContext());
        lv = view.findViewById(R.id.list1);

        ShowRecords();

        return view;
    }

    public void ShowRecords(){

        final ArrayList<Contact> contacts = new ArrayList<>(db.getMealsItems("LUNCH"));

        data = new dataAdapter(getContext(), contacts, view);

        lv.setAdapter(data);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataModel = contacts.get(position);
                Toast.makeText(getContext(),String.valueOf(dataModel.getId()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
