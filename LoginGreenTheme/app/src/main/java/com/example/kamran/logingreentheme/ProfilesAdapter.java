package com.example.kamran.logingreentheme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kamran.logingreentheme.model.Profile;

import java.util.ArrayList;

public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ViewHolder> {

    private ArrayList<Profile> profiles = new ArrayList<>();
    private Context context;

    public ProfilesAdapter(Context context, ArrayList<Profile> profiles){
        this.profiles = profiles;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfilesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profiles_list_item, viewGroup, false);
        return new ProfilesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilesAdapter.ViewHolder viewHolder, int i) {
        viewHolder.nationality.setText(profiles.get(i).getNationality());
        viewHolder.date_of_birth.setText(profiles.get(i).getDateOfBirth());
        viewHolder.location.setText(profiles.get(i).getLocation());
        viewHolder.phone.setText(profiles.get(i).getPhone());

    }


    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView phone, location, date_of_birth, nationality;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            phone = itemView.findViewById(R.id.phone);
            location = itemView.findViewById(R.id.location);
            date_of_birth = itemView.findViewById(R.id.date_of_birth);
            nationality = itemView.findViewById(R.id.nationality);
        }
    }
}
