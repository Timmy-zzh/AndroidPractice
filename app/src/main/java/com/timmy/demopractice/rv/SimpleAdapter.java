package com.timmy.demopractice.rv;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timmy.demopractice.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private static final String LOG = SimpleAdapter.class.getSimpleName();
    List<String> data = new ArrayList<>();

    public SimpleAdapter() {
        for (int i = 0; i < 1500; i++) {
            data.add("Item:" + i);
        }
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG, "-----------onCreateViewHolder");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple, parent, false);
        return new SimpleViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        Log.d(LOG, "onBindViewHolder position:" + position);
        String item = data.get(position);
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}
