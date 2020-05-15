package com.enveriesagestudios.gwrate.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enveriesagestudios.gwrate.R;
import com.enveriesagestudios.gwrate.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainProfile extends AppCompatActivity {

    //Declare an instance of firebase Auth
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    //Declare Variable for Navbar Header
    private TextView UsernameProf;
    private TextView UserEmailProf;
    private ImageView UserPhotoProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        //Fetching from view
        UsernameProf =  findViewById(R.id.fullname);
        UserPhotoProf = findViewById(R.id.image_profile);

        //Initiaziation Firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //Fetch Display
        UsernameProf.setText(currentUser.getDisplayName());
        UsernameProf.setText(currentUser.getDisplayName());

        //Glide to Photo Profile
        if(currentUser.getPhotoUrl()!= null) {
            Glide.with(this).load(currentUser.getPhotoUrl()).into(UserPhotoProf);
        }
    }
}
