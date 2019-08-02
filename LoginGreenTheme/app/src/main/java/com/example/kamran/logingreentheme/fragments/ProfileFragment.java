package com.example.kamran.logingreentheme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.kamran.logingreentheme.R;

public class ProfileFragment extends Fragment {
    ImageView edtprofile, savprofile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtprofile = view.findViewById(R.id.edtprofile);
        savprofile = view.findViewById(R.id.savprofile);
        edtprofile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                edtprofile.setVisibility(View.INVISIBLE);
                savprofile.setVisibility(View.VISIBLE);
                ViewSwitcher viewSwitcher = view.findViewById(R.id.nationality_view_switcher);
                viewSwitcher.showNext();
            }
        });
        savprofile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                edtprofile.setVisibility(View.VISIBLE);
                savprofile.setVisibility(View.INVISIBLE);
                ViewSwitcher viewSwitcher = view.findViewById(R.id.nationality_view_switcher);
                viewSwitcher.showPrevious();
            }
        });
    }
}
