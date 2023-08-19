package com.minhui.networkcapture.RadarActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.minhui.networkcapture.R;
import com.minhui.networkcapture.Utils.Api;
import com.minhui.networkcapture.Utils.HttpTask;
import com.minhui.networkcapture.Utils.LoginDataJSON;
import com.minhui.networkcapture.Utils.LoginInfoJSON;
import com.minhui.networkcapture.VPNCaptureActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

@Keep
public class LoginActivity extends AppCompatActivity {

    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private CheckBox savePasswordCheckBox;

    TextView errorTextView;

    TextView linkSite;
    TextView linkDiscord;

    TextView sendingLog;


    private Button loginButton;

    Gson gson = new Gson();

    @Override
    public void onBackPressed() {

        finishAffinity(); // Close all activities
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar));
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        setContentView(R.layout.activity_login);

        String userPassword = sharedPreferences.getString("password",null);
        String userName = sharedPreferences.getString("username",null);
        boolean isPasswordSaved = sharedPreferences.getBoolean("isPasswordSaved",false);

        // initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        savePasswordCheckBox = findViewById(R.id.save_password_checkbox);
        errorTextView = findViewById(R.id.loginErrorTextView);


        linkSite = findViewById(R.id.linkSite);
        linkDiscord = findViewById(R.id.linkDiscord);

        String linkTextSite= " Our site -> https://albioncheat.online/ " ;
        String linkTextDiscord = "Our discord -> https://discord.com/invite/QrkW8yFKcUd ";
        linkDiscord.setText(linkTextDiscord);

        linkSite.setText(linkTextSite);





        linkSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://albioncheat.online/"));
                startActivity(browserIntent);
            }
        });


        linkDiscord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.com/invite/QrkW8yFKcU"));
                startActivity(browserIntent);
            }
        });



        savePasswordCheckBox.setChecked(isPasswordSaved);
        loginButton = findViewById(R.id.login_button);

        if(isPasswordSaved)
        {

            if(userPassword!=null)
            {
                passwordEditText.setText(userPassword);
            }
            if(userName!=null)
            {
                usernameEditText.setText(userName);
            }
        }






        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                boolean savePassword = savePasswordCheckBox.isChecked();
                editor.putBoolean("isPasswordSaved",savePassword);
                if(savePassword)
                {

                    editor.putString("username",username);
                    editor.putString("password",password);

                }
                editor.apply();

                LoginDataJSON loginDataJSON = new LoginDataJSON();

                loginDataJSON.name  = username;
                loginDataJSON.password = password;




                String loginJSON = gson.toJson(loginDataJSON);


                System.out.println("loginJSON: " + loginJSON);

                String apiKey = "LKM2Dv71i0lEny3leCf64pLu53H4C4iC851vzCDx17q6wBoGy69c790Dxn3wbAU4y0fwFTfCDmUkQkj2hY0ORGza7dl81ijWHP0925MItNNkJ2wA7ps8n0HWuTbxz777v4aMkDXw52h9gv71U05IUV";






                HttpTask.Callback callback = new HttpTask.Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(String response) {


                        LoginInfoJSON loginInfo = gson.fromJson(response, LoginInfoJSON.class);




                        OkHttpClient client = new OkHttpClient();

                        String url = "http://worldtimeapi.org/api/timezone/Etc/GMT";  // Replace with your URL

                        Request request = new Request.Builder()
                                .url(url)
                                .build();

                        String myResponse = null;
                        try {
                            Response response1 = client.newCall(request).execute();
                            if (response1.isSuccessful()) {
                                 myResponse = response1.body().string();
                                // Do something with this data
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        if(myResponse==null)
                        {
                            errorTextView.setText("request error click again ");
                            return;
                        }



                        String datetimeString = myResponse.split("\"datetime\":\"")[1].split("\"")[0];
                        datetimeString = datetimeString.replace("+00:00", "Z");

                        Instant instant = Instant.parse(datetimeString);

                        Long currentTimeStamp  =instant.toEpochMilli() / 1000;







                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(currentTimeStamp -loginInfo.logindate >=30)
                                {

                                    Intent intent = new Intent(LoginActivity.this, VPNCaptureActivity.class);

                                    // Add any extra data to the Intent if needed
                                    intent.putExtra("logged", "true");
                                    intent.putExtra("login", username);
                                    intent.putExtra("password", password);

                                    intent.putExtra("days", loginInfo.currentdays);
                                    // Start the new activity
                                    startActivity(intent);

                                }
                                else
                                {

                                    int temp = (int) (currentTimeStamp -loginInfo.logindate);
                                    temp = 30 - temp;

                                    errorTextView.setText("Login time out wait :" + temp +"s" );
                                }





                            }
                        });


                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        // Handle the failure/error
                        errorTextView.setText("Login error invalid username or password");
                      //  System.out.println("Error: " + errorMessage);
                    }
                };

                HttpTask postTask = new HttpTask(apiKey, callback);
                String type = "POST";

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                          //      String log =  Api.getUserInfo;
                         //       log += loginJSON;
                         //       log += type;
                        //        sendingLog.setText(log);
                            }
                        });

                        postTask.sendPostRequest(Api.getUserInfo, loginJSON , type);
                    }
                });
                thread.start();


            }
        });
    }
}