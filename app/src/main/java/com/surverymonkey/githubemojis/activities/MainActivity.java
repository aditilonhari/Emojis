package com.surverymonkey.githubemojis.activities;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.surverymonkey.githubemojis.R;
import com.surverymonkey.githubemojis.adapters.EmojiAdapter;
import com.surverymonkey.githubemojis.models.Emoji;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvEmojis;
    private EmojiAdapter mAdapter;
    private List<Emoji> emojis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.app_title);
        Spannable text = new SpannableString(getSupportActionBar().getTitle());
        text.setSpan(new ForegroundColorSpan(Color.BLACK), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(text);

        // Find RecyclerView and bind to adapter
        rvEmojis = (RecyclerView) findViewById(R.id.rvEmojis);

        // allows for optimizations
        rvEmojis.setHasFixedSize(true);

        // Define 5 column grid layout
        final GridLayoutManager layout = new GridLayoutManager(MainActivity.this, 5);
        rvEmojis.setLayoutManager(layout);
        emojis = new ArrayList<>();
        fetchEmojis();

        // Create an adapter
        mAdapter = new EmojiAdapter(MainActivity.this, emojis);

        // Bind adapter to list
        rvEmojis.setAdapter(mAdapter);
    }

    public void fetchEmojis() {
        String url = "https://api.github.com/emojis";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", "aditilonhari");
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                emojis.addAll(Emoji.getAllEmojis(response));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if (!isNetworkAvailable())
                    Snackbar.make(findViewById(R.id.rvEmojis), "Network connectivity lost!", Snackbar.LENGTH_INDEFINITE)
                            .show();

                if (!isOnline())
                    Snackbar.make(findViewById(R.id.rvEmojis), "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                            .show();

            }

        });
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private boolean isOnline(){
        Runtime runtime = Runtime.getRuntime();

        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {e.printStackTrace();}
        return false;
    }
}
