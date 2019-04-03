package com.BunnyRabbit.SpaceXLaunches;

import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class GetDataFromAPI extends AsyncTask<String, Void, String> {

    public interface CallBackAsync
    {
        void DoInPreExecute();
        void DoInPostExecute(String someResult);
    }

    private CallBackAsync _callBackAsync;

    public GetDataFromAPI(final CallBackAsync callBackAsync){
        this._callBackAsync = callBackAsync;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (_callBackAsync != null)
        {
            _callBackAsync.DoInPreExecute();
        }

    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() == 200){
                InputStream responseBody = connection.getInputStream();
                String result = IOUtils.toString(responseBody, "UTF-8");
                IOUtils.closeQuietly(responseBody);
                return result;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (_callBackAsync != null) {
            _callBackAsync.DoInPostExecute(result);
        }
    }
}
