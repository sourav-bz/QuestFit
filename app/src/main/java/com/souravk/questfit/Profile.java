package com.souravk.questfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ImageView profilePic;
    private TextView firstName, lastName;
    private LinearLayout signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        profilePic = findViewById(R.id.profile_pic);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        signOut = findViewById(R.id.signoutbtn);

        Log.e("TAG", "onCreate: "+currentUser.getPhotoUrl());

        String picurl = currentUser.getPhotoUrl().toString();
        Picasso.get().load(picurl).into(profilePic);
        firstName.setText(currentUser.getDisplayName().split(" ")[0]);
        lastName.setText(currentUser.getDisplayName().split(" ")[1]);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}