package ca.keithweaver.openinstagram.View.Windows.LogIn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import ca.keithweaver.openinstagram.Controller.C;
import ca.keithweaver.openinstagram.Controller.Local.LogInSharedPref;
import ca.keithweaver.openinstagram.Controller.Server.InstaRestClient;
import ca.keithweaver.openinstagram.Model.ServerResponse.ResLogIn;
import ca.keithweaver.openinstagram.R;
import ca.keithweaver.openinstagram.View.Windows.Main.MainActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by keithweaver on 16-07-19.
 */
public class LogInFragment extends Fragment {
    protected Activity mActivity;
    private static final String TAG = LogInFragment.class.getSimpleName();


    Button logInBtn;
    LinearLayout errorLabelWrapper;
    EditText emailEditText;
    EditText passwordEditText;
    ProgressBar logInProgressBar;
    TextView errorLabel;

    LogInSharedPref mLogInSharedPref;

    @Override
    public void onAttach(Activity act)
    {
        super.onAttach(act);
        mActivity = act;

    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState)
    {
        super.onActivityCreated(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mLogInSharedPref = new LogInSharedPref(mActivity);

        view = declareUIObjects(view);

        logInProgressBar.setVisibility(View.GONE);
        errorLabelWrapper.setVisibility(View.GONE);


        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().toLowerCase().trim();
                String password = passwordEditText.getText().toString().trim();

                logInProgressBar.setVisibility(View.VISIBLE);
                logInBtn.setVisibility(View.GONE);

                if (TextUtils.isEmpty(email)) {
                    showError("Error: Email cannot be blank.");
                } else if (TextUtils.isEmpty(password)) {
                    showError("Error: Password cannot be blank.");
                } else if (!email.contains("@")) {
                    showError("Error: Email must contain @.");
                } else {
                    logIn(email, password);
                }
            }
        });

        return view;
    }


    @Override
    public void onResume(){
        super.onResume();


    }

    protected void showError(String message){
        logInProgressBar.setVisibility(View.GONE);
        logInBtn.setVisibility(View.VISIBLE);

        errorLabel.setText(message);
        errorLabelWrapper.setVisibility(View.VISIBLE);
    }
    protected void logIn(final String email, String password){
        InstaRestClient.get().logIn(C.API_KEY, email, password, new Callback<ResLogIn>() {
            @Override
            public void success(ResLogIn resLogIn, Response response) {
                if (resLogIn.getSuccess() == 1) {
                    if (resLogIn.getUsernames() != null && resLogIn.getUsernames().size() > 0) {
                        mLogInSharedPref.storeLogInToken(resLogIn.getMessage());
                        mLogInSharedPref.storeUserName(resLogIn.getUsernames().get(0));
                        mLogInSharedPref.storeEmail(email);

                        openMainWindow();
                    } else {
                        showError("Error: Unable to find your user name");
                    }
                } else {
                    showError(resLogIn.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //// TODO: 16-08-11
                Toast.makeText(mActivity, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void openMainWindow(){
        Intent openMain = new Intent(mActivity, MainActivity.class);
        mActivity.finish();
        mActivity.startActivity(openMain);
    }

    protected View declareUIObjects(View view){
        logInBtn = (Button) view.findViewById(R.id.logInBtn);
        errorLabelWrapper = (LinearLayout) view.findViewById(R.id.errorLabelWrapper);
        errorLabel = (TextView) view.findViewById(R.id.errorLabel);
        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        logInProgressBar = (ProgressBar) view.findViewById(R.id.logInProgressBar);
        return view;

    }
}