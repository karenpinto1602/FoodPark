package com.example.foodpark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

public class AuthFragment extends Fragment {

    EditText regn, passwd;
    Button signin, signup;

    AdmindbHelper MyData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.auth_fragment_activity, container, false);

        MyData = new AdmindbHelper(getContext());

        regn = view.findViewById(R.id.regInput);
        passwd = view.findViewById(R.id.passInput);
        signin = view.findViewById(R.id.SignInButton);
        signup = view.findViewById(R.id.SignUpButton);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r = regn.getText().toString();
                String p = passwd.getText().toString();

                boolean result = MyData.testSignIn(r, p);
                if (result) {
                    HomeFragment newFragment = new HomeFragment(r);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.main_container, newFragment);
                    transaction.commit();
                } else {
                    Toast.makeText(getContext(), "Inavlid Credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentswitchSignUp();
            }
        });


        return view;
    }

    public void fragmentswitchSignUp() {
        SignUpFragment newFragment = new SignUpFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, newFragment);
        transaction.commit();
    }

}
