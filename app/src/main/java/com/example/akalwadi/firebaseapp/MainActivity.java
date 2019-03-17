package com.example.akalwadi.firebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class MainActivity extends AppCompatActivity {

    public Button regbtn;
    public EditText etemail, etpass, etrepass;
    public TextView regtext;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        regtext = (TextView) findViewById(R.id.textView);
        regbtn = (Button) findViewById(R.id.button);
        etemail = (EditText) findViewById(R.id.editText);
        etpass = (EditText) findViewById(R.id.editText2);
        etrepass = (EditText) findViewById(R.id.editText3);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etemail.getText().toString().equals("") && etpass.getText().toString().equals("") && etrepass.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }else{
                    String email = etemail.getText().toString().trim();
                    String pass = etpass.toString();

                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(MainActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                    }
                                }
                            });
                }
            }
        });

    }
}
