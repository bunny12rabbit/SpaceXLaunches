package com.BunnyRabbit.SpaceXLaunches;

public class Launches {
    private String mission_patch, mission_name, launch_date_utc, rocket_name, payload_type, details, wikipedia, reddit_media, article_link, video_link;
    private int flight_number, payload_mass_kg;

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public String getLaunch_date_utc() {
        return launch_date_utc;
    }

    public void setLaunch_date_utc(String launch_date_utc) {
        this.launch_date_utc = launch_date_utc;
    }

    public int getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(int flight_number) {
        this.flight_number = flight_number;
    }

    public String getMission_patch() {
        return mission_patch;
    }

    public void setMission_patch(String mission_patch) {
        this.mission_patch = mission_patch;
    }

    public String getRocket_name() {
        return rocket_name;
    }

    public void setRocket_name(String rocket_name) {
        this.rocket_name = rocket_name;
    }

    public String getPayload_type() {
        return payload_type;
    }

    public void setPayload_type(String payload_type) {
        this.payload_type = payload_type;
    }

    public int getPayload_mass_kg() {
        return payload_mass_kg;
    }

    public void setPayload_mass_kg(int payload_mass_kg) {
        this.payload_mass_kg = payload_mass_kg;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getReddit_media() {
        return reddit_media;
    }

    public void setReddit_media(String reddit_media) {
        this.reddit_media = reddit_media;
    }

    public String getArticle_link() {
        return article_link;
    }

    public void setArticle_link(String article_link) {
        this.article_link = article_link;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    public Launches(String mission_name, String launch_date_utc, int flight_number, String mission_patch, String rocket_name,
                    String payload_type, int payload_mass_kg, String details, String wikipedia, String reddit_media,
                    String article_link, String video_link) {
        this.mission_name = mission_name;
        this.launch_date_utc = launch_date_utc;
        this.flight_number = flight_number;
        this.mission_patch = mission_patch;
        this.rocket_name = rocket_name;
        this.payload_type = payload_type;
        this.payload_mass_kg = payload_mass_kg;
        this.details = details;
        this.wikipedia = wikipedia;
        this.reddit_media = reddit_media;
        this.article_link = article_link;
        this.video_link = video_link;
    }

}
