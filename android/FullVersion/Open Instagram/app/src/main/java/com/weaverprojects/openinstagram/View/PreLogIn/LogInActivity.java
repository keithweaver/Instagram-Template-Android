package com.weaverprojects.openinstagram.View.PreLogIn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.weaverprojects.openinstagram.R;

/**
 * Created by Keith on 2015-10-25.
 */
public class LogInActivity extends Activity {
    LinearLayout signUpLayout;
    LinearLayout logInLayout;
    LinearLayout signUpLayoutBtn;
    LinearLayout logInLayoutBtn;

    Button signUpBtn;
    TableRow signUpProcessWrapper;

    Button logInBtn;
    TableRow logInProcessWrapper;

    EditText logInEmailEdittext;
    EditText logInPasswordEdittext;

    EditText signUpEmailEdittext;
    EditText signUpUsernameEdittext;
    EditText signUpPasswordEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log_in);
        declareUI();

        signUpLayout.setVisibility(View.GONE);
        signUpProcessWrapper.setVisibility(View.GONE);
        logInProcessWrapper.setVisibility(View.GONE);

        logInLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpLayout.setVisibility(View.GONE);
                logInLayout.setVisibility(View.VISIBLE);
            }
        });
        signUpLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpLayout.setVisibility(View.VISIBLE);
                logInLayout.setVisibility(View.GONE);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    protected void declareUI(){
        signUpLayout = (LinearLayout) findViewById(R.id.signUpLayout);
        logInLayout = (LinearLayout) findViewById(R.id.logInLayout);
        logInLayoutBtn = (LinearLayout) findViewById(R.id.logInLayoutBtn);
        signUpLayoutBtn = (LinearLayout) findViewById(R.id.signUpLayoutBtn);

        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signUpProcessWrapper = (TableRow) findViewById(R.id.signUpProcessWrapper);

        logInBtn = (Button) findViewById(R.id.logInBtn);
        logInProcessWrapper = (TableRow) findViewById(R.id.logInProcessWrapper);

        logInEmailEdittext = (EditText) findViewById(R.id.logInEmailEdittext);
        logInPasswordEdittext = (EditText) findViewById(R.id.logInPasswordEdittext);

        signUpEmailEdittext = (EditText) findViewById(R.id.signUpEmailEdittext);
        signUpUsernameEdittext = (EditText) findViewById(R.id.signUpUsernameEdittext);
        signUpPasswordEdittext = (EditText) findViewById(R.id.signUpPasswordEdittext);
    }
}
