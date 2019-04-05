package com.BunnyRabbit.SpaceXLaunches;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_ARTICLE;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_DATE;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_DETAILS;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_NAME;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_NUMBER;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_PAYLOAD_MASS;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_PAYLOAD_TYPE;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_REDDIT;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_ROCKET_NAME;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_URL;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_WIKI;
import static com.BunnyRabbit.SpaceXLaunches.MainActivity.EXTRA_YOUTUBE;

public class DetailActivity extends AppCompatActivity {

    ImageView mission_patch;
    TextView mission_name;
    TextView flight_number;
    TextView launch_date_utc;
    TextView rocket_name;
    TextView payload_type;
    TextView payload_mass_kg;
    TextView details;
    TextView video_link;
    TextView wikipedia;
    TextView reddit_media;
    TextView article_link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Getting Intent EXTRA data about fields
        Bundle intent = getIntent().getExtras();

        mission_patch = findViewById(R.id.image_detailed_mission_patch);
        mission_name = findViewById(R.id.text_view_detailed_name);
        flight_number = findViewById(R.id.text_view_detailed_flight_number);
        launch_date_utc = findViewById(R.id.text_view_detailed_date);
        rocket_name = findViewById(R.id.text_view_detailed_rocket_name);
        payload_type = findViewById(R.id.text_view_detailed_payload_type);
        payload_mass_kg = findViewById(R.id.text_view_detailed_payload_mass_kg);
        details = findViewById(R.id.text_view_detailed_details);
        video_link = findViewById(R.id.text_view_detailed_video_link);
        wikipedia = findViewById(R.id.text_view_detailed_wiki);
        reddit_media = findViewById(R.id.text_view_detailed_reddit_media);
        article_link = findViewById(R.id.text_view_detailed_article_link);

        // Setting intent EXTRA data intro views

        //  Check if image presents, if no set mock
        if (intent.getString(EXTRA_URL).equals("null")){
            mission_patch.setImageResource(R.drawable.no_img);
        } else {
            Glide.with(this)
                    .asBitmap()
                    .load(intent.getString(EXTRA_URL))
                    .into(mission_patch);
        }
        mission_name.setText(intent.getString(EXTRA_NAME));
        flight_number.setText("Flight number: " + intent.getInt(EXTRA_NUMBER, 0));
        launch_date_utc.setText(intent.getString(EXTRA_DATE).substring(0, 10));
        rocket_name.setText("Rocket name: " + intent.getString(EXTRA_ROCKET_NAME));
        payload_type.setText(intent.getString(EXTRA_PAYLOAD_TYPE));
        payload_mass_kg.setText(intent.getInt(EXTRA_PAYLOAD_MASS, 0) + "kg");
        details.setText("Details:\n\n" + intent.getString(EXTRA_DETAILS));
        video_link.setText("YouTube: " + intent.getString(EXTRA_YOUTUBE));
        wikipedia.setText("Wikipedia: " + intent.getString(EXTRA_WIKI));
        reddit_media.setText("Reddit: " + intent.getString(EXTRA_REDDIT));
        article_link.setText("Article: " + intent.getString(EXTRA_ARTICLE));

    }
}
