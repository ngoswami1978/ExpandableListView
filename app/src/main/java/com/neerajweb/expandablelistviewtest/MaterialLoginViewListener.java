package com.neerajweb.expandablelistviewtest;

import android.support.design.widget.TextInputLayout;
import android.widget.Spinner;

/**
 * Created by shem on 1/15/16.
 */
public interface MaterialLoginViewListener {
//    void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep);
//void onLogin(TextInputLayout loginUser, TextInputLayout loginPass);
    void onRegister(TextInputLayout registerUser, Spinner flatspinner);
    void onLogin(TextInputLayout loginUser, TextInputLayout loginPass);
    void register(String User,int flatid);
    void login(String User,String pass);

}
