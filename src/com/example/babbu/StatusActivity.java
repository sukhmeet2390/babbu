package com.example.babbu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import winterwell.jtwitter.Twitter;

public class StatusActivity extends Activity {
    static final String TAG = "StatusActivity";
    EditText editStatus;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        editStatus = (EditText) findViewById(R.id.edit_status);
    }

    public void onClick(View view) {
        final String statusText = editStatus.getText().toString();

        new Thread(){
            public void run(){
                Twitter twitter = new Twitter("student", "password");
                twitter.setAPIRootUrl("http://yamba.marakana.com/api");
                twitter.setStatus(statusText);

            }
        }.start();

        Log.d(TAG, "OnClicked!" + statusText);
        //view.getId()
    }
}
