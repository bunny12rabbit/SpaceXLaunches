package com.BunnyRabbit.SpaceXLaunches;

import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetDataFromAPI.CallBackAsync, RecyclerViewAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "mission_patch";
    public static final String EXTRA_NAME = "mission_name";
    public static final String EXTRA_NUMBER = "flight_number";
    public static final String EXTRA_DATE = "launch_date_utc";
    public static final String EXTRA_ROCKET_NAME = "rocket_name";
    public static final String EXTRA_PAYLOAD_TYPE = "payload_type";
    public static final String EXTRA_PAYLOAD_MASS = "payload_mass_kg";
    public static final String EXTRA_DETAILS = "details";
    public static final String EXTRA_YOUTUBE = "video_link";
    public static final String EXTRA_WIKI = "wikipedia";
    public static final String EXTRA_REDDIT = "reddit_media";
    public static final String EXTRA_ARTICLE = "article_link";

    private static final String TAG = "MainActivity";

    private ArrayList<Launches> mLaunches;
    private RequestQueue mRequestQueue;
    private String nullInfo = "No information";

    //In case of using HttpURLConnection to get JSON data in String for further use
    String responseFromApi = "";

    //JSON, sorted by date, new is upstairs with needed fields
    static public String apiUrl = "https://api.spacexdata.com/v3/launches?sort=launch_date_utc&order=desc&filter=flight_number," +
            "mission_name,launch_date_utc,rocket(rocket_name,second_stage/payloads/(payload_type,payload_mass_kg),details,links)";

    public JSONArray jsonArray;
    static public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressbar);

        mLaunches = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);



        //GetDataFromAPI getDataFromAPI = new GetDataFromAPI(this);
         //getDataFromAPI.execute(apiUrl);

        parseJSON();

    }

    private void parseJSON() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            progressBar.setVisibility(View.VISIBLE);
                            String payload_type = "";
                            int payload_mass_kg = 0;
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject launch = response.getJSONObject(i);

                                int flight_number = launch.optInt("flight_number");
                                String mission_name = launch.optString("mission_name");
                                String launch_date_utc = launch.optString("launch_date_utc");

                                JSONObject rocket = launch.getJSONObject("rocket");
                                String rocket_name = rocket.optString("rocket_name");

                                JSONObject second_stage = rocket.getJSONObject("second_stage");
                                JSONArray payloads = second_stage.getJSONArray("payloads");
                                for (int j = 0; j < payloads.length(); j++) {
                                    JSONObject payload = payloads.getJSONObject(j);
                                    payload_type = payload.optString("payload_type");
                                    payload_mass_kg = payload.optInt("payload_mass_kg");
                                }
                                String details = launch.optString("details");
                                details = details.equals("null") ? nullInfo : details;  // Check if null value presented, then change to "No information..."
                                JSONObject links = launch.getJSONObject("links");
                                String mission_patch = links.optString("mission_patch");
                                String wikipedia = links.optString("wikipedia");
                                wikipedia = wikipedia.equals("null") ? nullInfo : wikipedia;
                                String reddit_media = links.optString("reddit_media");
                                reddit_media = reddit_media.equals("null") ? nullInfo : reddit_media;
                                String article_link = links.optString("article_link");
                                article_link = article_link.equals("null") ? nullInfo : article_link;
                                String video_link = links.optString("video_link");
                                video_link = video_link.equals("null") ? nullInfo : video_link;

                                mLaunches.add(new Launches(mission_name, launch_date_utc, flight_number,mission_patch, rocket_name,
                                        payload_type, payload_mass_kg, details, wikipedia, reddit_media, article_link, video_link));
                            }

                            initRecyclerView(mLaunches);

                            progressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            Log.d(TAG, "onResponse: CRASHED!!!!!!!");
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

//    void initImageBitmaps(){
//        Log.d(TAG, "initImageBitmaps: preparing bitmaps");
//
//        mImageUrls.add("https://images2.imgbox.com/40/e3/GypSkayF_o.png");
//        mNames.add("FalconSat");
//        mLaunch_date_utc.add("2006-03-25");
//        mDetails.add("Engine failure at 33 seconds and loss of vehicle");
//
//        mImageUrls.add("https://images2.imgbox.com/be/e7/iNqsqVYM_o.png");
//        mNames.add("DemoSat");
//        mLaunch_date_utc.add("2007-03-21");
//        mDetails.add("Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine" +
//                " shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage" +
//                "Successful first stage burn and transition to second stage, maximum altitude 289 km, Premature engine shutdown at T+7 min 30 s, Failed to reach orbit, Failed to recover first stage");
//
//        mImageUrls.add("https://images.wallpaperscraft.ru/image/granat_yagody_sladkij_listya_frukt_95817_1920x1080.jpg");
//        mNames.add("FalconSat");
//        mLaunch_date_utc.add("2006-03-25");
//        mDetails.add("Engine failure at 33 seconds and loss of vehicle");
//
//        mImageUrls.add("https://gisher.news/upload/images/af92cb0c98cebbeda841cdffee938ea7.jpg");
//        mNames.add("FalconSat");
//        mLaunch_date_utc.add("2006-03-25");
//        mDetails.add("Engine failure at 33 seconds and loss of vehicle");
//
//        mImageUrls.add("https://avatars.mds.yandex.net/get-pdb/467185/b19ef1ab-da7f-4f32-a3e4-8d7326282ebc/s1200?webp=false");
//        mNames.add("FalconSat");
//        mLaunch_date_utc.add("2006-03-25");
//        mDetails.add("Engine failure at 33 seconds and loss of vehicle");
//
//        mImageUrls.add("https://gisher.news/upload/images/af92cb0c98cebbeda841cdffee938ea7.jpg");
//        mNames.add("FalconSat");
//        mLaunch_date_utc.add("2006-03-25");
//        mDetails.add("Engine failure at 33 seconds and loss of vehicle");
//
//        initRecyclerView();
//    }

    void initRecyclerView(ArrayList<Launches> list){
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        recyclerView.setHasFixedSize(true);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    public void onItemClick(int position) {
        Launches currentItem = mLaunches.get(position);

        Intent detailIntent = new Intent(this, DetailActivity.class);

        detailIntent.putExtra(EXTRA_URL, currentItem.getMission_patch());
        detailIntent.putExtra(EXTRA_NAME, currentItem.getMission_name());
        detailIntent.putExtra(EXTRA_NUMBER, currentItem.getFlight_number());
        detailIntent.putExtra(EXTRA_DATE, currentItem.getLaunch_date_utc());
        detailIntent.putExtra(EXTRA_ROCKET_NAME, currentItem.getRocket_name());
        detailIntent.putExtra(EXTRA_PAYLOAD_TYPE, currentItem.getPayload_type());
        detailIntent.putExtra(EXTRA_PAYLOAD_MASS, currentItem.getPayload_mass_kg());
        detailIntent.putExtra(EXTRA_DETAILS, currentItem.getDetails());
        detailIntent.putExtra(EXTRA_YOUTUBE, currentItem.getVideo_link());
        detailIntent.putExtra(EXTRA_WIKI, currentItem.getWikipedia());
        detailIntent.putExtra(EXTRA_REDDIT, currentItem.getReddit_media());
        detailIntent.putExtra(EXTRA_ARTICLE, currentItem.getArticle_link());

        startActivity(detailIntent);
    }
}
