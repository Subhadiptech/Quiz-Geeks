package com.ersubhadip.quizgeeks.Teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.ersubhadip.quizgeeks.R;

public class MainTeacherActivity extends AppCompatActivity {
    private FrameLayout parentFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        parentFrameLayout=findViewById(R.id.idTeacherRootFrameLayout);
        setFragmet(new TecaherDashboardFragment());
    }
    protected void setFragmet(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}