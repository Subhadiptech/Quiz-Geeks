package com.ersubhadip.quizgeeks.Teacher;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ersubhadip.quizgeeks.R;
import com.google.firebase.firestore.FirebaseFirestore;


public class TecaherDashboardFragment extends Fragment {
    private FirebaseFirestore firestore;
    private Button idAddDataTeacher;
    public TecaherDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tecaher_dashboard, container, false);
        idAddDataTeacher=view.findViewById(R.id.idAddDataTeacher);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idAddDataTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TeacherAddDataActivity.class);
                startActivity(intent);
            }
        });
        firestore=FirebaseFirestore.getInstance();
        //firestore.collection("Questions").document("set1")

    }
}