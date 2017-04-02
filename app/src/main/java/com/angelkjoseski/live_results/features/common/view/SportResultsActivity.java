package com.angelkjoseski.live_results.features.common.view;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.features.live.view.LiveResultsFragment;
import com.angelkjoseski.live_results.features.teams.view.AllTeamsFragment;
import com.angelkjoseski.live_results.features.upcomingfixtures.view.UpcomingFixturesFragment;
import com.angelkjoseski.live_results.features.common.SportResults;

public class SportResultsActivity extends AppCompatActivity implements SportResults.View {

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_teams:
                    fragment = new AllTeamsFragment();
                    break;
                case R.id.navigation_live_results:
                    fragment = new LiveResultsFragment();
                    break;
                case R.id.navigation_fixtures:
                    fragment = new UpcomingFixturesFragment();
                    break;
                default:
                    break;
            }
            loadContent(fragment);
            return true;
        }
    };

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.football_results_activity);

        progressDialog = ProgressDialog.show(this, null, null);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        loadContent(new AllTeamsFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Will load the given fragment in the main content area.
     * @param fragment The fragment to show.
     */
    private void loadContent(Fragment fragment) {
        if (fragment == null) {
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commitNow();
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.hide();
        }
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
