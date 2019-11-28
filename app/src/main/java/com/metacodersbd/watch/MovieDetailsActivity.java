package com.metacodersbd.watch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class MovieDetailsActivity extends AppCompatActivity {

    TextView titleTv, descriptionTv ;
    String  titel , desc , link ;
    PlayerView playerView  ;
    SimpleExoPlayer exoPlayer  ;
    Uri linkUri ;
    boolean fullscreen = false;
    ImageView fullscreenButton ;
    Dialog mFullScreenDialog ;
    private boolean mExoPlayerFullscreen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // nas




        Intent p = getIntent();
        titel =  p.getStringExtra("NAME") ;
        desc = p.getStringExtra("DESC") ;
        link = p.getStringExtra("LINK") ;



        titleTv = findViewById(R.id.movieName) ;
        descriptionTv = findViewById(R.id.details) ;
        playerView = findViewById(R.id.playerView) ;

        fullscreenButton = findViewById(R.id.exo_fullscreen_icon);


        titleTv.setText(titel);
        descriptionTv.setText(desc);

        // init the player

        exoPlayer = ExoPlayerFactory.newSimpleInstance(MovieDetailsActivity.this) ;

        playerView.getResizeMode() ;

        playerView.setPlayer(exoPlayer);

        try {
                 linkUri = Uri.parse(link) ;

            // producer  Datasource   which media  is loaded

            DataSource.Factory dataSourceFactory  = new DefaultDataSourceFactory(MovieDetailsActivity.this ,

                    Util.getUserAgent(this , String.valueOf(R.string.app_name))) ;

            // this is  the progressive
            MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(linkUri) ;


            // prepare the  player

            exoPlayer.prepare(mediaSource) ;


            exoPlayer.setPlayWhenReady(true);


        }
        catch (Exception e )
        {

            // do nothing

            Toast.makeText(getApplicationContext() , "Error " + e.getMessage() , Toast.LENGTH_LONG)
                    .show();
        }


        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mExoPlayerFullscreen)
                {
                   // not in fullscreen

                   openFullScreenDialog()  ;


                }
                else {

                    closeFullScreenDialog() ;

                }


            }
        });





    }

    public  void initFullsceen()
    {

            mFullScreenDialog = new Dialog(MovieDetailsActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            {
                public  void onBackPressed()
                {
                    if(mExoPlayerFullscreen)
                    {
                        closeFullScreenDialog();

                        super.onBackPressed();
                    }

                }


            }  ;



    }


    private void closeFullScreenDialog() {


        ((ViewGroup) playerView.getParent()).removeView(playerView); // removes the player screen


        ((FrameLayout ) findViewById(R.id.parent_relative )).addView(playerView) ;

        mExoPlayerFullscreen = false ;

        mFullScreenDialog.dismiss();


        // change the full screen image
        fullscreenButton.setImageDrawable(ContextCompat.getDrawable(MovieDetailsActivity.this , R.drawable.full));




    }

    private void openFullScreenDialog() {


        // opening the dialgoue

        ((ViewGroup) playerView.getParent()).removeView(playerView); // removes the player screen

        mFullScreenDialog.addContentView(playerView , new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT ));
        // change the full screen image
        fullscreenButton.setImageDrawable(ContextCompat.getDrawable(MovieDetailsActivity.this , R.drawable.full));

        mExoPlayerFullscreen = true ;

        mFullScreenDialog.show();


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if ( exoPlayer != null)
        {
            exoPlayer.stop();
            exoPlayer.release() ;

            finish();

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();



            exoPlayer.stop();
            exoPlayer.release();





    }

    @Override
    protected void onPause() {
        super.onPause();

       exoPlayer.setPlayWhenReady(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initFullsceen();
    }
}
