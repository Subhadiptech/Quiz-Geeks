package com.ersubhadip.quizgeeks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ersubhadip.quizgeeks.Student.StudentsAuthActivity;
import com.ersubhadip.quizgeeks.Teacher.TeacherAuthActivity;

public class MainActivity extends AppCompatActivity {
    private Button s,t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s=findViewById(R.id.student);
        t=findViewById(R.id.teacher);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this, StudentsAuthActivity.class);
                startActivity(i1);


            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this, TeacherAuthActivity.class);
                startActivity(i2);

            }
        });



    }


}