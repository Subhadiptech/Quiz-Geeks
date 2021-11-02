/*
In this Fragment we will show all sets that are available to the teacher
Along with time, marking scheme, total questions.
 */
package com.ersubhadip.quizgeeks.Teacher;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ersubhadip.quizgeeks.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class TecaherDashboardFragment extends Fragment {
    private FirebaseFirestore firestore;
    private Button idAddDataTeacher;
    private RecyclerView teacherDashRV;
    private TeacherDashRVAdapter adapter;
    public TecaherDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tecaher_dashboard, container, false);
        idAddDataTeacher=view.findViewById(R.id.idAddDataTeacher);
        teacherDashRV=view.findViewById(R.id.teacherDashRV);
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
        /*                     ---------------------Doubts---------------
        When to use onComplete and when to use OnSuccess?
        When to use object and when to use lists?
         */
        //firestore.collection("Questions").document("set1") --> better rahega ki loop laga kar karein. loop ka size hume pata hoga pehle se
        firestore.collection("Questions").document("totalSets").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot!=null && documentSnapshot.exists()){
                        Log.d("####","Okay Good");
                        //pata karo kitne sets hain total phir utne ki ArrayList banao
                        long listSize = documentSnapshot.getLong("count");
                        Log.d("####",""+listSize);// #####  yahan tak sab sahi chal raha hai
                        ArrayList<TeacherDashModel> dashList = new ArrayList<>();
                        for(int i=1 ; i <= listSize ; i++){
                            firestore.collection("Questions").document("set"+i).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task1) {
                                    DocumentSnapshot snapshot = task1.getResult();
                                    if(task1.isSuccessful() && snapshot!=null && snapshot.exists()) {
                                        String para1 = snapshot.getString("setName");
                                        String para2 = snapshot.getString("totalQuestions");
                                        String para3 = snapshot.getString("totalTime");
                                        String para4 = snapshot.getString("posMarks");
                                        String para5 = snapshot.getString("negMarks");
                                        dashList.add(new TeacherDashModel("",para2,para3,para4,para5) );
                                        //now set data in RV
                                        adapter=new TeacherDashRVAdapter(dashList);
                                        teacherDashRV.setAdapter(adapter);
                                        LinearLayoutManager manager =new LinearLayoutManager(getContext());
                                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                                        teacherDashRV.setLayoutManager(manager);
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            });

                        }
                    }
                    else{
                        Log.d("####","documentSnapshot does not exists or is null");
                    }
                }
                else
                    Log.d("####","task failed");
            }
        });
        //long listSize = firestore.collection("Questions").document("totalSets").get()



    }
}