package com.example.noteappwithsql.recyclerviewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteappwithsql.R;
import com.example.noteappwithsql.activities.UpdateActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

     private Context context;
     private ArrayList title, note;
     int position;
    public CustomAdapter(Context context, ArrayList title, ArrayList note){
     this. context= context;
     this.title=title;
     this.note=note;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
       this.position=position;
        holder.title.setText(String.valueOf(title.get(position)));
        holder.note.setText(String.valueOf(note.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("title", String.valueOf(title.get(position)));
                intent.putExtra("note", String.valueOf(note.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,note;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            note=itemView.findViewById(R.id.note);
            mainLayout=itemView.findViewById(R.id.mainLayout);



        }
    }
}
