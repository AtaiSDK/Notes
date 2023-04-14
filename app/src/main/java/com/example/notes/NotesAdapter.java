package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder > {
    private List<Note> list = new ArrayList<>();

    public void setList(List<Note> list){

        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public Note getItem(int pos){
        return list.get(pos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.title.setText(list.get(position).getTitle());
    holder.description.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title, description, date;
        private Button change, delete, send;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            date = itemView.findViewById(R.id.textDate);
            change = itemView.findViewById(R.id.button23);
            delete = itemView.findViewById(R.id.button3);
            send = itemView.findViewById(R.id.button1);
        }
        public List<Note> getList() {
            return list;
    }
}}
