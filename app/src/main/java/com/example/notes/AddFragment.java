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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class AddFragment extends Fragment {
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    TextView textView;
    TextView textView1;
    TextView textView2 ;
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

        textView = view.findViewById(R.id.editTitle);
        textView1 = view.findViewById(R.id.editDescription);
        textView2 = view.findViewById(R.id.editDate);
        button = view.findViewById(R.id.btnAdd);

        Bundle bundleChange = getArguments();
        if (bundleChange != null) {
            Note note = (Note) bundleChange.getSerializable("key2");
            if (textView != null && textView1 != null) {
                textView.setText(note.getTitle());
                textView1.setText(note.getDescription());
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = textView.getText().toString();
                String desc = textView1.getText().toString();
                String date = textView2.getText().toString();
                Note note = new Note(title, desc, "", date);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("data", note);

                MainFragment mainFragment = new MainFragment();
                mainFragment.setArguments(bundle1);

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mainFragment)
                        .commit();
            }
        });
        imageView = view.findViewById( R.id.image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }


        });
        if (getArguments() != null) {
            Bundle bundle = getArguments();

            String text = bundle.getString("key");
            textView.setText(text);
        }
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