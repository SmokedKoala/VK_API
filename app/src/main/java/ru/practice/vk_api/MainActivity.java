package ru.practice.vk_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static ru.practice.vk_api.utils.NetworkUtils.generateURL;
import static ru.practice.vk_api.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchButton;
    private TextView resultText;


    class MyAsyncTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }
        @Override
        protected void onPostExecute(String response){
            String firstName = null;
            String lastName = null;
            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray jsonArray = jsonResponse.getJSONArray("response");
                JSONObject userInfo = jsonArray.getJSONObject(0);
                firstName = userInfo.getString("first_name");
                lastName = userInfo.getString("last_name");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            resultText.setText(firstName+" "+lastName);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_for_search:
                        URL generatedURL = generateURL(searchField.getText().toString());
                        new MyAsyncTask().execute(generatedURL);
                        break;
                }
            }
        };

        setContentView(R.layout.activity_main);
        searchField= findViewById(R.id.search_field);
        searchButton = findViewById(R.id.button_for_search);
        resultText = findViewById(R.id.result);
        searchButton.setOnClickListener(onClickListener);

    }

}