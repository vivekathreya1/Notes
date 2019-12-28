package com.vivek.notes.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.vivek.notes.R;
import com.vivek.notes.databinding.SortFragmentBinding;
import com.vivek.notes.viewmodel.ShowNotesViewModel;

public class SortDialogFragment extends BottomSheetDialogFragment {

    private SortFragmentBinding binding;
    private View rootView;

    private ShowNotesViewModel showNotesViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.sort_fragment, container,false);
        showNotesViewModel = ViewModelProviders.of(this).get(ShowNotesViewModel.class);
        binding.setViewModel(showNotesViewModel);
        rootView = binding.getRoot();
        setClickListeners();
        return rootView;
    }

    private void setClickListeners(){
       /* binding.oldestTv.setOnClickListener(view -> {
            dismiss();
        });*/
    }
}
