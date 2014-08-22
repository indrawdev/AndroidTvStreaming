package com.tvstreaming.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

	private ProgressDialog pd;
	private VideoView videoView;

	public String URL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);

		videoView = (VideoView) findViewById(R.id.VideoView);

		pd = new ProgressDialog(VideoActivity.this);
		pd.setTitle("TV Streaming");
		pd.setMessage("Buffering...");
		pd.setIndeterminate(false);
		pd.setCancelable(false);

		pd.show();

		try {
			MediaController mediacontroller = new MediaController(
					VideoActivity.this);
			mediacontroller.setAnchorView(videoView);
			// Get the URL from String VideoURL
			Uri video = Uri.parse(URL);
			videoView.setMediaController(mediacontroller);
			videoView.setVideoURI(video);

		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}

		videoView.requestFocus();
		videoView.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				pd.dismiss();
				videoView.start();

			}
		});

	}

}
