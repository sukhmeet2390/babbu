package com.example.babbu;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
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
      //  Debug.startMethodTracing("Babbu.trace");
        setContentView(R.layout.status);
        editStatus = (EditText) findViewById(R.id.edit_status);
    }

    public void onClick(View view) {
        String statusText = editStatus.getText().toString();

       new PostToTwitter().execute(statusText);
        Log.d(TAG, "OnClicked!" + statusText);
        //view.getId()
    }


    class PostToTwitter extends AsyncTask<String, Void, String> {
              // new thread
        @Override
        protected String doInBackground(String... params) {
            try {
                Twitter twitter = new Twitter("student", "password");
                twitter.setAPIRootUrl("http://yamba.marakana.com/api");
                twitter.setStatus(params[0]);

                Log.d(TAG, "Posted Sucessfully" + params[0]);
                return "Sucess post " + params[0];
            } catch (TwitterException e) {
                Log.e(TAG, "Died", e);
                e.printStackTrace();
                return "Failed to post " + params[0];
            }
        }

        @Override
        // ui thread
        protected void onPostExecute(String result) {
            super.onPostExecute(result);    //To change body of overridden methods use File | Settings | File Templates .
            Toast.makeText(StatusActivity.this, "Sucessfully Posted "+ result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        //Debug.stopMethodTracing();
        super.onStop();
    }
}