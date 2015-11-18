package de.simonmayrshofer.refugeemaps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.simonmayrshofer.refugeemaps.pojos.Hotspot;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ListViewFragment extends Fragment {

    public ListViewFragment() {
    }

    @Bind(R.id.text_view)
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        ButterKnife.bind(this, view);

        Log.d("SIMON", "make call");

        APIManager apiManager = new APIManager();

        apiManager.getHotspots("hamburg")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hotspots -> {
                    String hotspotString = hotspots.get(0).toString();
                    textView.setText(hotspotString);

                    for (Hotspot hotspot : hotspots)
                        Log.d("SIMON", hotspot.getAddress());

                }, throwable -> {
                    Log.d("ERROR", throwable.toString());
                });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
