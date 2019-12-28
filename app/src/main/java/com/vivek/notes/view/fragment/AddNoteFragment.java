package com.vivek.notes.view.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.vivek.notes.R;
import com.vivek.notes.databinding.FragmentAddNoteBinding;
import com.vivek.notes.utils.ImageUtils;
import com.vivek.notes.viewmodel.AddNoteViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment {

    private FragmentAddNoteBinding binding;
    private View rootView;
    private AddNoteViewModel viewModel;
    private boolean titleEmpty = true;
    private boolean descEmpty = true;
    private String imageFilePath;
    private static int CAMERA_PIC_REQUEST = 1000;
    private byte[] imageArray = null;


    public AddNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false);
        rootView = binding.getRoot();
        viewModel = ViewModelProviders.of(this).get(AddNoteViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        setClickListeners();
        setObservers();
        return rootView;
    }

    private void setObservers() {
        viewModel.getTitle().observe(this, s -> {
            if (s.trim().isEmpty()) {
                titleEmpty = true;
                binding.createTv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorGray));
                binding.createTv.setEnabled(false);
            } else {
                titleEmpty = false;
                if (!titleEmpty && !descEmpty) {
                    binding.createTv.setEnabled(true);
                    binding.createTv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                }
            }
        });

        viewModel.getDesc().observe(this, s -> {
            if (s.trim().isEmpty()) {
                binding.createTv.setEnabled(false);
                descEmpty = true;
                binding.createTv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorGray));
            } else {
                descEmpty = false;
                if (!titleEmpty && !descEmpty) {
                    binding.createTv.setEnabled(true);
                    binding.createTv.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                }
            }
        });

        viewModel.getNoteCreated().observe(this, aBoolean -> {
            if(aBoolean){
                Navigation.findNavController(rootView).popBackStack();
            }
        });
    }

    private void setClickListeners() {
        binding.addImage.setOnClickListener(view -> {
            createCameraIntent();
        });
    }

    private void createCameraIntent() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                Pair<File, String> p = ImageUtils.createImageFile(getActivity());
                imageFilePath = p.second;
                photoFile = p.first;
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.vivek.notes.android.fileprovider",
                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == getActivity().RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
            bitmap = ImageUtils.scaleImage(bitmap);
            binding.addImage.setImageBitmap(bitmap);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imageArray = stream.toByteArray();
            File image = new File(imageFilePath);
            viewModel.imageFilePath.setValue(imageFilePath);
        }
    }
}
