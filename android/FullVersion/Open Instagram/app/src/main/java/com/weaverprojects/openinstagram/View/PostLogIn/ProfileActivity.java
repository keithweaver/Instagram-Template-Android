package com.weaverprojects.openinstagram.View.PostLogIn;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weaverprojects.openinstagram.Controller.Temp.Fake;
import com.weaverprojects.openinstagram.Controller.image.BlurTransform;
import com.weaverprojects.openinstagram.Controller.image.CircleTransform;
import com.weaverprojects.openinstagram.Model.UsersProfile;
import com.weaverprojects.openinstagram.R;

/**
 * Created by kweaver on 18/12/15.
 */
public class ProfileActivity extends Activity {

    //UI
    LinearLayout topWrapper;
    ImageView profileImageView;
    TextView userNameTextView;
    TextView aboutTextView;
    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        declareUIObjects();

        UsersProfile currentUserBeingViewed = Fake.getUserProfile();//pass in a user name

        Picasso.with(this).load(currentUserBeingViewed.getProfileImg()).transform(new CircleTransform()).into(profileImageView);

        nameTextView.setText(currentUserBeingViewed.getName());
        userNameTextView.setText(currentUserBeingViewed.getUserName());
        aboutTextView.setText(currentUserBeingViewed.getAbout());
    }
    protected void declareUIObjects(){
        topWrapper = (LinearLayout) findViewById(R.id.topWrapper);
        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        aboutTextView = (TextView) findViewById(R.id.aboutTextView);
        nameTextView = (TextView) findViewById(R.id.nameTextView);

    }
}
