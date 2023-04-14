package com.example.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder > {
    private List<Note> list = new ArrayList<>();
    private IOnItem listener;

    public NotesAdapter(IOnItem listener) {
        this.listener = listener;
    }

    public void addNote(Note model) {
        list.add(model);
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
    public void delete (int pos){
        list.remove(pos);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
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
        void bind (int position){

            title.setText(list.get(position).getTitle());
            description.setText(list.get(position).getDescription());
            Glide.with(itemView).
                    load(list.get(position).getImage())
                            .transform(new CenterCrop(), new RoundedCorners(25))
                                    .into(imageView);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.delete(getAdapterPosition());

                }
             });

        }


    }
    interface IOnItem{

        void delete(int pos);

        void share(int pos);

        void edit(int pos, Note note);
    }

}
