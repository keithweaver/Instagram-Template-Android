package com.weaverprojects.openinstagram.Controller.local;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.weaverprojects.openinstagram.Controller.C;

/**
 * Created by kweaver on 18/12/15.
 */
public class SessionTokenPref {
    Context c;

    public SessionTokenPref(Context c) {
        this.c = c;
    }

    public String getSessionToken(){
        return getSharePref(C.local.pref.SESSION_TOKEN);
    }
    public String getUserName(){
        return getSharePref(C.local.pref.USER_NAME);
    }
    public void storeSessionToken(String token){
        storeSharePref(C.local.pref.SESSION_TOKEN, token);
    }
    public void storeUserName(String userName){
        storeSharePref(C.local.pref.USER_NAME, userName);
    }
    public void eraseSessionToken() {
        eraseSharedPref(C.local.pref.SESSION_TOKEN);
    }
    public void eraseUserName() {
        eraseSharedPref(C.local.pref.USER_NAME);
    }



    private String getSharePref(String key) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mSP;
        mSP = c.getSharedPreferences(C.local.pref.SHARE_PREF, mode);
        String r = mSP.getString(key, "");
        return r;
    }
    private void storeSharePref(String key, String value) {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mSP = c.getSharedPreferences(C.local.pref.SHARE_PREF, mode);
        SharedPreferences.Editor editor = mSP.edit();
        editor.putString(key, value);
        editor.commit();
    }
    private void eraseSharedPref(String key){
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mSP;
        mSP = c.getSharedPreferences(C.local.pref.SHARE_PREF, mode);
        SharedPreferences.Editor editor = mSP.edit();
        editor.putString(key, "");
        editor.commit();
    }
}