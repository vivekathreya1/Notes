package com.vivek.notes.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.vivek.notes.R;
import com.vivek.notes.databinding.NoteRowBinding;
import com.vivek.notes.model.Note;

import java.util.List;

public class ShowNotesAdapter extends RecyclerView.Adapter<ShowNotesAdapter.ShowNotesRecyclerViewHolder> {

    private List<Note> notesList;
    private Context context;


    public ShowNotesAdapter(List<Note> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowNotesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.note_row, parent, false);
        return new ShowNotesRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowNotesRecyclerViewHolder holder, int position) {
        holder.bind(notesList.get(position));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ShowNotesRecyclerViewHolder extends RecyclerView.ViewHolder {
        private NoteRowBinding binding;

        public ShowNotesRecyclerViewHolder(NoteRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setViewHolder(this);
        }

        public void bind(Object obj) {
            binding.setVariable(BR.obj, obj);
            binding.executePendingBindings();
        }
    }
}
