package com.palmalabs.parsesaveeventuallyissue;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class MainActivity extends ActionBarActivity {
    private TextView mCurrentValueTextView;
    private int mCurrentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCurrentValue = 0;

        mCurrentValueTextView = (TextView) findViewById(R.id.text_view_current_value);

        final ParseQuery parseQuery = new ParseQuery("Counter");
        parseQuery.addDescendingOrder("value");
        parseQuery.fromLocalDatastore();

        parseQuery.getFirstInBackground(new GetCallback() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e != null) {
                    Log.d("main", e.getMessage());
                }
                else if (parseObject != null) {
                    mCurrentValue = parseObject.getInt("value");
                    mCurrentValueTextView.setText(String.valueOf(mCurrentValue));
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            mCurrentValue++;
            ParseObject parseObject = new ParseObject("Counter");
            parseObject.put("value", mCurrentValue);

            parseObject.saveEventually();

            mCurrentValueTextView.setText(String.valueOf(mCurrentValue));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
