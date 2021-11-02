package com.ersubhadip.quizgeeks.Teacher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ersubhadip.quizgeeks.R;

import java.util.ArrayList;

public class TeacherDashRVAdapter extends RecyclerView.Adapter<TeacherDashRVAdapter.ViewHolder> {
    private ArrayList<TeacherDashModel> dashList;

    public TeacherDashRVAdapter(ArrayList<TeacherDashModel> dashList) {
        this.dashList = dashList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_dash_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //final int index = holder.getAdapterPosition();
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return dashList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t1,t2,t3,t4,t5;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.itemSet);
            t2=itemView.findViewById(R.id.itemQuestions);
            t3=itemView.findViewById(R.id.itemTime);
            t4=itemView.findViewById(R.id.itemPos);
            t5=itemView.findViewById(R.id.itemNeg);
        }

        public void setData(int position) {
            String s1,s2,s3,s4,s5;
            s1=dashList.get(position).getSetNumber();  t1.setText(s1);
            s2=dashList.get(position).getTotalQuestions();  t2.setText(s2);
            s3=dashList.get(position).getTime();  t3.setText(s3);
            s4=dashList.get(position).getPosMarks();  t4.setText(s4);
            s5=dashList.get(position).getNegMarks();  t5.setText(s5);

        }
    }
}
