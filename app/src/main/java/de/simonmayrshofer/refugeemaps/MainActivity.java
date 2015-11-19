package de.simonmayrshofer.refugeemaps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    WebViewFragment webViewFragment;
    ListViewFragment listViewFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        webViewFragment = new WebViewFragment();
        listViewFragment = new ListViewFragment();

        replaceFragment(webViewFragment);

        setSupportActionBar(toolbar);
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_content_view, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                replaceFragment(listViewFragment);
                return true;
            case R.id.action_map:
                replaceFragment(webViewFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
