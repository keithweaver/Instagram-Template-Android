package com.expeditionlabs.openinstagram.db.login;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.expeditionlabs.openinstagram.Windows.LogIn.OpenInstagramLogInActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by kweaver on 28/07/15.
 */
public class SignUpServerActivity  extends AsyncTask<String, Void, String> {
    public static String TAG = "OpenInstagram_SignUpServerActivity_";

    private Context context;

    public SignUpServerActivity (Context c){
        this.context = c;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String username = params[0];
            String password = params[1];
            String email = params[2];

            String link="http://weaverprojects.com/instagram/signup.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
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
                sb.append(line);
                break;
            }
            return sb.toString();
        }catch(Exception e){
            Log.e(TAG, e.toString());
            return "Failed InApp 001";
        }
    }
    @Override
    protected void onPostExecute(String result){
        Log.v(TAG, "OnPostExecute  Result:" + result);
        if(result.contains("Failed")){
            Toast.makeText(context, "Failed to sign up.", Toast.LENGTH_SHORT).show();
        }else{
            OpenInstagramLogInActivity.signUpAsyncTaskIsComplete = true;
        }
    }
}
