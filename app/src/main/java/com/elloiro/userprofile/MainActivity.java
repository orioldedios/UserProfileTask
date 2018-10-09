package com.elloiro.userprofile;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Profile profile;
    private Gson gson;
    private TextView name;
    private TextView handle;
    private TextView following;
    private TextView followers;
    private TextView about;
    private ImageView background;
    private ImageView profileImage;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();
        queue = Volley.newRequestQueue(this);

        try {
            InputStream stream = getAssets().open("Me.json");
            InputStreamReader reader = new InputStreamReader(stream);
            profile = gson.fromJson(reader, Profile.class);
        } catch (IOException e) {
            Toast.makeText(this,"No se puedo leer json", Toast.LENGTH_LONG).show();
        }

        name = findViewById(R.id.name);
        handle = findViewById(R.id.handle);
        following = findViewById(R.id.FollowingNumber);
        followers = findViewById(R.id.FollowersNumber);
        about = findViewById(R.id.AboutText);
        background = findViewById(R.id.ImageBackgrownd);
        profileImage = findViewById(R.id.ImageProfile);

        updateProfile();

    }
    private void updateProfile() {
        name.setText(profile.getName() + profile.getLastname());
        handle.setText(profile.getHandle());
        following.setText(profile.getFollowing());
        followers.setText(profile.getFollowers());
        about.setText(profile.getAbout());

        Glide.with(this).load("file:///android_asset/Uri.jpg").into(profileImage);
        Glide.with(this).load("file:///android_asset/UserProfile-background.jpg").into(background);

    }

}
