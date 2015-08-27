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

import com.expeditionlabs.openinstagram.R;
import com.expeditionlabs.openinstagram.lib.CustomAdapters.ExploreAdapter;

/**
 * Created by kweaver on 27/07/15.
 */
public class OpenInstagramExploreActivity extends Activity {
    public static String TAG = "OpenInstagram_OpenInstagramExploreActivity_";
    protected Context mContext;

    ExploreAdapter mExploreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_oi_explore);
        mContext = this.getApplicationContext();


        ListView exploreListView = (ListView) findViewById(R.id.exploreListView);
        mExploreAdapter = new ExploreAdapter(this, R.layout.oi_single_explore_row,
                OpenInstagramHomeActivity.listOfExploring);
        exploreListView.setAdapter(mExploreAdapter);

        Log.v(TAG, "Explore Array Adapter loaded.");

        /*
        -------- Bottom Menu ------
         */
        TableRow homeBtn = (TableRow) findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openHomeWindow = new Intent(v.getContext(),
                                        OpenInstagramHomeActivity.class);
                startActivity(openHomeWindow);
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
    
}
