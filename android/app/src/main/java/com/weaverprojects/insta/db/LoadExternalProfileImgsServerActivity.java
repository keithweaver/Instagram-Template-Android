package com.weaverprojects.insta.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.weaverprojects.insta.InstaTestMainActivity;
import com.weaverprojects.insta.InstaTestSplashActivity;
import com.weaverprojects.insta.UserPost;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Keith on 2015-07-25.
 */
public class LoadExternalProfileImgsServerActivity  extends AsyncTask<String, String, Bitmap> {
    public static String TAG = "WEAVER_LoadExternalProfileImgsServerActivity_";

    Context mContext;
    Bitmap bitmap;

    int position;

    public LoadExternalProfileImgsServerActivity(Context c) {
        super.onPreExecute();
        mContext = c;
    }

    protected Bitmap doInBackground(String... args) {
        String link = args[0];
        Log.v(TAG, "Link:[" + link + "]");
        position = Integer.valueOf(args[1]);
        try {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());

        } catch (Exception e) {
            Log.e("WEAVER_DOINBACKGROUND_", "ERROR HERE");
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap image) {

        if (image != null) {
            //UserPost newPost = new UserPost("Title","about",image,"123");
            //if(newPost.getImg()==null){
            //    Log.v(TAG, "Bitmap is null");
            //}
            //InstaTestMainActivity.mainList.add(newPost);
            try {
                UserPost currentPost = InstaTestMainActivity.mainList.get(position);
                if (currentPost.getProfileImg() != null) {
                    Log.e(TAG, "two of the same images.");
                } else {
                    currentPost.setProfileImg(image);
                    InstaTestMainActivity.mainList.set(position, currentPost);
                    InstaTestSplashActivity.profileImagesLoaded++;
                }
            } catch (Exception e) {
                Log.e(TAG, "Error setting image");
                Log.e(TAG, e.toString());
            }
            //Toast.makeText(mContext, "Loaded", Toast.LENGTH_SHORT).show();
        } else {

            //pDialog.dismiss();
            Toast.makeText(mContext, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

        }
    }
}