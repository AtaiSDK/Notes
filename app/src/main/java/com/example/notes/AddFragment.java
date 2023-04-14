package com.example.notes;

import static android.app.Activity.RESULT_OK;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class AddFragment extends Fragment {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    EditText editTitle;
    EditText editDesc;
    EditText editDate;
    ImageView imageView;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTitle = view.findViewById(R.id.editTitle);
        editDesc = view.findViewById(R.id.editDescription);
        editDate = view.findViewById(R.id.editDate);
        button = view.findViewById(R.id.btnAdd);


        if (getArguments() != null) {
            button.setText("Edit");
            Note note = (Note) getArguments()
                    .getSerializable("key2");

            int position = getArguments().getInt("position");

            editTitle.setText(note.getTitle());
            editDesc.setText(note.getDescription());
            button.setOnClickListener(v -> {
                String titleEdit = editTitle.getText().toString();
                String titleDes = editDesc.getText().toString();
                String titleDate = editDate.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putSerializable("editNote", new Note(titleEdit, titleDes, "", titleDate));
                requireActivity().getSupportFragmentManager().setFragmentResult("editData", bundle);
                requireActivity().getSupportFragmentManager().popBackStack();
                Toast.makeText(requireActivity(), "Кнопка нажата", Toast.LENGTH_SHORT).show();
            });

        } else {
            button.setOnClickListener(v -> {
                String title = editTitle.getText().toString();
                String desc = editDesc.getText().toString();
                String date = editDate.getText().toString();
                Note note = new Note(title, desc, "", date);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("model", note);

                requireActivity().getSupportFragmentManager().setFragmentResult("data", bundle1);
                requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            });
        }

        imageView = view.findViewById(R.id.image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
      /*  if (getArguments() != null) {
            Bundle bundle = getArguments();

            String text = bundle.getString("key");
            editTitle.setText(text);
        }*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_PICK_IMAGE && data != null) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}