package com.expeditionlabs.openinstagram.db.explore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.expeditionlabs.openinstagram.Windows.main.OpenInstagramHomeActivity;
import com.expeditionlabs.openinstagram.lib.CustomElements.ExplorePost;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Weaver on 15-08-26.
 */
public class LoadExploreImageServerActivity extends AsyncTask<String, String, Bitmap> {
    public static String TAG = "OpenInstagram_LoadExploreImageServerActivity_";

    Context mContext;
    Bitmap bitmap;

    int position;

    int column = 0;

    public LoadExploreImageServerActivity(Context c) {
        super.onPreExecute();
        mContext = c;
    }
    protected Bitmap doInBackground(String... args) {
        String link = args[0];
        String columnStr = args[2];
        try{
            column = Integer.parseInt(columnStr);
        }catch(Exception e){
            Log.v(TAG, "Column couldnt convert");
        }
        Log.v(TAG, "Link:[" + link + "]");
        position = Integer.valueOf(args[1]);

        Log.v(TAG, "LINK:[" + link + "]");
        Log.v(TAG, "POSITION:[" + position + "]");
        Log.v(TAG, "COLUMN:[" + column + "]");

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

            try{
                ExplorePost currentExploreRow = OpenInstagramHomeActivity.listOfExploring.get(position);
                if(column == 1){
                    if(currentExploreRow.getImg1() == null){
                        currentExploreRow.setImg1(image);
                        OpenInstagramHomeActivity.listOfExploring.add(position, currentExploreRow);
                    }
                }else if(column == 2){
                    if(currentExploreRow.getImg2() == null){
                        currentExploreRow.setImg2(image);
                        OpenInstagramHomeActivity.listOfExploring.add(position, currentExploreRow);
                    }
                }else{
                    if(currentExploreRow.getImg3() == null){
                        currentExploreRow.setImg3(image);
                        OpenInstagramHomeActivity.listOfExploring.add(position, currentExploreRow);
                    }
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
