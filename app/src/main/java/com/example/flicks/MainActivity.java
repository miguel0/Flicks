package com.example.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    // CONSTANTS
    // moviedb base url
    public final static String API_BASE_URL = "https://api.themoviedb.org/3";
    // parameter name for api key
    public static final String API_KEY_PARAM = "api_key";
    // tag for logs from this activity
    public static final String TAG = "MainActivity";

    // INSTANCE FIELDS
    AsyncHttpClient client;
    String imageBaseUrl;
    String posterSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new AsyncHttpClient();
        getConfiguration();
    }

    // get the config from the api
    private void getConfiguration() {
        String url = API_BASE_URL + "/configuration";
        RequestParams params = new RequestParams();
        params.put(API_KEY_PARAM, getString(R.string.api_key);

        // GET request to get json object
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject images = response.getJSONObject("images");
                    // get image base url
                    imageBaseUrl = images.getString("secure_base_url");
                    // get poster size
                    JSONArray posterSizeOptions = images.getJSONArray("poster_sizes");
                    posterSize = posterSizeOptions.optString(3, "w342");
                } catch (JSONException e) {
                    logError("Failed while parsing configuration.", e, true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("Failed getting configuration.", throwable, true);
            }
        });
    }

    // handle errors and notify the user
    private void logError(String message, Throwable error, Boolean alertUser) {
        Log.e(TAG, message, error);
        // notify user
        if (alertUser) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}
