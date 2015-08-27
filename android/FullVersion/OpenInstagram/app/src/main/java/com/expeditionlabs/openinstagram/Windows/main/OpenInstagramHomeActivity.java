package com.expeditionlabs.openinstagram.Windows.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TableRow;

import com.expeditionlabs.openinstagram.lib.CustomAdapters.PostAdapter;
import com.expeditionlabs.openinstagram.lib.CustomElements.ExplorePost;
import com.expeditionlabs.openinstagram.lib.CustomElements.Post;
import com.expeditionlabs.openinstagram.R;
import com.expeditionlabs.openinstagram.db.home.LoadTenPostsFromServerActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kweaver on 27/07/15.
 */
public class OpenInstagramHomeActivity extends Activity {
    public static String TAG = "OI_";
    protected Context mContext;

    public static String username = "";

    public static ArrayList<Post> listOfPostings;
    public static ArrayList<ExplorePost> listOfExploring;

    PostAdapter mPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_oi_main);
        mContext = this.getApplicationContext();

        new LoadTenPostsFromServerActivity(mContext).execute(username);

        listOfPostings = new ArrayList<Post>();

        mPostAdapter = new PostAdapter(this, R.layout.oi_single_post, listOfPostings);
        ListView mainListview = (ListView) findViewById(R.id.mainListview);
        mainListview.setAdapter(mPostAdapter);


        final int[] loads = {0};
        final Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(loads[0] == 10){
                                mTimer.cancel();
                            }
                            Log.v(TAG, "List of Posting Size:" + listOfPostings.size());
                            mPostAdapter.notifyDataSetChanged();
                            loads[0]++;
                        }
                    });
                } catch (Exception e) {

                }
            }
        }, 0, 2000);





        /*
        ----------- BOTTOM MENU --------
         */
        TableRow exploreBtn = (TableRow) findViewById(R.id.exploreBtn);
        exploreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openExploreWindow = new Intent(v.getContext(),
                        OpenInstagramExploreActivity.class);
                startActivity(openExploreWindow);
            }
        });
    }
    protected void onPause(){
        super.onPause();
    }
    protected void onResume(){
        super.onResume();
    }
    protected void onDestroy(){
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        Log.e(TAG, "Back button pressed.");
    }
}
