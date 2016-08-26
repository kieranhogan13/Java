package ie.kieranhogan.dayvinrosssoundboard;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    MediaPlayer a_theme, b_roundbrush, c_time, d_welcome, e_bother,
            f_brush, g_nomistakes, h_colours, i_noise1, j_whack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer intro = MediaPlayer.create(getApplicationContext(), R.raw.b_roundbrush);
        intro.start();

        a_theme = MediaPlayer.create(this, R.raw.a_theme);
        b_roundbrush = MediaPlayer.create(this, R.raw.b_roundbrush);
        c_time = MediaPlayer.create(this, R.raw.c_time);
        d_welcome = MediaPlayer.create(this, R.raw.d_welcome);
        e_bother = MediaPlayer.create(this, R.raw.e_bother);
        f_brush = MediaPlayer.create(this, R.raw.f_brush);
        g_nomistakes = MediaPlayer.create(this, R.raw.g_nomistakes);
        h_colours = MediaPlayer.create(this, R.raw.h_colours);
        i_noise1 = MediaPlayer.create(this, R.raw.i_noise1);
        j_whack = MediaPlayer.create(this, R.raw.j_whack);
    }


    public void a_theme(View view) {
        if (a_theme.isPlaying()){
            a_theme.seekTo(0); }
        else {
            a_theme.start();
        }
    }
    public void b_roundbrush(View view) {
        if (b_roundbrush.isPlaying()){
            b_roundbrush.seekTo(0); }
        else {
            b_roundbrush.start();
        }
    }
    public void c_time(View view) {
        if (c_time.isPlaying()){
            c_time.seekTo(0); }
        else {
            c_time.start();
        }
    }
    public void d_welcome(View view) {
        if (d_welcome.isPlaying()){
            d_welcome.seekTo(0); }
        else {
            d_welcome.start();
        }
    }
    public void e_bother(View view) {
        if (e_bother.isPlaying()){
            e_bother.seekTo(0); }
        else {
            e_bother.start();
        }
    }
    public void f_brush(View view) {
        if (f_brush.isPlaying()){
            f_brush.seekTo(0); }
        else {
            f_brush.start();
        }
    }
    public void g_nomistakes(View view) {
        if (g_nomistakes.isPlaying()){
            g_nomistakes.seekTo(0); }
        else {
            g_nomistakes.start();
        }
    }
    public void h_colours(View view) {
        if (h_colours.isPlaying()){
            h_colours.seekTo(0); }
        else {
            h_colours.start();
        }
    }
    public void i_noise1(View view) {
        if (i_noise1.isPlaying()){
            i_noise1.seekTo(0); }
        else {
            i_noise1.start();
        }      }
    public void j_whack(View view) {
        if (j_whack.isPlaying()){
            j_whack.seekTo(0); }
        else {
            j_whack.start();
        }
    }

}
