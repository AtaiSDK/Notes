package com.example.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    Button button, buttonSort;
    EditText editText;
    RecyclerView recyclerView;
    NotesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        button = view.findViewById(R.id.add_btn);
        editText = view.findViewById(R.id.editSearch);
        buttonSort = view.findViewById(R.id.sort_btn);
        recyclerView = view.findViewById(R.id.recycle);
        adapter = new NotesAdapter();
        recyclerView.setAdapter(adapter);


        Bundle bundle = getArguments();
        if (bundle != null) {
            Note note = (Note) bundle.getSerializable("data");
            if (note != null) {
                List<Note> list = new ArrayList<>();
                list.add(note);
                adapter.setList(list);
            }
        }
        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("key", text);
                AddFragment addFragment = new AddFragment();
                addFragment.setArguments(bundle);

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, addFragment)
                        .commit();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment addFragment = new AddFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, addFragment)
                        .commit();
            }
        });
        return view;
    }
}