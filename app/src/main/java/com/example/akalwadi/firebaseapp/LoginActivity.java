package com.example.akalwadi.firebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    public Button btnlogin;
    public EditText etemail, etpass;
    public TextView regtv, forgotpasstv;

    private FirebaseAuth fireauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fireauth = FirebaseAuth.getInstance();

        btnlogin = (Button) findViewById(R.id.button2);
        etemail = (EditText) findViewById(R.id.editText4);
        etpass = (EditText) findViewById(R.id.editText5);
        regtv = (TextView) findViewById(R.id.textView3);
        forgotpasstv = (TextView) findViewById(R.id.textView4);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etemail.getText().toString().equals("") && etpass.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                }else{
                    String email = etemail.getText().toString().trim();
                    String pass = etpass.getText().toString();

                    fireauth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Something went wrong!!! Check credentials.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

        regtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });

        forgotpasstv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}
