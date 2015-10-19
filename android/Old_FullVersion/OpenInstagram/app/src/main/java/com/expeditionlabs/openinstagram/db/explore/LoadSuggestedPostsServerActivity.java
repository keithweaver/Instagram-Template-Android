package com.expeditionlabs.openinstagram.db.explore;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.expeditionlabs.openinstagram.Windows.main.OpenInstagramHomeActivity;
import com.expeditionlabs.openinstagram.lib.CustomElements.ExplorePost;

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
public class LoadSuggestedPostsServerActivity extends AsyncTask<String, Void, String>{
    public static final String TAG = "OpenInstagram_LoadSuggestedPostsServerActivity_";

    private Context context;

    public LoadSuggestedPostsServerActivity (Context c){
        this.context = c;
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            String currentUser = params[0];

            String link = "http://www.weaverprojects.com/instagram/explore.php";

            String data = URLEncoder.encode("pass","UTF-8") + "=" +
                    URLEncoder.encode("SOME_HIDDEN_CODE","UTF-8");
            data += "&";
            data += URLEncoder.encode("username","UTF-8") + "=" + URLEncoder.encode(currentUser, "UTF-8");

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
        }catch (Exception e){
            Log.e(TAG, "Error in app");
            Log.e(TAG, e.toString());
            return "Failed In App";
        }
    }
    @Override
    protected void onPostExecute(String result){
        Log.v(TAG, "OnPostExecute Result:[" + result + "]");
        if(result.contains("Failed")){

        }else{
            ArrayList<String> serverElements = split(result);
            //1st element is post code
            //2nd element is title
            //3rd element is username
            //4th element is link
            //5th element is likes
            String postCode = "";
            String link = "";
            int col = 1;
            ExplorePost exploreRow = new ExplorePost(
                    null, null, null,
                    null, null, null,
                    null, null, null);
            int rows = 0;
            for(int i =0;i < serverElements.size();i++) {
                Log.v(TAG, "Loop...");
                Log.v(TAG, "ITEM:" + serverElements.get(i));

                if(link.length() == 0) {
                    link = serverElements.get(i);
                }else if(postCode.length() == 0){
                    if(col == 1){
                        exploreRow = new ExplorePost(
                                null, null, null,
                                null, null, null,
                                null, null, null);
                        OpenInstagramHomeActivity.listOfExploring.add(exploreRow);
                    }

                    postCode = serverElements.get(i);

                    Log.v(TAG, "Current Col:[" + col + "]");
                    Log.v(TAG, "Current Row:[" + rows + "]");
                    Log.v(TAG, "Post Code:[" + postCode + "]");
                    Log.v(TAG, "Link:[" + link + "]");

                    if(col == 1){
                        exploreRow.setImgLink1(link);
                        exploreRow.setPostCode1(postCode);
                    }else if(col == 2){
                        exploreRow.setImgLink2(link);
                        exploreRow.setPostCode2(postCode);
                    }else{
                        exploreRow.setImgLink3(link);
                        exploreRow.setPostCode3(postCode);
                    }

                    OpenInstagramHomeActivity.listOfExploring.set(rows, exploreRow);
                    //InstaTestMainActivity.likesMap.put(InstaTestMainActivity.likesMap.size(), postCode);



                    String posStr = null;
//                    if(OpenInstagramHomeActivity.listOfExp) {
                      posStr= String.valueOf(OpenInstagramHomeActivity.listOfExploring.size() - 1);
//                    }else{
//
//                    }
                    new LoadExploreImageServerActivity(context).execute(link, posStr,
                            String.valueOf(col));



                    postCode = "";
                    link = "";
                    col++;
                    if(col == 4){
                        col = 1;
                        rows++;
                    }
                }
            }

            Log.v(TAG, "End Size:" + OpenInstagramHomeActivity.listOfExploring.size());
        }
    }
    protected ArrayList<String> split(String s){
        ArrayList<String> elements = new ArrayList<String>();
        while(s.contains("|")){
//            Log.v(TAG, "S:" + s);
            int positionOfLastSplitter = s.lastIndexOf("|");
//            Log.v(TAG, "Position Of Last Splitter:" + positionOfLastSplitter);
            String singleElement = s.substring(positionOfLastSplitter+1, s.length());
//            Log.v(TAG, "Single Element:" + singleElement);
            s = s.substring(0, positionOfLastSplitter);
//            Log.v(TAG, "New S:" + s);
            elements.add(singleElement);
        }
        //Log.v(TAG, "Final S:" + s);
        elements.add(s);
        return elements;
    }
}
