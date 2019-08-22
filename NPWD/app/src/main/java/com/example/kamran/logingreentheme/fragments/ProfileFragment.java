package com.example.kamran.logingreentheme.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.kamran.logingreentheme.R;
import com.example.kamran.logingreentheme.RetrofitClient;
import com.example.kamran.logingreentheme.activities.MainActivity;
import com.example.kamran.logingreentheme.model.Profile;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private TextView firstname, tv_address, dob, sex, phn, ident, location;
    private EditText edtdateob, editsex, editphone, editidentity, editlocation;
    private ImageView edticon, saveicon;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        swipeRefreshLayout = view.findViewById(R.id.refreshProfile);
        swipeRefreshLayout.setOnRefreshListener(this);
        getActivity().setTitle("Profile");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // text views
        firstname = view.findViewById(R.id.firstname);
        tv_address = view.findViewById(R.id.tv_address);
        dob = view.findViewById(R.id.dob);
        sex = view.findViewById(R.id.sex);
        phn = view.findViewById(R.id.phn);
        location = view.findViewById(R.id.location);
        ident = view.findViewById(R.id.ident);
        edticon = view.findViewById(R.id.edtprofile);
        saveicon = view.findViewById(R.id.savprofile);

        // edit text
        edtdateob = view.findViewById(R.id.edtdateob);
        editsex = view.findViewById(R.id.editsex);
        editphone = view.findViewById(R.id.editphone);
        editidentity = view.findViewById(R.id.editidentity);
        editlocation = view.findViewById(R.id.editlocation);

        edticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewSwitcher switcher = view.findViewById(R.id.my_switcher);
                ViewSwitcher location = view.findViewById(R.id.location_view_switcher);
                ViewSwitcher dob = view.findViewById(R.id.edtdob);
                ViewSwitcher sex = view.findViewById(R.id.edtsex);
                ViewSwitcher phone = view.findViewById(R.id.edtphn);
                ViewSwitcher id = view.findViewById(R.id.edtident);
                id.showNext();
                dob.showNext();
                switcher.showNext();
                sex.showNext();
                phone.showNext();
                location.showNext();
                edticon.setVisibility(View.INVISIBLE);
                saveicon.setVisibility(View.VISIBLE);
            }
        });

        saveicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewSwitcher switcher = view.findViewById(R.id.my_switcher);
                ViewSwitcher location = view.findViewById(R.id.location_view_switcher);
                ViewSwitcher dob = view.findViewById(R.id.edtdob);
                ViewSwitcher sex = view.findViewById(R.id.edtsex);
                ViewSwitcher id = view.findViewById(R.id.edtident);
                ViewSwitcher phone = view.findViewById(R.id.edtphn);
                id.showPrevious();
                dob.showPrevious();
                switcher.showPrevious();
                sex.showPrevious();
                switcher.showPrevious();
                phone.showPrevious();
                location.showPrevious();
                edticon.setVisibility(View.VISIBLE);
                saveicon.setVisibility(View.INVISIBLE);

                editProfile();
                getMyProfile();
            }
        });

        getMyProfile();
    }

    private void editProfile() {
        String dob = edtdateob.getText().toString().toUpperCase().trim();
        String sex = editsex.getText().toString().toUpperCase().trim();
        String phone = editphone.getText().toString().toUpperCase().trim();
        String blood_type = editidentity.getText().toString().toUpperCase().trim();
        String location = editlocation.getText().toString().toUpperCase().trim();

        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("blood_type", blood_type);
        map.put("date_of_birth", dob);
        map.put("location", location);
        map.put("sex", sex);

        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken = preferences.getString("TOKEN", null);
        String token = retrivedToken;
        Call<Profile> call = RetrofitClient
                .getInstance()
                .getApi()
                .editmyProfile(token, map);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "No changes made", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(getActivity(), "success", Toast.LENGTH_LONG).show();
                onRefresh();
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getActivity(), "failed" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getMyProfile() {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken = preferences.getString("TOKEN", null);//second parameter default value
        String token = retrivedToken;
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
                for (Profile profile : profiles) {
                    firstname.setText(profile.getUsername());
                    tv_address.setText(profile.getEmail());
                    location.setText(profile.getLocation());
                    dob.setText(profile.getDateOfBirth());
                    sex.setText(profile.getSex());
                    phn.setText(profile.getPhone());
                    ident.setText(profile.getBloodType());
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getMyProfile();
    }
}
