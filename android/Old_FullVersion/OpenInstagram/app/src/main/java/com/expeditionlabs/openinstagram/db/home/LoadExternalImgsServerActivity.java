package com.expeditionlabs.openinstagram.db.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.expeditionlabs.openinstagram.lib.CustomElements.Post;
import com.expeditionlabs.openinstagram.Windows.main.OpenInstagramHomeActivity;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Keith on 2015-07-30.
 */
public class LoadExternalImgsServerActivity extends AsyncTask<String, String, Bitmap> {
    public static String TAG = "OpenInstagram_LoadExternalImgsServerActivity_";

    Context mContext;
    Bitmap bitmap;

    int position;

    public LoadExternalImgsServerActivity(Context c) {
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

        if(image != null){
            //UserPost newPost = new UserPost("Title","about",image,"123");
            //if(newPost.getImg()==null){
            //    Log.v(TAG, "Bitmap is null");
            //}
            //InstaTestMainActivity.mainList.add(newPost);
            try{
                Post currentPost = OpenInstagramHomeActivity.listOfPostings.get(position);
                if(currentPost.getImg() != null){
                    Log.e(TAG, "two of the same images.");
                }else{
                    currentPost.setImg(image);
                    OpenInstagramHomeActivity.listOfPostings.set(position, currentPost);
                    //InstaTestSplashActivity.imagesLoaded++;
                }
            }catch (Exception e){
                Log.e(TAG, "Error setting image");
                Log.e(TAG, e.toString());
            }
            Toast.makeText(mContext, "Loaded", Toast.LENGTH_SHORT).show();
        }else{

            //pDialog.dismiss();
            Toast.makeText(mContext, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

        }
    }
}
