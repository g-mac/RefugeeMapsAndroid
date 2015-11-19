package de.simonmayrshofer.refugeemaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebViewFragment webViewFragment = new WebViewFragment();
        ListViewFragment listViewFragment = new ListViewFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_content_view, listViewFragment)
                .commit();
    }

}
