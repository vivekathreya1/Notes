package com.vivek.notes.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


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

    private static boolean isAsc;
    private static boolean mediaOnly;
    private List<Note> noteListCache;


    public ShowNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        noteListCache = new ArrayList<>();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_notes, container, false);
        rootView = binding.getRoot();
        viewModel = ViewModelProviders.of(getActivity()).get(ShowNotesViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        init();
        setClickListeners();
        setAdapter();
        setObservers();
        return rootView;
    }

    private void init(){
        isAsc = false;
        mediaOnly = false;
        viewModel.getDataFromDb(isAsc, "null");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        setObservers();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_but:
                Navigation.findNavController(rootView).navigate(R.id.action_showNotesFragment_to_searchFragment);
                break;
        }
        return true;
    }

    private void setClickListeners() {
        binding.addNotesBut.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showNotesFragment_to_addNote);
        });

        binding.addFab.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showNotesFragment_to_addNote);
        });

        binding.sortTv.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showNotesFragment_to_sortFragment);
        });

        binding.filterTv.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_showNotesFragment_to_filterDialogFragment);
        });

    }

    private void setObservers() {
        viewModel.getAllNotes().observe(this, notes -> {
            binding.progressBar.setVisibility(View.GONE);
            noteList.clear();
            noteListCache.clear();
            noteList.addAll(notes);
                noteListCache.addAll(noteList);
            adapter.notifyDataSetChanged();
        });

        viewModel.getOldestClicked().observe(this, aBoolean -> {
            if(aBoolean){
                Collections.sort(noteList, Comparator.comparingLong(Note::getCreationTime));
                adapter.notifyDataSetChanged();
            }

        });

        viewModel.getNewestClick().observe(this, aBoolean -> {
            if (aBoolean) {
                Collections.sort(noteList, Collections.reverseOrder(Comparator.comparingLong(Note::getCreationTime)));
                adapter.notifyDataSetChanged();
            }
        });
/*
        viewModel.getAllNotesClick().observe(this, aBoolean -> {
            mediaOnly = aBoolean;
            viewModel.getDataFromDb(isAsc, mediaOnly);
            *//*if (aBoolean) {
                Collections.sort(noteList, Comparator.comparingLong(Note::getCreationTime));
                adapter.notifyDataSetChanged();
            }*//*
        });
*/
        viewModel.getMediaOnlyClick().observe(this, aBoolean -> {
            if (aBoolean) {
//                noteListCache.clear();
                List<Note> mediaOnlyNotes = noteList.stream().filter(note -> note.getImagePath() != null).collect(Collectors.toList());
                noteList.clear();
                noteList.addAll(mediaOnlyNotes);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getAllNotesClick().observe(this, aBoolean -> {
           /* if (aBoolean) {
                noteList.clear();
                noteList.addAll(noteListCache);
                adapter.notifyDataSetChanged();
            }*/
        });

    }


    private void setAdapter() {
        noteList = new ArrayList<>();
        adapter = new ShowNotesAdapter(noteList, getActivity());
        binding.noteRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.noteRv.setAdapter(adapter);

    }


}
