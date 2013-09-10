package com.example.babbu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: sukhmeet
 * Date: 10/09/13
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimelineActivity extends Activity {
    TextView textView;
    StatusData statusData;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);

        textView.findViewById(R.id.status_data);
    }
}