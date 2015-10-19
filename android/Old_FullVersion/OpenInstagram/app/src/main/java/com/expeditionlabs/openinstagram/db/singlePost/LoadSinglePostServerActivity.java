package com.expeditionlabs.openinstagram.db.singlePost;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.expeditionlabs.openinstagram.Windows.main.OpenInstagramSinglePostActivity;
import com.expeditionlabs.openinstagram.lib.CustomElements.Post;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Weaver on 15-08-26.
 */
public class LoadSinglePostServerActivity extends AsyncTask<String, Void, String> {
    public static final String TAG = "OpenInstagram_LoadSinglePostServerActivity_";
    private Context context;

    public LoadSinglePostServerActivity(Context c){
        this.context = c;
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            String postCode = params[0];

            String link = "http://weaverprojects.com/instagram/loadsinglepost.php";
            String data = URLEncoder.encode("pass","UTF-8") + "=" +
                    URLEncoder.encode("SOME_HIDDEN_CODE","UTF-8");
            data += "&";
            data += URLEncoder.encode("code","UTF-8") + "=" + URLEncoder.encode(postCode, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                sb.append(line);
                break;
            }
            return sb.toString();
        }catch(Exception e){
            Log.e(TAG, "Error: on in app");
            Log.e(TAG, e.toString());
            return "Failed In App";
        }
    }
    @Override
    protected void onPostExecute(String result){
        Log.v(TAG, "Result:[" + result + "]");
        if(result.contains("Failed")){

        }else{
            ArrayList<String> serverElements = split(result);

            String postCode = "";
            String title = "";
            String username = "";
            String link = "";
            String likes = "";
            String profileImgLink = "";
            for(int i =0;i < serverElements.size();i++) {
                Log.v(TAG, "ITEM:" + serverElements.get(i));

                if(profileImgLink.length() == 0) {
                    profileImgLink = serverElements.get(i);
                }else if(likes.length() == 0) {
                    likes = serverElements.get(i);
                }else if(link.length() == 0) {
                    link = serverElements.get(i);
                }else if(username.length() == 0) {
                    username = serverElements.get(i);
                }else if(title.length() == 0) {
                    title = serverElements.get(i);
                }else if(postCode.length() == 0){
                    postCode = serverElements.get(i);

                    OpenInstagramSinglePostActivity.singleListOfExplore.add(new Post(
                            postCode, 0, username, null, profileImgLink, null, link,
                            Integer.parseInt(likes), title, null));

                    String posStr = String.valueOf(OpenInstagramSinglePostActivity.singleListOfExplore.size() - 1);
                    new LoadExternalImgForSinglePostServerActivity(context).execute(link, posStr);
                    new LoadExternalProfileImgForSinglePostServerActivity(context).execute(profileImgLink, posStr);
                    postCode = "";
                    title = "";
                    username = "";
                    link = "";
                    likes = "";
                    profileImgLink = "";
                }
            }
        }
    }
    protected ArrayList<String> split(String s){
        ArrayList<String> elements = new ArrayList<String>();
        while(s.contains("|")){
            Log.v(TAG, "S:" + s);
            int positionOfLastSplitter = s.lastIndexOf("|");
            Log.v(TAG, "Position Of Last Splitter:" + positionOfLastSplitter);
            String singleElement = s.substring(positionOfLastSplitter+1, s.length());
            Log.v(TAG, "Single Element:" + singleElement);
            s = s.substring(0, positionOfLastSplitter);
            Log.v(TAG, "New S:" + s);
            elements.add(singleElement);
        }
        Log.v(TAG, "Final S:" + s);
        elements.add(s);
        return elements;
    }
}
