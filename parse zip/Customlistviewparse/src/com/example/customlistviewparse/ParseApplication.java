package com.example.customlistviewparse;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
 
import android.app.Application;
 
public class ParseApplication extends Application {
 
    @Override
    public void onCreate() {
        super.onCreate();
 
        // Add your initialization code here
        Parse.initialize(this, "akFwLlYfXb2jqMzZjBdXE1uaIuPAgvQyuSDXQeWI", "PjASAnUPKh2iu3wtIO0vCsR9tOxxfCuBwNFOIoYe");
 
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
 
        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
 
        ParseACL.setDefaultACL(defaultACL, true);
    }
 
}