package com.example.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements NotesAdapter.IOnItem {
    Button button, buttonSort, buttonEdit;
    EditText editText;
    RecyclerView recyclerView;
    NotesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        button = view.findViewById(R.id.add_btn);
        buttonEdit = view.findViewById(R.id.button23);
        editText = view.findViewById(R.id.editSearch);
        buttonSort = view.findViewById(R.id.sort_btn);
        recyclerView = view.findViewById(R.id.recycle);
        adapter = new NotesAdapter(this);
        recyclerView.setAdapter(adapter);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        Bundle bundle = getArguments();
        if (bundle != null) {
            Note note = (Note) bundle.getSerializable("data");
            if (note != null) {

                adapter.addNote(note);
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
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void delete(int pos) {
        AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());
        alert.setTitle("Warning");
        alert.setMessage("Are you sure to delete?");
        alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.delete(pos);
            }
        });
        alert.setNegativeButton("Cansel", null);
        alert.show();
    }

    @Override
    public void share(int pos) {

    }

    @Override
    public void edit(int pos, Note note) {
                AddFragment  fragment = new AddFragment();
                Bundle bundleChange = new Bundle();
                bundleChange.putSerializable("key2", note);
                AddFragment addFragment = new AddFragment();
                addFragment.setArguments(bundleChange);

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, addFragment)
                        .commit();


    }
}