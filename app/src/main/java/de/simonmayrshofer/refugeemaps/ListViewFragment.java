package de.simonmayrshofer.refugeemaps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.simonmayrshofer.refugeemaps.pojos.Hotspot;
import de.simonmayrshofer.refugeemaps.pojos.Position;
import de.simonmayrshofer.refugeemaps.pojos.Translation;
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

        APIManager apiManager = new APIManager();

        apiManager.getHotspots("hamburg")
                .subscribeOn(Schedulers.io()) // need to run network call on another bg thread
                .doOnNext(hotspots -> saveData(hotspots)) //save data on bg thread
                .observeOn(AndroidSchedulers.mainThread()) // run onSuccess on UI thread
                .subscribe(hotspots1 -> {
                    textView.setText("SUCCESFULLY RETRIEVED " + hotspots1.size() + " HOTSPOTS.");
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

    private void saveData(List<Hotspot> hotspots) {
        for (Hotspot hotspot : hotspots) {
            hotspot.save();
            Position position = hotspot.getPosition();
            position.hotspot = hotspot;
            position.save();
            for (Translation translation : hotspot.getTranslations()) {
                translation.hotspot = hotspot;
                translation.save();
            }
        }
    }

    @OnClick(R.id.button)
    public void onGetHotSpotClick() {
//        Hotspot hotspot = getRandomHotSpot();

        List<Hotspot> hotspotList = getHotspotsForCategory("authorities");

        String result = "";

        for (Hotspot hotspot : hotspotList) {
            try {
                result = result.concat(hotspot.getName()
//                    + ", " + hotspot.getTranslationList().get(0).getText()
                        + ", " + hotspot.getPositionObject().getLat()
                        + ", " + hotspot.getPositionObject().getLng()
                        + ", " + hotspot.getCategory()
                        + "\n\n");
            } catch (Exception e) {
                Toast.makeText(getActivity(), "" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        textView.setText(result);
    }

    private Hotspot getRandomHotSpot() {
        return new Select().from(Hotspot.class).orderBy("RANDOM()").executeSingle();
    }

    private List<Hotspot> getHotspotsForCategory(String category) {
        return new Select()
                .from(Hotspot.class)
                .where("Category = ?", category)
                .execute();
    }
}
