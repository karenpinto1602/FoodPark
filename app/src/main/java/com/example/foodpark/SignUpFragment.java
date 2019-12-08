package com.example.foodpark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SignUpFragment extends Fragment {

    EditText reg,name,paw;
    Button signup,back;
    AdmindbHelper Authdb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.signup_fragment_activity, container, false);

        reg = view.findViewById(R.id.SignUpReg);
        name = view.findViewById(R.id.SignUpName);
        paw = view.findViewById(R.id.SignUpPass);
        signup = view.findViewById(R.id.SignUpButton);
        back = view.findViewById(R.id.SignInButton);

        Authdb = new AdmindbHelper(getContext());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String regString = reg.getText().toString();
                String nameString = name.getText().toString();
                String pawString = paw.getText().toString();
                char test = regString.charAt(0);
                Boolean result1 = Authdb.insertSignUpDetails(regString,pawString);
                Boolean result2 = false;

                if(test == 'T'){
                    result2  = Authdb.insertAdminDetails(regString,nameString);
                }
                else{
                    result2  = Authdb.insertStudentDetails(regString,nameString);
                }

                if(result1 == true & result2 == true){
                    Toast.makeText(getContext(),"Succesfully Signed Up",Toast.LENGTH_SHORT).show();
                    fragmentswitch();
                }
                else{
                    Toast.makeText(getContext(),"Error while Signing Up",Toast.LENGTH_SHORT).show();
                    fragmentswitch();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentswitch();
            }
        });

        return view;
    }
    public void fragmentswitch() {
        AuthFragment newFragment = new AuthFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, newFragment);
        transaction.commit();
    }
}
