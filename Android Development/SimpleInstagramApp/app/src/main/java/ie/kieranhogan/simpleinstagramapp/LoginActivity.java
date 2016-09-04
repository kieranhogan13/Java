package ie.kieranhogan.simpleinstagramapp;

/**
 * Created by Kieran on 31/08/2016.
 * <p/>
 * Class handles login by retrieving api id and secret from ApplicationData class and creates an instance of InstagramApp object using packaged class
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;

import ie.kieranhogan.simpleinstagramapp.br.com.dina.oauth.instagram.InstagramApp;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.instagramApp = new InstagramApp(this,
                ApplicationData.CLIENT_ID,
                ApplicationData.CLIENT_SECRET,
                ApplicationData.REDIRECT_URI);

        MainActivity.instagramApp.setListener(new InstagramApp.OAuthAuthenticationListener() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                finish();
            }

            @Override
            public void onFail(String error) {
                Log.w(error, ": login failed");
            }
        });

        if (MainActivity.instagramApp.hasAccessToken())
            MainActivity.instagramApp.fetchUserName();
        else
            MainActivity.instagramApp.authorize();
    }
}
