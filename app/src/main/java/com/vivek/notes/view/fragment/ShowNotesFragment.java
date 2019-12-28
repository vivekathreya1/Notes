package com.vivek.notes.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vivek.notes.R;
import com.vivek.notes.databinding.FragmentShowNotesBinding;
import com.vivek.notes.model.Note;
import com.vivek.notes.view.adapter.ShowNotesAdapter;
import com.vivek.notes.viewmodel.ShowNotesViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowNotesFragment extends Fragment {

    private FragmentShowNotesBinding binding;
    private ShowNotesViewModel viewModel;
    private View rootView;
    private NavController navController;
    private ShowNotesAdapter adapter;
    private List<Note> noteList;



    public ShowNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_show_notes, container, false );
        rootView = binding.getRoot();
        viewModel = ViewModelProviders.of(this).get(ShowNotesViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        setClickListeners();
        setAdapter();
        setObservers();
        return rootView;
    }

    private void setClickListeners(){
        binding.addNotesBut.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showNotesFragment_to_addNote);
        });

        binding.addFab.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showNotesFragment_to_addNote);
        });

        binding.sortTv.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showNotesFragment_to_sortFragment);
        });

    }

    private void setObservers(){
        viewModel.getAllNotes().observe(this, notes -> {
            noteList.clear();
            noteList.addAll(notes);
            adapter.notifyDataSetChanged();
        });

    }


    private void setAdapter(){
        noteList = new ArrayList<>();
        adapter = new ShowNotesAdapter(noteList, getActivity());
        binding.noteRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.noteRv.setAdapter(adapter);

    }

}
