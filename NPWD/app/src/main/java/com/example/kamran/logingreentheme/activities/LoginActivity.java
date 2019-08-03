package com.example.kamran.logingreentheme.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamran.logingreentheme.R;
import com.example.kamran.logingreentheme.RetrofitClient;
import com.example.kamran.logingreentheme.model.User;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity
{
    EditText pswd,usrusr;
    TextView sup,lin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lin = findViewById(R.id.lin);
        usrusr = findViewById(R.id.usrusr);
        pswd = findViewById(R.id.edtpassword);
        sup = findViewById(R.id.sup);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        lin.setTypeface(custom_font1);
        sup.setTypeface(custom_font);
        usrusr.setTypeface(custom_font);
        pswd.setTypeface(custom_font);

        final String[] token = new String[1];

        lin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loginUser();
            }

            private void loginUser() {
                String email = usrusr.getText().toString().trim();
                String password = pswd.getText().toString().trim();

                if (email.isEmpty()){
                    usrusr.setError("Email is required");
                    usrusr.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    pswd.setError("Username is required");
                    pswd.requestFocus();
                    return;
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("email",email);
                map.put("password",password);


                Call<User> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .loginUser(map);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (response.isSuccessful()) {
                            String s = response.body().getToken();
                            token[0] = response.body().getToken();
                            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(it);
                        }
                        else {
                            try {
                                Toast.makeText(LoginActivity.this, "Error: " + response.errorBody().string(), Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                Toast.makeText(LoginActivity.this, "Error: Unknown ", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        sup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(it);
            }
        });
    }
}