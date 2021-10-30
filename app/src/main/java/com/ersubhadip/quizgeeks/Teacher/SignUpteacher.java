package com.ersubhadip.quizgeeks.Teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ersubhadip.quizgeeks.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpteacher extends AppCompatActivity {
    private EditText emailET,pswdET;
    private FirebaseAuth auth;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upteacher);

        emailET= findViewById(R.id.idEmail);
        pswdET= findViewById(R.id.idPswd);
        auth=FirebaseAuth.getInstance();
        btn=findViewById(R.id.signUPteacher);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(emailET.getText().toString(),pswdET.getText().toString());
                Toast.makeText(SignUpteacher.this, "User Created", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(SignUpteacher.this,MainTeacherActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}