package com.angelkjoseski.live_results.features.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.mvp.SportResults;

public class SportResultsActivity extends AppCompatActivity implements SportResults.View {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_teams:
                    mTextMessage.setText(R.string.title_all_teams);
                    return true;
                case R.id.navigation_live_results:
                    mTextMessage.setText(R.string.title_live_results);
                    return true;
                case R.id.navigation_fixtures:
                    mTextMessage.setText(R.string.title_fixtures);
                    return true;
                default:
                    mTextMessage.setText("Default");
                    return false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.football_results_activity);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void showLoading(boolean show) {
        // TODO: Implement loading spinner.
    }

    @Override
    public void showMessage(String message, String description) {
        new AlertDialog.Builder(this)
                .setTitle(message)
                .setMessage(description)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }
}
