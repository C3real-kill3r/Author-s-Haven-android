package com.example.kamran.logingreentheme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamran.logingreentheme.R;
import com.example.kamran.logingreentheme.RetrofitClient;
import com.example.kamran.logingreentheme.model.Disability;
import com.example.kamran.logingreentheme.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private TextView tv_name, tv_address, ntnlity, dob, sex, phn, ident, disab, cause;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_name = view.findViewById(R.id.tv_name);
        ntnlity = view.findViewById(R.id.ntnlity);
        dob = view.findViewById(R.id.dob);
        sex = view.findViewById(R.id.sex);
        phn = view.findViewById(R.id.phn);
        ident = view.findViewById(R.id.ident);
        disab = view.findViewById(R.id.disab);
        cause = view.findViewById(R.id.cause);

        getMyProfile();
    }

    private void getMyProfile() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJCcnlieiIsImVtYWlsIjoiYnJ5YnppQGdtYWlsLmNvbSIsImlhdCI6MTU2NDgwOTE4OSwiZXhwIjoxNTY0ODIzNTg5fQ.NLN90yFPYcD58-nKVQjaNKFQsi0CY3XAREiBZ0rmFLY";
        Call<List<Profile>> call = RetrofitClient
                .getInstance()
                .getApi()
                .myProfile(token);
        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "not successful", Toast.LENGTH_LONG).show();
                }
                List<Profile> profiles = response.body();
                for (Profile profile : profiles){
                    tv_name.setText(profile.getFirstname() + " " + profile.getLastname());
                    ntnlity.setText(profile.getNationality());
                    dob.setText(profile.getDateOfBirth());
                    sex.setText(profile.getSex());
                    phn.setText(profile.getPhone());
                    ident.setText(profile.getNationalId());
                    List<Disability> disabilities = profile.getDisabilities();
                    for(Disability disability : disabilities){
//                        disab.setText(disability.getDisability());
                        cause.setText(disability.getCause());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
