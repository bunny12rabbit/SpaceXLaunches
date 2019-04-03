package com.BunnyRabbit.SpaceXLaunches;

import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetDataFromAPI.CallBackAsync {

    private static final String TAG = "MainActivity";

    ArrayList<String> mNames = new ArrayList<>();
    ArrayList<String> mImageUrls = new ArrayList<>();
    ArrayList<String> mLaunch_date_utc = new ArrayList<>();
    ArrayList<String> mDetails = new ArrayList<>();

    String responseFromApi = "";
    String apiUrl = "https://api.spacexdata.com/v3/launches";

    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        progressBar = findViewById(R.id.progressbar);

        GetDataFromAPI getDataFromAPI = new GetDataFromAPI(this);
         getDataFromAPI.execute(apiUrl);

        //initImageBitmaps();

    }

    /*void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mImageUrls.add("https://images2.imgbox.com/40/e3/GypSkayF_o.png");
        mNames.add("FalconSat");
        mLaunch_date_utc.add("2006-03-25");
        mDetails.add("Engine failure at 33 seconds and loss of vehicle");

        mImageUrls.add("https://images2.imgbox.com/be/e7/iNqsqVYM_o.png");
        mNames.add("DemoSat");
        mLaunch_date_utc.add("2007-03-21");
        mDetails.add("Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine" +
                " shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage" +
                "Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage");

        mImageUrls.add("https://images.wallpaperscraft.ru/image/granat_yagody_sladkij_listya_frukt_95817_1920x1080.jpg");
        mNames.add("FalconSat");
        mLaunch_date_utc.add("2006-03-25");
        mDetails.add("Engine failure at 33 seconds and loss of vehicle");

        mImageUrls.add("https://gisher.news/upload/images/af92cb0c98cebbeda841cdffee938ea7.jpg");
        mNames.add("FalconSat");
        mLaunch_date_utc.add("2006-03-25");
        mDetails.add("Engine failure at 33 seconds and loss of vehicle");

        mImageUrls.add("https://avatars.mds.yandex.net/get-pdb/467185/b19ef1ab-da7f-4f32-a3e4-8d7326282ebc/s1200?webp=false");
        mNames.add("FalconSat");
        mLaunch_date_utc.add("2006-03-25");
        mDetails.add("Engine failure at 33 seconds and loss of vehicle");

        mImageUrls.add("https://gisher.news/upload/images/af92cb0c98cebbeda841cdffee938ea7.jpg");
        mNames.add("FalconSat");
        mLaunch_date_utc.add("2006-03-25");
        mDetails.add("Engine failure at 33 seconds and loss of vehicle");

        initRecyclerView();
    }*/

    void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mImageUrls, mNames, mLaunch_date_utc, mDetails);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void JsonParse(){
        
    }

    @Override
    public void DoInPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
        try {
            HttpResponseCache myCache = HttpResponseCache.install(
                    getCacheDir(), 100000L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DoInPostExecute(String someResult) {
        progressBar.setVisibility(View.GONE);
        responseFromApi = someResult;
    }
}
