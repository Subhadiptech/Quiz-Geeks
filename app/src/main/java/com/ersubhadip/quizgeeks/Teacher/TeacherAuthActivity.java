package com.ersubhadip.quizgeeks.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ersubhadip.quizgeeks.R;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherAuthActivity extends AppCompatActivity {
    private EditText emailET,pswdET;
    private FirebaseAuth auth;
    private TextView signUpTV;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_auth);
        btn=findViewById(R.id.idLoginTeacher);
        emailET= findViewById(R.id.idTeacherEmail);
        pswdET= findViewById(R.id.idTeacherPswd);
        auth =FirebaseAuth.getInstance();
        String email= emailET.getText().toString();
        String pswd= pswdET.getText().toString();
        //
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(email,pswd);
                Intent intent1=new Intent(TeacherAuthActivity.this,MainTeacherActivity.class);
                startActivity(intent1);
            }
        });
        signUpTV=findViewById(R.id.idSignUpTV);
        signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to sign up activity
                Intent intent=new Intent(TeacherAuthActivity.this,SignUpteacher.class);
                startActivity(intent);
                finish();
            }
        });
    }
//    protected void changeFragment(Fragment fragment){
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace()
//    }
}