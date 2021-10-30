package com.ersubhadip.quizgeeks.Student;

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
import android.widget.Toast;

import com.ersubhadip.quizgeeks.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class students_forget extends Fragment {
    private FrameLayout frameLayout;
    private EditText email;
    private Button reset;
    private FirebaseAuth auth;


    public students_forget() {
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
        View view= inflater.inflate(R.layout.fragment_students_forget, container, false);
        //initialisation
        frameLayout=getActivity().findViewById(R.id.students_auth_frame);
        auth=FirebaseAuth.getInstance();
        email=view.findViewById(R.id.students_email);
        reset=view.findViewById(R.id.students_reset_password);

        //end
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String data=email.getText().toString().trim();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!email.getText().toString().trim().equals("")){
                    reset.setEnabled(false);

                    String emailData=email.getText().toString().trim();
                    auth.sendPasswordResetEmail(emailData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(getContext(), "Email successfully sent to your registered mail, Dont't Forget to check spam folder", Toast.LENGTH_LONG).show();


                            }else{
                                Toast.makeText(getContext(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                reset.setEnabled(true);

                            }


                        }
                    });

                }else{

                    Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    reset.setEnabled(true);

                }



            }
        });

    }

}