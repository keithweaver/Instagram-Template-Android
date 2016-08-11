package ca.keithweaver.openinstagram.View.Windows;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ca.keithweaver.openinstagram.Controller.C;
import ca.keithweaver.openinstagram.Controller.Local.LogInSharedPref;
import ca.keithweaver.openinstagram.Controller.Server.InstaRestClient;
import ca.keithweaver.openinstagram.Model.ServerResponse.ResGeneral;
import ca.keithweaver.openinstagram.R;
import ca.keithweaver.openinstagram.View.Windows.LogIn.LogInActivity;
import ca.keithweaver.openinstagram.View.Windows.Main.MainActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashActivity extends AppCompatActivity {
    public static final String TAG = SplashActivity.class.getSimpleName();

    LogInSharedPref mLogInSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mLogInSharedPref = new LogInSharedPref(this);

    }
    @Override
    protected void onResume(){
        super.onResume();

        if(TextUtils.isEmpty(mLogInSharedPref.getLogInToken()) ||
                TextUtils.isEmpty(mLogInSharedPref.getEmail()) ||
                TextUtils.isEmpty(mLogInSharedPref.getUserName())){
            openLogInWindow();
        }else{
            checkLogInToken(mLogInSharedPref.getEmail(), mLogInSharedPref.getLogInToken(),
                    mLogInSharedPref.getUserName());
        }
    }
    protected void openLogInWindow(){
        mLogInSharedPref.storeDeviceId("");
        mLogInSharedPref.storeLogInToken("");
        mLogInSharedPref.storeUserName("");
        mLogInSharedPref.storeEmail("");

        Intent openLogin = new Intent(this, LogInActivity.class);
        finish();//Closes slash screen so clicking back does take the user back here
        startActivity(openLogin);
    }
    protected void openMainWindow(){
        //// TODO: 16-08-11
        //add register device here if no device id stored

        Intent openMain = new Intent(this, MainActivity.class);
        finish();//Closes slash screen so clicking back does take the user back here
        startActivity(openMain);
    }
    protected void checkLogInToken(String email, String token, String userName){
        InstaRestClient.get().checkLogInToken(C.API_KEY, email, token, userName, new Callback<ResGeneral>() {
            @Override
            public void success(ResGeneral resGeneral, Response response) {
                if(resGeneral.getSuccess() == 1){
                    openMainWindow();
                }else{
                    openLogInWindow();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //// TODO: 16-08-11
                Toast.makeText(SplashActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
