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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    public Button resetbtn;
    public EditText etemail;
    public TextView tvbacklogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();

        resetbtn = (Button) findViewById(R.id.button3);
        etemail = (EditText) findViewById(R.id.editText6);
        tvbacklogin = (TextView) findViewById(R.id.textView6);

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etemail.getText().toString().equals("")){
                    Toast.makeText(ForgotPassword.this, "Please enter the registered email address.", Toast.LENGTH_SHORT).show();
                }else{
                    String email = etemail.getText().toString().trim();

                    firebaseAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ForgotPassword.this, "We have sent the email successfully", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(ForgotPassword.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        tvbacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
            }
        });
    }
}
