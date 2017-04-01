package com.angelkjoseski.live_results.features.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.angelkjoseski.live_results.R;
import com.angelkjoseski.live_results.features.live.LiveResultsFragment;
import com.angelkjoseski.live_results.features.teams.AllTeamsFragment;
import com.angelkjoseski.live_results.mvp.SportResults;

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
                    break;
                default:
                    break;
            }
            loadContent(fragment);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.football_results_activity);

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
