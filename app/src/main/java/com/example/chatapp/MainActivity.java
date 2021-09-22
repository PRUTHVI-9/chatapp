package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText first_name;
    EditText middle_name;
    EditText last_name;
    EditText dd;
    EditText mm;
    EditText yyyy;
    EditText number;
    EditText email;
    EditText password;
    EditText confirm_password;
    Button submit;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first_name = findViewById(R.id.et_first_name);
        middle_name = findViewById(R.id.et_middle_name);
        last_name = findViewById(R.id.et_last_name);
        dd = findViewById(R.id.et_dd);
        mm = findViewById(R.id.et_mm);
        yyyy = findViewById(R.id.et_yyyy);
        number = findViewById(R.id.et_number);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        confirm_password = findViewById(R.id.et_confirm_password);
        submit = findViewById(R.id.btn_submit);
        preferences = getSharedPreferences("MyappName",MODE_PRIVATE);
        editor = preferences.edit();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject userdetails = new JSONObject();
                JSONObject userlist = new JSONObject();
                if (first_name.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"first name should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (middle_name.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"middle name should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (last_name.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"last name should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dd.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"dd should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mm.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"mm should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (yyyy.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"yyyy should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (number.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"number should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"email should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"password should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (confirm_password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"confirm password should not be empty.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().equals(confirm_password.getText().toString())) {
                    Toast.makeText(MainActivity.this, "password matched", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "password mismatched", Toast.LENGTH_SHORT).show();
                }
                try {
                    userdetails.put("first_name",first_name.getText().toString());
                    userdetails.put("middle_name",middle_name.getText().toString());
                    userdetails.put("last_name",last_name.getText().toString());
                    userdetails.put("number",number.getText().toString());
                    userdetails.put("dob", dd.getText().toString() + "/" + mm.getText().toString() + "/" + yyyy.getText().toString());
                    userdetails.put("password",password.getText().toString());
                    if (userlist.has(email.getText().toString())){
                        Toast.makeText(MainActivity.this, "Usrr Already Exist.", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        userlist = new JSONObject(preferences.getString("UserList","{}"));
                        userlist.put(email.getText().toString(),userdetails);
                        editor.putString("UserList",userlist.toString());
                        editor.commit();
                    }
                    Log.e("TAG", userlist.toString() );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}