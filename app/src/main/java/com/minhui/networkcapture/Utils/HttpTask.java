package com.minhui.networkcapture.Utils;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.Keep;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Keep
public class HttpTask
{
    private String apiKey;
    private Callback callback;

    public HttpTask(String apiKey, Callback callback)
    {
        this.apiKey = apiKey;
        this.callback = callback;
    }

    public void sendPostRequest(String url, String jsonPayload, String type)
    {
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(type);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("API-Key", apiKey);
            connection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonPayload);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null)
                {
                    response.append(line);
                }

                reader.close();
                callback.onSuccess(response.toString());
            }
            else
            {
                callback.onFailure("POST request failed");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            callback.onFailure("Error occurred during POST request: " + e.getMessage());
        }
    }

    public interface Callback
    {
        void onSuccess(String response);
        void onFailure(String errorMessage);
    }
}