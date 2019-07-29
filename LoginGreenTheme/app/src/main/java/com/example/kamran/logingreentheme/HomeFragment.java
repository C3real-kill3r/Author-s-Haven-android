package com.example.kamran.logingreentheme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamran.logingreentheme.model.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    ArrayList<Profile> profiles = new ArrayList<>();
    private ProfilesAdapter profilesAdapter;
    private RecyclerView profiles_recycler_view;
    private TextView textViewResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        profiles_recycler_view = view.findViewById(R.id.profiles_recycler_view);
        profilesAdapter = new ProfilesAdapter(getContext(), profiles);
        profiles_recycler_view.setAdapter(profilesAdapter);
        profiles_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewResult = view.findViewById(R.id.text_view_result);

        getProfile();

    }

    private void getProfile() {
        String token = "";
        Call<List<Profile>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getJson();

        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("code: " + response.code());
                    return;
                }
                profiles = new ArrayList<>(response.body());
                System.out.println(response.body());
                profilesAdapter = new ProfilesAdapter(getContext(), profiles);
                profiles_recycler_view.setAdapter(profilesAdapter);
                DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                profiles_recycler_view.addItemDecoration(itemDecoration);
                Toast.makeText(getActivity(), "success", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }
}
