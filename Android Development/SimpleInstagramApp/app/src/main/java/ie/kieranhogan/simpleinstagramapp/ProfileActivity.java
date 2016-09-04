package ie.kieranhogan.simpleinstagramapp;

/**
 * Created by Kieran on 01/09/2016.
 *
 * Class populates screen with profile information and pictures by using InstagramApp class
 *
 */

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    List<Image> imageList = new ArrayList<>();
    String userImageUrl, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button login_btn = (Button) findViewById(R.id.logout);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.instagramApp.resetAccessToken();
                Intent mIntent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
            }
        });

        try {
            initList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.user_image_list);
        MediaImageAdapter mediaImageAdapter = new MediaImageAdapter();
        listView.setAdapter(mediaImageAdapter);
        TextView username_text;
        ImageView iv_profile_image;
        username_text = (TextView) findViewById(R.id.username);
        username_text.setText("Username: " + username);
//        tv_bio = (TextView) findViewById(R.id.tv_bio);
//        tv_bio.setText("Bio: " + bio);
        iv_profile_image = (ImageView) findViewById(R.id.users_profile);
        Picasso.with(ProfileActivity.this).load(userImageUrl).into(iv_profile_image);
    }

    public void initList() throws JSONException {
        JSONArray wholeData = MainActivity.jsonObject.getJSONArray("data");

        for (int i = 0; i < wholeData.length(); i++) {
            JSONObject json = wholeData.getJSONObject(i);
            String ImageUrl = json.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
            userImageUrl = json.getJSONObject("user").getString("profile_picture");
            username = json.getJSONObject("user").getString("username");
            //bio = data_line.getJSONObject("bio").getString("bio");
            imageList.add(new Image(ImageUrl));
        }
    }

    class ViewHolder {
        ImageView users_images;
    }

    class Image {
        public String ImageUrl;

        Image(String imageUrl) {
            this.ImageUrl = imageUrl;
        }
    }

    public class MediaImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public Object getItem(int position) {
            return imageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder = new ViewHolder();
            View my_view;

            if (convertView == null) {
                my_view = View.inflate(getApplicationContext(), R.layout.activity_profile, null);


                mViewHolder = new ViewHolder();
            } else
                my_view = convertView;

            Button logout_btn = (Button) my_view.findViewById(R.id.logout);
            logout_btn.setVisibility(View.GONE);
            TextView username_text = (TextView) my_view.findViewById(R.id.username);
            username_text.setVisibility(View.GONE);
            TextView bio_text = (TextView) my_view.findViewById(R.id.bio);
            bio_text.setVisibility(View.GONE);
            TextView website_text = (TextView) my_view.findViewById(R.id.website);
            website_text.setVisibility(View.GONE);

            mViewHolder.users_images = (ImageView) my_view.findViewById(R.id.users_images);
            Picasso.with(ProfileActivity.this).load(imageList.get(position).ImageUrl).into(mViewHolder.users_images);

            return my_view;
        }
    }
}

