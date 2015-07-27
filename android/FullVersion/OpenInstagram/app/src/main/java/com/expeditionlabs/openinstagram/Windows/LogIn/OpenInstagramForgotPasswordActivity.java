package com.expeditionlabs.openinstagram.Windows.LogIn;

/**
 * Created by kweaver on 27/07/15.
 */
public class OpenInstagramForgotPasswordActivity extends Activity {
    public static String TAG = "OI_";
    //protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_oi_splash);
        //this = mContext.getApplicationContext();
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
