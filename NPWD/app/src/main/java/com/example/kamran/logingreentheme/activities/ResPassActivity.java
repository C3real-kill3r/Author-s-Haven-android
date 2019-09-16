package com.example.kamran.logingreentheme.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamran.logingreentheme.R;
import com.example.kamran.logingreentheme.RetrofitClient;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResPassActivity extends AppCompatActivity {

    EditText eml;
    TextView resPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_pass);

        eml = findViewById(R.id.eml);
        resPassword = findViewById(R.id.ressPassword);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        eml.setTypeface(custom_font);
        resPassword.setTypeface(custom_font);

        resPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }

            private void resetPassword() {
                String email = eml.getText().toString().trim();

                if (email.isEmpty()) {
                    eml.setError("Email is required");
                    eml.requestFocus();
                    return;
                }

                HashMap<String, String> usr = new HashMap<>();
                usr.put("email", email);

                Call<ResponseBody> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .forgotPassword(usr);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            showCustomDialog();
                        }
                        else {
                            try {
                                Toast.makeText(ResPassActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                Toast.makeText(ResPassActivity.this, "Error: Unknown ", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ResPassActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewGroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.rest_diag, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Button btnAdd1 = dialogView.findViewById(R.id.buttonOk);
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ResPassActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
