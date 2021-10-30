package com.ersubhadip.quizgeeks.Student;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class student_signup extends Fragment {
    private FrameLayout frameLayout;
    private Button signUp;
    private TextView login;
    private EditText name,email,pass,cpass;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private FirebaseAuth auth;
    private FirebaseFirestore store;



    public student_signup() {
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
        View view= inflater.inflate(R.layout.fragment_student_signup, container, false);
        //initialisation
        frameLayout=getActivity().findViewById(R.id.students_auth_frame);
        login=view.findViewById(R.id.student_login_redirect_text);
        signUp=view.findViewById(R.id.students_signup_button);
        name=view.findViewById(R.id.students_name);
        email=view.findViewById(R.id.students_email);
        pass=view.findViewById(R.id.students_password);
        cpass=view.findViewById(R.id.students_cpassword);
        auth=FirebaseAuth.getInstance();
        store=FirebaseFirestore.getInstance();
        //end
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments(new students_login());
            }
        });

        //TextWatchers

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkValidInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkValidInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkValidInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
       cpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkValidInputs();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(email.getText().toString().trim().matches(emailPattern)){
                   if(cpass.getText().toString().trim().equals(pass.getText().toString().trim())){


                       String e=email.getText().toString();
                       String p=pass.getText().toString();
                       auth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {

                               if(task.isSuccessful()){

                                   auth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                       @Override
                                       public void onComplete(@NonNull Task<AuthResult> task) {

                                           if(task.isSuccessful()){

                                               Map<String,Object> credStudents=new HashMap<>();
                                               credStudents.put("name",name.getText().toString().trim());
                                               credStudents.put("email",e);
                                               store.collection("students").document(auth.getUid()).set(credStudents).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<Void> task) {

                                                       if(task.isSuccessful()){

                                                           //todo:intent


                                                       }else{
                                                           signUp.setEnabled(true);
                                                           Toast.makeText(getContext(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                                                       }

                                                   }
                                               });



                                           }else{
                                               signUp.setEnabled(true);
                                               Toast.makeText(getContext(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                           }
                                       }
                                   });


                               }else{
                                   signUp.setEnabled(true);
                                   Toast.makeText(getContext(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                               }

                           }
                       });



                   }else{


                       email.setError("Please Enter Valid Email");
                       signUp.setEnabled(true);


                   }




               }else{


                   cpass.setError("Please Enter Same Password");
                   signUp.setEnabled(true);

               }


           }
       });


    }
    private void changeFragments(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void checkValidInputs(){

        if(!name.getText().toString().trim().equals("")){

            if(!email.getText().toString().trim().equals("")){

                if(!pass.getText().toString().trim().equals("")){

                    if (!cpass.getText().toString().trim().equals("")){

                        if(pass.getText().toString().trim().length()>=6){

                            signUp.setEnabled(true);

                        }else{
                            pass.setError("Please Enter Valid Password!");
                            signUp.setEnabled(false);
                        }
                    }else{
                        cpass.setError("Please Re-enter Password!");
                        signUp.setEnabled(false);
                    }
                }else{
                    pass.setError("Please Enter Password!");
                    signUp.setEnabled(false);
                }
            }else{
                email.setError("Please Enter Email!");
                signUp.setEnabled(false);
            }
        }else{
            name.setError("Please Enter Name!");
            signUp.setEnabled(false);
        }

    }
}