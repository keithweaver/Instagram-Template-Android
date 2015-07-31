package com.expeditionlabs.openinstagram.db.home;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.expeditionlabs.openinstagram.CustomElements.Post;
import com.expeditionlabs.openinstagram.Windows.main.OpenInstagramHomeActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by kweaver on 28/07/15.
 */
public class LoadTenPostsFromServerActivity extends AsyncTask<String, Void, String> {
    public static String TAG = "OpenInstagram_LoadTenPostsFromServerActivity_";

    private Context context;

    public LoadTenPostsFromServerActivity (Context c){
        this.context = c;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String username = params[0];
            //String password = params[1];

            String link="http://weaverprojects.com/instagram/loadposts.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode("SOME_HIDDEN_CODE", "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                //Log.e("LINE:", line);//Dev
                sb.append(line);
                break;
            }
            return sb.toString();
        }catch(Exception e){
            return "Failed InApp 001";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        Log.v(TAG, "OnPostExecute  Result:[" + result + "]");
        if (result.contains("Failed")) {
            Toast.makeText(context, "Unable to load posts.", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<String> serverElements = split(result);
            //1st element is post code
            //2nd element is title
            //3rd element is username
            //4th element is link
            //5th element is likes
            String postCode = "";
            String title = "";
            String username = "";
            String link = "";
            String likes = "";
            String profileImgLink = "";
            for(int i =0;i < serverElements.size();i++) {
                Log.v(TAG, "ITEM:" + serverElements.get(i));
                /*
                Likes: 4
                Link
                user
                title
                postcode
                 */
                /*
                if(postCode.length() == 0){
                    postCode = serverElements.get(i);
                }else if(postCode.length() > 0 && title.length() == 0){
                    title = serverElements.get(i);
                }else if(title.length() > 0 && username.length() == 0){
                    username = serverElements.get(i);
                }else if(title.length() > 0 && username.length() > 0 && link.length() == 0){
                    link = serverElements.get(i);
                    //call the server  call
                    //with size
                }else if(title.length() > 0 && username.length() > 0 && link.length() > 0 && likes.length() == 0){
                */
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

                    OpenInstagramHomeActivity.listOfPostings.add(new Post(
                            postCode, 0, username, null, profileImgLink, null, link,
                            Integer.parseInt(likes), title, null));
                    //InstaTestMainActivity.likesMap.put(InstaTestMainActivity.likesMap.size(), postCode);

                    String posStr = String.valueOf(OpenInstagramHomeActivity.listOfPostings.size() - 1);
                    new LoadExternalImgsServerActivity(context).execute(link, posStr);
                    new LoadExternalProfileImgsServerActivity(context).execute(profileImgLink, posStr);
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