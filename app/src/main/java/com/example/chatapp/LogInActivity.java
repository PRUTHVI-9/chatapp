package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {

    EditText mobile;
    EditText password;
    Button log_in;
    TextView forget_password;
    Button Create_new;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mobile = findViewById(R.id.et_mobile);
        password = findViewById(R.id.et_password);
        log_in = findViewById(R.id.btn_login);
        forget_password = findViewById(R.id.tv_forget_password);
        Create_new = findViewById(R.id.btn_register);
        preferences = getSharedPreferences("MyappName", MODE_PRIVATE);
        Create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Open_register = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(Open_register);
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile.getText().toString().isEmpty()) {
                    Toast.makeText(LogInActivity.this, "mobile no should not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(LogInActivity.this, "password should not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    JSONObject userlist = new JSONObject(preferences.getString("UserList", "{}"));
                    Log.e("TAG", userlist.toString());
                    if (userlist.has(mobile.getText().toString())) {
                        Toast.makeText(LogInActivity.this, "user exists in list", Toast.LENGTH_SHORT).show();
                        JSONObject userDetails = new JSONObject();

                        userDetails = userlist.getJSONObject(mobile.getText().toString());
                        String first = userDetails.getString("first_name");
                        String pwd = userDetails.getString("password");
                        String pw = password.getText().toString();
                        if (pw.equals(pwd)){
                            Intent p = new Intent(LogInActivity.this,GoogleLoginActivity.class);
                            startActivity(p);
                            finish();

                        } else {

                            Toast.makeText(LogInActivity.this, "Password is  incorrect , try new.", Toast.LENGTH_SHORT).show();
                        }
                        Log.e("TAG","onClick: "+password);
                        Log.e("TAG", "onClick: " +first);

                    } else {
                        Toast.makeText(LogInActivity.this, "userlist not exists in list", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}