package com.example.foodpark;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class BreakfastFragment extends Fragment {

    AdmindbHelper db;
    MenuSelectFragment mn;

    ListView lv;
    dataAdapter data;
    Contact dataModel;
    View view;

    String reg;
    int totalspent;

    BreakfastFragment(String reg){
        this.reg = reg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
         view = inflater.inflate(R.layout.breakfast_fragment_activity, container, false);

        db = new AdmindbHelper(this.getContext());

        lv = view.findViewById(R.id.list1);

        ShowRecords();
        totalspent = data.returntotal();

        db.updateCredits(reg,totalspent);


        return view;
    }

    public void ShowRecords(){

        final ArrayList<Contact> contacts = new ArrayList<>(db.getMealsItems("BREAKFAST"));

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
