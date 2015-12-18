package com.weaverprojects.openinstagram.View.PostLogIn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TableRow;

import com.weaverprojects.openinstagram.Controller.Temp.Fake;
import com.weaverprojects.openinstagram.Model.Post;
import com.weaverprojects.openinstagram.R;
import com.weaverprojects.openinstagram.View.Adapters.HomeArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Keith on 2015-10-25.
 */
public class HomeActivity extends Activity {
    public static final String TAG = "instagram_";
    HomeArrayAdapter mHomeArrayAdapter;
    ArrayList<Post> mPosts;


    //UI
    ListView mainListView;
    TableRow profileBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        declareUIObjects();

        mPosts = new ArrayList<>();
        mPosts = Fake.getFakePosts();
        mHomeArrayAdapter = new HomeArrayAdapter(this, R.layout.row_home_item, mPosts);
        mainListView.setAdapter(mHomeArrayAdapter);

        handleBottomMenuButtons();
    }
    @Override
    protected void onResume(){
        super.onResume();

    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
    protected void declareUIObjects(){
        mainListView = (ListView) findViewById(R.id.mainListView);
        profileBtn = (TableRow) findViewById(R.id.profileBtn);
    }
    protected void handleBottomMenuButtons(){
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openProfileWindow = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(openProfileWindow);
            }
        });
    }
}
