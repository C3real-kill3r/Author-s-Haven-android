package com.example.kamran.logingreentheme.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kamran.logingreentheme.R;
import com.example.kamran.logingreentheme.RetrofitClient;
import com.example.kamran.logingreentheme.activities.LoginActivity;
import com.example.kamran.logingreentheme.adapters.TopicsAdapter;
import com.example.kamran.logingreentheme.model.Articles.Article;
import com.example.kamran.logingreentheme.model.Articles.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    ArrayList<Result> topics = new ArrayList<>();
    private TopicsAdapter topicsAdapter;
    private RecyclerView topics_recycler_view;
    private TextView textViewResult;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        topics_recycler_view = view.findViewById(R.id.topics_recycler_view);
        topicsAdapter = new TopicsAdapter(getContext(), topics);
        topics_recycler_view.setAdapter(topicsAdapter);
        topics_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()) );
        swipeRefreshLayout = view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        getActivity().setTitle("Home");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewResult = view.findViewById(R.id.text_view_result);

        listTopics();

    }

    private void listTopics() {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken = preferences.getString("TOKEN", null);//second parameter default value
        String token = retrivedToken;
        Call<List<Result>> call = RetrofitClient
                .getInstance()
                .getApi()
                .getArticles(token);

        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("code: " + response.code());
                    if (response.code() == 403) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                    return;
                }
                topics = new ArrayList<>(response.body());
                topicsAdapter = new TopicsAdapter(getContext(), topics);
                topics_recycler_view.setAdapter(topicsAdapter);
                DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                topics_recycler_view.addItemDecoration(itemDecoration);

            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        listTopics();
    }
}
