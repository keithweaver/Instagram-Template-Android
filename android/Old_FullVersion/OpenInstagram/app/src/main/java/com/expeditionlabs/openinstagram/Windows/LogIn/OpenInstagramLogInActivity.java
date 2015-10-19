package com.expeditionlabs.openinstagram.Windows.LogIn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.expeditionlabs.openinstagram.R;
import com.expeditionlabs.openinstagram.Windows.main.OpenInstagramHomeActivity;
import com.expeditionlabs.openinstagram.db.login.CheckCredentialsForLogInServerActivity;
import com.expeditionlabs.openinstagram.db.login.SignUpServerActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kweaver on 27/07/15.
 */
public class OpenInstagramLogInActivity extends Activity {
    public static String TAG = "OI_";
    protected Context mContext;

    TableRow signUpProcessWrapper;
    TableRow logInProcessWrapper;

    boolean signUpProcessStarted = false;
    boolean logInProcessStarted = false;

    public static boolean signUpAsyncTaskIsComplete = false;
    public static boolean logInAsyncTaskIsComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_oi_login);
        mContext = this.getApplicationContext();


        final LinearLayout signUpLayout = (LinearLayout) findViewById(R.id.signUpLayout);
        final LinearLayout logInLayout = (LinearLayout) findViewById(R.id.logInLayout);

        logInLayout.setVisibility(View.GONE);

        TableRow logInLayoutBtn = (TableRow) findViewById(R.id.logInLayoutBtn);
        TableRow signUpLayoutBtn = (TableRow) findViewById(R.id.signUpLayoutBtn);

        logInLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!signUpProcessStarted) {
                    logInLayout.setVisibility(View.VISIBLE);
                    signUpLayout.setVisibility(View.GONE);
                }
            }
        });
        signUpLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logInProcessStarted) {
                    signUpLayout.setVisibility(View.VISIBLE);
                    logInLayout.setVisibility(View.GONE);
                }
            }
        });

        final Button logInBtn = (Button) findViewById(R.id.logInBtn);
        final Button signUpBtn = (Button) findViewById(R.id.signUpBtn);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText logInEmailEdittext = (EditText) findViewById(R.id.logInEmailEdittext);
                EditText logInPasswordEdittext = (EditText) findViewById(R.id.logInPasswordEdittext);

                String username = logInEmailEdittext.getText().toString();
                String password = logInPasswordEdittext.getText().toString();

                OpenInstagramHomeActivity.username = username;

                //send log in
                new CheckCredentialsForLogInServerActivity(mContext).execute(username, password);

                logInProcessWrapper.setVisibility(View.VISIBLE);
                logInBtn.setVisibility(View.GONE);
                logInProcessStarted = true;
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText signUpEmailEdittext = (EditText) findViewById(R.id.signUpEmailEdittext);
                EditText signUpUsernameEdittext = (EditText) findViewById(R.id.signUpUsernameEdittext);
                EditText signUpPasswordEdittext = (EditText) findViewById(R.id.signUpPasswordEdittext);

                String email = signUpEmailEdittext.getText().toString();
                String username = signUpUsernameEdittext.getText().toString();
                String password = signUpPasswordEdittext.getText().toString();

                OpenInstagramHomeActivity.username = username;

                //send sign up
                new SignUpServerActivity(mContext).execute(username, password, email);

                signUpProcessWrapper.setVisibility(View.VISIBLE);
                signUpBtn.setVisibility(View.GONE);
                signUpProcessStarted = true;
            }
        });

        signUpProcessWrapper = (TableRow) findViewById(R.id.signUpProcessWrapper);
        signUpProcessWrapper.setVisibility(View.GONE);
        logInProcessWrapper = (TableRow) findViewById(R.id.logInProcessWrapper);
        logInProcessWrapper.setVisibility(View.GONE);


        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(signUpProcessStarted || logInProcessStarted){
                                if(signUpAsyncTaskIsComplete || logInAsyncTaskIsComplete){

                                    int mode = Activity.MODE_PRIVATE;
                                    SharedPreferences loginSharedPreferences;
                                    loginSharedPreferences = getSharedPreferences("openinstagramlogin", mode);
                                    SharedPreferences.Editor editor = loginSharedPreferences.edit();
                                    editor.putString("USERNAME",OpenInstagramHomeActivity.username);
                                    editor.commit();

                                    Intent openHomeWindow = new Intent(mContext,
                                            OpenInstagramHomeActivity.class);
                                    startActivity(openHomeWindow);
                                    t.cancel();
                                }
                            }
                        }
                    });
                }catch (Exception e){

                }
            }
        }, 0, 1000);

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
    @Override
    public void onBackPressed() {
        Log.e(TAG, "Back button pressed.");
    }
}
