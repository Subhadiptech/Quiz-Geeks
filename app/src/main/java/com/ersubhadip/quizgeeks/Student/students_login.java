package com.ersubhadip.quizgeeks.Student;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ersubhadip.quizgeeks.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.PrimitiveIterator;

public class students_login extends Fragment {

    private FrameLayout frameLayout;
    private TextView signup,forgot;
    private EditText email,pass;
    private Button login;
    private FirebaseAuth auth;


    public students_login() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_students_login, container, false);
        //initialisation
        frameLayout=getActivity().findViewById(R.id.students_auth_frame);
        email=view.findViewById(R.id.students_email);
        pass=view.findViewById(R.id.students_password);
        login=view.findViewById(R.id.student_login_button);
        signup=view.findViewById(R.id.students_signup_redirect);
        forgot=view.findViewById(R.id.students_forgotpassword);
        auth=FirebaseAuth.getInstance();
        //end
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments(new student_signup());
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments(new students_forget());
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().trim().equals("")){
                    if(!pass.getText().toString().trim().equals("")){

                        login.setEnabled(false);




                        String emailDataLog = email.getText().toString().trim();
                        String passDataLog = pass.getText().toString().trim();
                        auth.signInWithEmailAndPassword(emailDataLog,passDataLog).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(getContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                    //todo:intent of student quiz activity

                                }else{

                                    login.setEnabled(true);
                                    Toast.makeText(getContext(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }else{
                        pass.setError("Please Enter Password");
                    }
                }else{
                    email.setError("Please Enter Email");
                }
            }
        });





    }

    private void changeFragments(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}