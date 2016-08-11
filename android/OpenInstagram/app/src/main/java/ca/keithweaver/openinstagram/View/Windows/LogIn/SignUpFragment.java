package ca.keithweaver.openinstagram.View.Windows.LogIn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ca.keithweaver.openinstagram.Controller.C;
import ca.keithweaver.openinstagram.Controller.Local.LogInSharedPref;
import ca.keithweaver.openinstagram.Controller.Server.InstaRestClient;
import ca.keithweaver.openinstagram.Model.ServerResponse.ResGeneral;
import ca.keithweaver.openinstagram.Model.ServerResponse.ResLogIn;
import ca.keithweaver.openinstagram.R;
import ca.keithweaver.openinstagram.View.Windows.Main.MainActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by keithweaver on 16-07-19.
 */
public class SignUpFragment extends Fragment {
    protected Activity mActivity;
    private static final String TAG = SignUpFragment.class.getSimpleName();

    Button signUpBtn;
    LinearLayout errorLabelWrapper;
    EditText emailEditText;
    EditText userNameEditText;
    EditText passwordEditText;
    ProgressBar signUpProgressBar;
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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        view = declareUIObjects(view);

        mLogInSharedPref = new LogInSharedPref(mActivity);

        signUpProgressBar.setVisibility(View.GONE);
        errorLabelWrapper.setVisibility(View.GONE);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().toLowerCase().trim();
                String userName = userNameEditText.getText().toString().toLowerCase().trim();
                String password = passwordEditText.getText().toString().trim();

                signUpProgressBar.setVisibility(View.VISIBLE);
                signUpBtn.setVisibility(View.GONE);

                if (TextUtils.isEmpty(email)) {
                    showError("Error: Email cannot be blank.");
                } else if (TextUtils.isEmpty(password)) {
                    showError("Error: Password cannot be blank.");
                } else if (!email.contains("@")) {
                    showError("Error: Email must contain @.");
                } else if (TextUtils.isEmpty(userName)) {
                    showError("Error: User Name cannot be blank.");
                } else {
                    checkIfTakenUserName(email, password, userName);
                }
            }
        });

        return view;
    }


    @Override
    public void onResume(){
        super.onResume();


    }
    protected void checkIfTakenUserName(final String email, final String password, final String userName){
        InstaRestClient.get().checkTakenUserName(C.API_KEY, userName, new Callback<ResGeneral>() {
            @Override
            public void success(ResGeneral resGeneral, Response response) {
                if(resGeneral.getSuccess() == 1){
                    signUp(email, password, userName);
                }else{
                    showError(resGeneral.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //// TODO: 16-08-11
                Toast.makeText(mActivity, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void signUp(final String email, final String password, final String userName){
        InstaRestClient.get().signUp(C.API_KEY, email, password, new Callback<ResGeneral>() {
            @Override
            public void success(ResGeneral resGeneral, Response response) {
                if (resGeneral.getSuccess() == 1) {
                    logIn(email, password, userName);
                } else {
                    showError(resGeneral.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //// TODO: 16-08-11
                Toast.makeText(mActivity, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void logIn(final String email, final String password, final String userName){
        InstaRestClient.get().logIn(C.API_KEY, email, password, new Callback<ResLogIn>() {
            @Override
            public void success(ResLogIn resLogIn, Response response) {
                if (resLogIn.getSuccess() == 1) {
                    if (resLogIn.getUsernames() != null && resLogIn.getUsernames().size() > 0) {
                        mLogInSharedPref.storeLogInToken(resLogIn.getMessage());
                        mLogInSharedPref.storeEmail(email);

                        updateUserName(email, password, userName);
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
    protected void updateUserName(String email, String password, final String userName){
        InstaRestClient.get().updateUserName(C.API_KEY, email, password, userName, new Callback<ResGeneral>() {
            @Override
            public void success(ResGeneral resGeneral, Response response) {
                if (resGeneral.getSuccess() == 1) {
                    mLogInSharedPref.storeUserName(userName);

                    openMainWindow();

                } else {
                    mLogInSharedPref.storeLogInToken("");
                    mLogInSharedPref.storeEmail("");

                    showError(resGeneral.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mLogInSharedPref.storeLogInToken("");
                mLogInSharedPref.storeEmail("");

                Toast.makeText(mActivity, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void showError(String message){
        signUpProgressBar.setVisibility(View.GONE);
        signUpBtn.setVisibility(View.VISIBLE);

        errorLabel.setText(message);
        errorLabelWrapper.setVisibility(View.VISIBLE);
    }
    protected void openMainWindow(){
        Intent openMain = new Intent(mActivity, MainActivity.class);
        mActivity.finish();
        mActivity.startActivity(openMain);
    }


    protected View declareUIObjects(View view){
        signUpBtn = (Button) view.findViewById(R.id.signUpBtn);
        errorLabelWrapper = (LinearLayout) view.findViewById(R.id.errorLabelWrapper);
        errorLabel = (TextView) view.findViewById(R.id.errorLabel);
        emailEditText = (EditText) view.findViewById(R.id.emailEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
        userNameEditText = (EditText) view.findViewById(R.id.userNameEditText);
        signUpProgressBar = (ProgressBar) view.findViewById(R.id.signUpProgressBar);
        return view;

    }
}
