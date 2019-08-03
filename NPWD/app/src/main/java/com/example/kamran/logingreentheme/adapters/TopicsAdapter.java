package com.example.kamran.logingreentheme.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kamran.logingreentheme.R;
import com.example.kamran.logingreentheme.model.Topic;

import java.util.ArrayList;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    private ArrayList<Topic> topics = new ArrayList<Topic>();
    private Context context;

    public TopicsAdapter(Context context, ArrayList<Topic> profiles) {
        this.topics = profiles;
        this.context = context;
    }

    @NonNull
    @Override
    public TopicsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topics_list_item, viewGroup, false);
        return new TopicsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(topics.get(i).getTitle());
        viewHolder.description.setText(topics.get(i).getDescription());

    }


    @Override
    public int getItemCount() {
        return topics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.edttitle);
            description = itemView.findViewById(R.id.editdescription);
        }
    }
}
