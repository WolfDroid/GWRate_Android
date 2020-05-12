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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    //Declare an instance of firebase Auth
    private FirebaseAuth mAuth;
    private EditText edtEmail;
    private EditText edtUserName;
    private EditText edtPassword;
    private EditText edtRePassword;
    private Button btRegister;

    //Declare Tag
    private static final String TAG = "RegisterActivity";

    //Oncreate Function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Button Definition
        edtEmail = findViewById(R.id.etEmail);
        edtUserName = findViewById(R.id.etUserName);
        edtPassword = findViewById(R.id.etPassword);
        edtRePassword = findViewById(R.id.etRePassword);
        btRegister= findViewById(R.id.btnRegister);

        //Onclick Listener
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    //Sign up Authentication
    private void signUp() {

        //showProgressDialog();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String repassword = edtRePassword.getText().toString();

        //Form Authentication Function
        if (!validateForm()) {
            return;
        }

        //Create user function
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent backToLoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(backToLoginIntent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    //Validation Form
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(edtPassword.getText().toString())
                &&TextUtils.isEmpty(edtEmail.getText().toString())
                &&TextUtils.isEmpty(edtUserName.getText().toString())
                &&TextUtils.isEmpty(edtRePassword.getText().toString())
        ) {
            Toast.makeText(RegisterActivity.this, "Please fill the form properly", Toast.LENGTH_LONG).show();
            edtPassword.setError("Required");
            edtEmail.setError("Required");
            edtUserName.setError("Required");
            edtRePassword.setError("Required");
            result = false;
        } else if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Email Required", Toast.LENGTH_LONG).show();
            edtEmail.setError("Required");
            result = false;
        } else if (TextUtils.isEmpty(edtUserName.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Pelase enter your Full Name", Toast.LENGTH_LONG).show();
            edtUserName.setError("Required");
            result = false;
        } else if (TextUtils.isEmpty(edtPassword.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Password Required", Toast.LENGTH_LONG).show();
            edtPassword.setError("Required");
            result = false;
        } else if (TextUtils.isEmpty(edtRePassword.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Please reconfirm your Password", Toast.LENGTH_LONG).show();
            edtRePassword.setError("Required");
            result = false;
        } else {
            edtPassword.setError(null);
            edtRePassword.setError(null);
        }

        //Password and Re password Equalize Checker
        if (!TextUtils.isEmpty(edtPassword.getText().toString()) &&!TextUtils.isEmpty(edtRePassword.getText().toString())){
            if ( edtRePassword.getText().toString().equals( edtPassword.getText().toString() ) ){
                result = true;
            } else {
                edtRePassword.setError("Password does not match");
                result = false;
            }
        }

        return result;
    }

}
