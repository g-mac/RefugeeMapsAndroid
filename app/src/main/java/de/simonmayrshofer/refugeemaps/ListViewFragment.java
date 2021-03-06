package de.simonmayrshofer.refugeemaps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
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
    @Bind(R.id.spinner)
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        ButterKnife.bind(this, view);

        String[] categories = {"religion", "sport", "authorities"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        APIManager apiManager = new APIManager();
        apiManager.getHotspots("hamburg")
                .subscribeOn(Schedulers.io()) // need to run network call on another bg thread
                .doOnNext(hotspots -> saveData(hotspots)) //save data on bg thread
                .observeOn(AndroidSchedulers.mainThread()) // run onSuccess on UI thread
                .subscribe(hotspots1 -> {
                    Toast.makeText(getActivity(), "SUCCESFULLY RETRIEVED " + hotspots1.size() + " HOTSPOTS.", Toast.LENGTH_SHORT).show();
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

    private void saveData(List<Hotspot> hotspots) {

        new Delete().from(Hotspot.class).execute(); // delete all existing records

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

        String category = spinner.getSelectedItem().toString();

        List<Hotspot> hotspotList = getHotspotsForCategory(category);

        String result = "";

        int language = 0;

        for (Hotspot hotspot : hotspotList) {
            try {
                result += "< " + hotspot.getName() + " >";
                if (hotspot.getTranslationList().size() > 0)
                    if (!TextUtils.isEmpty(hotspot.getTranslationList().get(language).getText()))
                        result += " \n" + hotspot.getTranslationList().get(language).getText();
                result += " \n" + hotspot.getAddress();
                result += "\n\n";
            } catch (Exception e) {
                Toast.makeText(getActivity(), "" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        textView.setText(result);
    }

    //--- DB Queries -------------------------------------------------------------------------------

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
