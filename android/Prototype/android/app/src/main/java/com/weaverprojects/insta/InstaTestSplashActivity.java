package com.weaverprojects.insta;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.weaverprojects.insta.db.LoadPostsServerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kweaver on 20/07/15.
 */
public class InstaTestSplashActivity extends Activity {
    public static String TAG = "WEAVER_SplashActivity_";
    Context mContext;

    public static int imagesLoaded = 0;
    public static int profileImagesLoaded = 0;
    public static boolean imagesLoadedError = false;
    public static boolean loadPostsAsyncTastIsComplete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this.getApplicationContext();

        InstaTestMainActivity.mainList = new ArrayList<UserPost>();
        InstaTestMainActivity.likesMap = new HashMap<Integer, String>();
        //new LoadExternalImgsServerActivity(mContext).execute("http://www.keithweaver.ca/icons/github.png");
        new LoadPostsServerActivity(mContext).execute("kweaver0");


        final Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.v(TAG, "Timer ImagesLoaded:" + imagesLoaded);
                            if (profileImagesLoaded == InstaTestMainActivity.mainList.size() &&
                                    imagesLoaded == InstaTestMainActivity.mainList.size() &&
                                    nullImageElements() && loadPostsAsyncTastIsComplete) {
                                mTimer.cancel();
                                startActivity(new Intent("android.intent.action.InstaTestMainActivity"));
                            }else if(imagesLoadedError){
                                //go to error screen
                            }
                        }
                    });
                } catch (Exception e) {
                    Log.e(TAG, " Error occurred with timer");
                    Log.e(TAG, e.toString());
                }
            }
        }, 0, 1000);
    }
    protected boolean nullImageElements(){
        try{
            for(int i = 0;i < InstaTestMainActivity.mainList.size();i++){
                UserPost currentPost = InstaTestMainActivity.mainList.get(i);
                if(currentPost.getImg() == null){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            imagesLoadedError = true;
            return false;
        }
    }
}
