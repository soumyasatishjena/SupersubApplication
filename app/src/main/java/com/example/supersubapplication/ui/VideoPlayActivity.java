package com.example.supersubapplication.ui;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.supersubapplication.R;
import com.example.supersubapplication.databinding.ActivityVideoPlayBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static com.example.supersubapplication.utility.Utility.YOUTUBE_API_KEY;
import static com.example.supersubapplication.utility.Utility.getVideoId;

public class VideoPlayActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {


    private ActivityVideoPlayBinding binding;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private String url= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Fullscreen);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_play);
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if(intent != null){
            url = intent.getStringExtra("video");
            binding.youtubeView.initialize(YOUTUBE_API_KEY, this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            getYouTubePlayerProvider().initialize(YOUTUBE_API_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtubeView);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        if (!b) {
            String videoId = getVideoId(url);
            youTubePlayer.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener =
            new YouTubePlayer.PlaybackEventListener() {
                @Override
                public void onBuffering(boolean arg0) {
                }
                @Override
                public void onPaused() {
                }
                @Override
                public void onPlaying() {
                }
                @Override
                public void onSeekTo(int arg0) {
                }
                @Override
                public void onStopped() {
                }
            };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener =
            new YouTubePlayer.PlayerStateChangeListener() {
                @Override
                public void onAdStarted() {
                }
                @Override
                public void onError(YouTubePlayer.ErrorReason arg0) {
                }
                @Override
                public void onLoaded(String arg0) {
                }
                @Override
                public void onLoading() {
                }
                @Override
                public void onVideoEnded() {
                }
                @Override
                public void onVideoStarted() {
                }
            };
}