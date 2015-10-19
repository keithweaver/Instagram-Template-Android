package com.expeditionlabs.openinstagram.Windows.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.expeditionlabs.openinstagram.R;
import com.expeditionlabs.openinstagram.db.singlePost.LoadSinglePostServerActivity;
import com.expeditionlabs.openinstagram.lib.CustomAdapters.PostAdapter;
import com.expeditionlabs.openinstagram.lib.CustomElements.Post;

import java.util.ArrayList;

/**
 * Created by kweaver on 27/07/15.
 */
public class OpenInstagramSinglePostActivity extends Activity {
    public static String TAG = "OI_";
    protected Context mContext;

    public static String postImgCode = "";

    public static ArrayList<Post> singleListOfExplore;

    ListView exploreListview;
    PostAdapter mPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_oi_single_post);
        mContext = this.getApplicationContext();

        singleListOfExplore = new ArrayList<Post>();

        new LoadSinglePostServerActivity(mContext).execute(postImgCode);

        exploreListview = (ListView) findViewById(R.id.exploreListview);
        mPostAdapter = new PostAdapter(this, R.layout.oi_single_post, singleListOfExplore);
        exploreListview.setAdapter(mPostAdapter);

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
