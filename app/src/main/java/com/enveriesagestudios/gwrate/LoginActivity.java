package com.enveriesagestudios.gwrate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //Declare an instance of firebase Auth
    private FirebaseAuth mAuth;
    private EditText edtEmail;
    private EditText edtPass;
    private Button btLogin;
    private TextView btRegister;

    //Declare Tag
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Button Definition
        edtEmail = findViewById(R.id.etEmail);
        edtPass = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btnLogin);
        btRegister = findViewById(R.id.tvRegister);

        //Onclick Listener
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //Register Button
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registrationIntent);
            }
        });
    }

    //Sign In Authentication
    private void signIn() {
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "Authentication Success.", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    //Validation Form
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(edtPass.getText().toString())&&TextUtils.isEmpty(edtEmail.getText().toString())) {
            Toast.makeText(LoginActivity.this, "Email and Password Required", Toast.LENGTH_LONG).show();
            edtPass.setError("Required");
            edtEmail.setError("Required");
            result = false;
        } else if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            Toast.makeText(LoginActivity.this, "Email Required", Toast.LENGTH_LONG).show();
            edtEmail.setError("Required");
            result = false;
        } else if (TextUtils.isEmpty(edtPass.getText().toString())) {
            Toast.makeText(LoginActivity.this, "Password Required", Toast.LENGTH_LONG).show();
            edtPass.setError("Required");
            result = false;
        } else {
            edtPass.setError(null);
        }
        return result;
    }

}
