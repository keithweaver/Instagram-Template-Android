package ca.keithweaver.openinstagram.Controller.Local;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import ca.keithweaver.openinstagram.Controller.C;
import ca.keithweaver.openinstagram.View.Windows.LogIn.LogInActivity;

/**
 * Created by keithweaver on 16-08-11.
 *
 * Used to store log in information, tokens, usernames, emails
 */
public class LogInSharedPref {
    Context context;

    public LogInSharedPref(Context c){
        this.context = c;
    }
    public String getDeviceId(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp = context.getSharedPreferences(C.sharedPreferences.ACCOUNT, mode);
        return sp.getString(C.sharedPreferences.DEVICE, "");
    }
    public void storeDeviceId(String regId){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp =  context.getSharedPreferences(C.sharedPreferences.ACCOUNT, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(C.sharedPreferences.DEVICE, regId);
        editor.commit();
    }
    public String getUserName(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp = context.getSharedPreferences(C.sharedPreferences.ACCOUNT, mode);
        return sp.getString(C.sharedPreferences.USER_NAME, "");
    }
    public void storeUserName(String userName){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp =  context.getSharedPreferences(C.sharedPreferences.ACCOUNT, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(C.sharedPreferences.USER_NAME, userName);
        editor.commit();
    }
    public String getEmail(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp = context.getSharedPreferences(C.sharedPreferences.ACCOUNT, mode);
        return sp.getString(C.sharedPreferences.EMAIL, "");
    }
    public void storeEmail(String email){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp =  context.getSharedPreferences(C.sharedPreferences.ACCOUNT, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(C.sharedPreferences.EMAIL, email);
        editor.commit();
    }
    public String getLogInToken(){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp = context.getSharedPreferences(C.sharedPreferences.ACCOUNT, mode);
        return sp.getString(C.sharedPreferences.LOG_IN_TOKEN, "");
    }
    public void storeLogInToken(String token){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences sp =  context.getSharedPreferences(C.sharedPreferences.ACCOUNT, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(C.sharedPreferences.LOG_IN_TOKEN, token);
        editor.commit();
    }
}
