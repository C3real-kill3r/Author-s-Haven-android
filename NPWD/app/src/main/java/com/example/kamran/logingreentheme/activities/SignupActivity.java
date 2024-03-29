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

public class SignupActivity extends AppCompatActivity
{
    EditText edtemail, edtpassword, edtusername, confPassword;
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
        confPassword = findViewById(R.id.confPassword);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        sup.setTypeface(custom_font1);
        edtpassword.setTypeface(custom_font);
        lin.setTypeface(custom_font);
        edtusername.setTypeface(custom_font);
        edtemail.setTypeface(custom_font);
        confPassword.setTypeface(custom_font);
        lin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(it);
                finish();
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
        String confPass = confPassword.getText().toString().trim();

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

        if (confPass.isEmpty()){
            confPassword.setError("Password confirmation is required");
            confPassword.requestFocus();
            return;
        }

        if (confPass != password){
            confPassword.setError("Passwords do not match");
            confPassword.requestFocus();
        }

        HashMap<String, String> map = new HashMap<>();
                map.put("email",email);
                map.put("username",username);
                map.put("password",password);
                map.put("confirm_password", confPass);

        HashMap<String, Object> usr = new HashMap<>();
        usr.put("user", map);



        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(usr);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    showCustomDialog();
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
    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Button btnAdd1 = dialogView.findViewById(R.id.buttonOk);
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
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
