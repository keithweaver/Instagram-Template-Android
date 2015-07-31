package com.expeditionlabs.openinstagram;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

import com.expeditionlabs.openinstagram.Windows.LogIn.OpenInstagramLogInActivity;
import com.expeditionlabs.openinstagram.Windows.main.OpenInstagramHomeActivity;


public class OpenInstagramSplashActivity extends Activity {
    public static String TAG = "OI_";
    //protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_oi_splash);
        //this = mContext.getApplicationContext();

        int mode = Activity.MODE_PRIVATE;
        SharedPreferences loginSharedPreferences;
        loginSharedPreferences = getSharedPreferences("openinstagramlogin", mode);
        String tempUser = loginSharedPreferences.getString("USERNAME","");
        if(tempUser.length() > 0){
            OpenInstagramHomeActivity.username = tempUser;
            Intent openLogInWindow = new Intent(this, OpenInstagramHomeActivity.class);
            startActivity(openLogInWindow);
        }else{
            Intent openLogInWindow = new Intent(this, OpenInstagramLogInActivity.class);
            startActivity(openLogInWindow);
        }
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
