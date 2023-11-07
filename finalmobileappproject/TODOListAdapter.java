package com.example.finalmobileappproject;

import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TODOListAdapter extends RecyclerView.Adapter<TODOListViewHolder> {

    List<String> items;

    public TODOListAdapter(List<String> userItems) {
        this.items = userItems;
    }

    @NonNull
    @Override
    public TODOListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_item_view, parent, false);
        TODOListViewHolder vh = new TODOListViewHolder(view);

        return vh.linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull TODOListViewHolder holder, int position) {
        holder.userText.setText(items.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

// creating my view holder here
class TODOListViewHolder extends RecyclerView.ViewHolder
{
    public EditText userText;

    CheckBox checkBox;

    private TODOListAdapter adapter;

    public TODOListViewHolder(@NonNull View itemView) {
        super(itemView);

        userText = itemView.findViewById(R.id.userInputText);
        userText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.items.set(getAdapterPosition(), charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        checkBox = itemView.findViewById(R.id.checkboxView);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.items.remove(getAdapterPosition());
                adapter.notifyItemRemoved(getAdapterPosition());
                checkBox.setChecked(false);
            }
        });
    }

    public TODOListViewHolder linkAdapter(TODOListAdapter adapter)
    {
        this.adapter = adapter;
        return this;
    }
}