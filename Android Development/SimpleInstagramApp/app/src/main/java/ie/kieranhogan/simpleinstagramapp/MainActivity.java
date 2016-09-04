package ie.kieranhogan.simpleinstagramapp;

/**
 * Created by Kieran on 31/08/2016.
 * <p/>
 * Class handles main menu with button for login activity
 */

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ie.kieranhogan.simpleinstagramapp.br.com.dina.oauth.instagram.InstagramApp;

public class MainActivity extends AppCompatActivity {

    public static InstagramApp instagramApp;
    public static JSONObject jsonObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login_btn = (Button) findViewById(R.id.login_with_instagram);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        TextView link = (TextView) findViewById(R.id.code_link);
        link.setClickable(true);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://github.com/kieranhogan13/Java/tree/master/Android%20Development/SimpleInstagramApp'>Link to app on GitHub</a>";
        link.setText(Html.fromHtml(text));
    }
}