package com.vivek.notes.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vivek.notes.R;
import com.vivek.notes.databinding.FragmentSearchBinding;
import com.vivek.notes.model.Note;
import com.vivek.notes.view.adapter.ShowNotesAdapter;
import com.vivek.notes.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private View rootView;
    private SearchViewModel viewModel;
    private List<Note> noteList;
    private ShowNotesAdapter adapter;
    private String TAG = SearchFragment.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        rootView = binding.getRoot();
        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        viewModel.searchNotes("");
        setAdapter();
        setListener();
        setObservers();
        return rootView;
    }

    private void setListener() {

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.trim().isEmpty()) {
                    viewModel.searchNotes(query);
//                    binding.loadingProgressBar.setVisibility(View.VISIBLE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });
    }

    private void setObservers() {
        viewModel.getSearchedNotes().observe(this, notes -> {
            if (notes!= null && notes.size() != 0) {
                binding.noNoteTv.setVisibility(View.GONE);
                binding.searchResultsRv.setVisibility(View.VISIBLE);
                noteList.clear();
                noteList.addAll(notes);
                adapter.notifyDataSetChanged();

            }else{
                binding.noNoteTv.setVisibility(View.VISIBLE);
                binding.searchResultsRv.setVisibility(View.GONE);
            }
        });
    }

    private void setAdapter() {
        noteList = new ArrayList<>();
        adapter = new ShowNotesAdapter(noteList, getActivity());
        binding.searchResultsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.searchResultsRv.setAdapter(adapter);

    }

}
