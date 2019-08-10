package com.example.kamran.logingreentheme.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class ProfileFragment extends Fragment {
    private TextView firstname, tv_address,
            ntnlity, dob, sex, phn, ident, disab, cause, location, secondname;
    private EditText edtnationality,
            edtdateob, editsex, editphone, editidentity,
            editdisability, editcause, editlocation, editsecondname,
            editemail, editfirstname;
    private ImageView edticon, saveicon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // text views
        firstname = view.findViewById(R.id.firstname);
        secondname = view.findViewById(R.id.secondname);
        tv_address = view.findViewById(R.id.tv_address);
        ntnlity = view.findViewById(R.id.ntnlity);
        dob = view.findViewById(R.id.dob);
        sex = view.findViewById(R.id.sex);
        phn = view.findViewById(R.id.phn);
        location = view.findViewById(R.id.location);
        ident = view.findViewById(R.id.ident);
        disab = view.findViewById(R.id.disab);
        cause = view.findViewById(R.id.cause);
        edticon = view.findViewById(R.id.edtprofile);
        saveicon = view.findViewById(R.id.savprofile);

        // edit text
        edtnationality = view.findViewById(R.id.edtnationality);
        editfirstname = view.findViewById(R.id.editfirstname);
        editsecondname = view.findViewById(R.id.editsecondname);
        edtdateob = view.findViewById(R.id.edtdateob);
        editsex = view.findViewById(R.id.editsex);
        editphone = view.findViewById(R.id.editphone);
        editidentity = view.findViewById(R.id.editidentity);
        editdisability = view.findViewById(R.id.editdisability);
        editcause = view.findViewById(R.id.editcause);
        editlocation = view.findViewById(R.id.editlocation);
        editemail = view.findViewById(R.id.editemail);

        edticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewSwitcher switcher = view.findViewById(R.id.my_switcher);
                ViewSwitcher firstname = view.findViewById(R.id.frstname);
                ViewSwitcher secondname = view.findViewById(R.id.scndname);
                ViewSwitcher email = view.findViewById(R.id.email);
                ViewSwitcher location = view.findViewById(R.id.location_view_switcher);
                ViewSwitcher nationality = view.findViewById(R.id.nationality_view_switcher);
                ViewSwitcher dob = view.findViewById(R.id.edtdob);
                ViewSwitcher sex = view.findViewById(R.id.edtsex);
                ViewSwitcher cause = view.findViewById(R.id.edtcause);
                ViewSwitcher disability = view.findViewById(R.id.edtdisab);
                ViewSwitcher phone = view.findViewById(R.id.edtphn);
                ViewSwitcher id = view.findViewById(R.id.edtident);
                id.showNext();
                disability.showNext();
                cause.showNext();
                nationality.showNext();
                dob.showNext();
                switcher.showNext();
                sex.showNext();
                phone.showNext();
                location.showNext();
                firstname.showNext();
                secondname.showNext();
                email.showNext();
                edticon.setVisibility(View.INVISIBLE);
                saveicon.setVisibility(View.VISIBLE);
            }
        });

        saveicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewSwitcher switcher = view.findViewById(R.id.my_switcher);
                ViewSwitcher firstname = view.findViewById(R.id.frstname);
                ViewSwitcher secondname = view.findViewById(R.id.scndname);
                ViewSwitcher email = view.findViewById(R.id.email);
                ViewSwitcher location = view.findViewById(R.id.location_view_switcher);
                ViewSwitcher nationality = view.findViewById(R.id.nationality_view_switcher);
                ViewSwitcher dob = view.findViewById(R.id.edtdob);
                ViewSwitcher sex = view.findViewById(R.id.edtsex);
                ViewSwitcher cause = view.findViewById(R.id.edtcause);
                ViewSwitcher disability = view.findViewById(R.id.edtdisab);
                ViewSwitcher id = view.findViewById(R.id.edtident);
                ViewSwitcher phone = view.findViewById(R.id.edtphn);
                id.showPrevious();
                disability.showPrevious();
                cause.showPrevious();
                nationality.showPrevious();
                dob.showPrevious();
                switcher.showPrevious();
                sex.showPrevious();
                switcher.showPrevious();
                phone.showPrevious();
                location.showPrevious();
                firstname.showPrevious();
                secondname.showPrevious();
                email.showPrevious();
                edticon.setVisibility(View.VISIBLE);
                saveicon.setVisibility(View.INVISIBLE);

                editProfile();
                getMyProfile();
            }
        });

        getMyProfile();
    }

    private void editProfile() {
        String nationality = edtnationality.getText().toString().toUpperCase().trim();
        String dob = edtdateob.getText().toString().toUpperCase().trim();
        String sex = editsex.getText().toString().toUpperCase().trim();
        String email = editemail.getText().toString().trim();
        String phone = editphone.getText().toString().toUpperCase().trim();
        String id = editidentity.getText().toString().toUpperCase().trim();
        String disability = editdisability.getText().toString().toUpperCase().trim();
        String cause = editcause.getText().toString().toUpperCase().trim();
        String location = editlocation.getText().toString().toUpperCase().trim();
        String firstname = editfirstname.getText().toString().toUpperCase().trim();
        String secondname = editsecondname.getText().toString().toUpperCase().trim();

        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("national_id", id);
        map.put("date_of_birth", dob);
        map.put("firstname", firstname);
        map.put("lastname", secondname);
        map.put("email", email);
        map.put("location", location);
        map.put("nationality", nationality);
        map.put("sex", sex);
        map.put("disability", disability);
        map.put("cause", cause);

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
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
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
                    firstname.setText(profile.getFirstname());
                    secondname.setText(profile.getLastname());
                    tv_address.setText(profile.getEmail());
                    location.setText(profile.getLocation());
                    ntnlity.setText(profile.getNationality());
                    dob.setText(profile.getDateOfBirth());
                    sex.setText(profile.getSex());
                    phn.setText(profile.getPhone());
                    ident.setText(profile.getNationalId());
                    disab.setText(profile.getDisability());
                    cause.setText(profile.getCause());
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Toast.makeText(getActivity(), "failed" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
