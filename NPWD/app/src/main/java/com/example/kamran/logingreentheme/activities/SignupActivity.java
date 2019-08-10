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

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity
{
    EditText edtemail, edtpassword, edtusername;
    TextView lin,sup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sup = findViewById(R.id.sup);
        lin = findViewById(R.id.lin);
        edtusername = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtpassword);
        edtemail = findViewById(R.id.edtemail);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        sup.setTypeface(custom_font1);
        edtpassword.setTypeface(custom_font);
        lin.setTypeface(custom_font);
        edtusername.setTypeface(custom_font);
        edtemail.setTypeface(custom_font);
        lin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });
        sup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                userSignup();
            }
        });
    }

    private void userSignup(){
        String email = edtemail.getText().toString().trim();
        String password = edtpassword.getText().toString().trim();
        String username = edtusername.getText().toString().trim();

        if (email.isEmpty()){
            edtemail.setError("Email is required");
            edtemail.requestFocus();
            return;
        }

        if (username.isEmpty()){
            edtusername.setError("Username is required");
            edtusername.requestFocus();
            return;
        }

        if (password.isEmpty()){
            edtpassword.setError("Password is required");
            edtpassword.requestFocus();
            return;
        }

        HashMap<String, String> map = new HashMap<>();
                map.put("email",email);
                map.put("username",username);
                map.put("password",password);


        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(map);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {
                        String s = response.body().string();
                        Toast.makeText(SignupActivity.this, s, Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(it);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        Toast.makeText(SignupActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(SignupActivity.this, "Error: Unknown ", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
