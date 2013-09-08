package com.example.babbu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;

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

        new Thread() {
            public void run() {
                try {
                    Twitter twitter = new Twitter("student", "password");
                    twitter.setAPIRootUrl("http://yamba.marakana.com/api");
                    twitter.setStatus(statusText);
                    Toast.makeText(StatusActivity.this, "Sucessfully Posted ", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Posted Sucessfully" + statusText);

                } catch (TwitterException e) {
                    Log.e(TAG, "Died", e);
                    Toast.makeText(StatusActivity.this, "Fail ", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();

        Log.d(TAG, "OnClicked!" + statusText);
        //view.getId()
    }
}
