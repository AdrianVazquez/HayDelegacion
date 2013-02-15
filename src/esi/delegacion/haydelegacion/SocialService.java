package esi.delegacion.haydelegacion;

import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.ListView;

public class SocialService extends Service {

	private Handler mHandler = new Handler();
	private Activity contexto = (Activity)getApplicationContext();
	private ListView tweetList = (ListView) contexto.findViewById(R.id.ListViewId);
	
	ArrayList<Tweet> tweets = null;

	public static final int MINUTES = 240000;

	private Runnable periodicTask = new Runnable() {
		public void run() {
			Log.d("SERVICE", "Ejecutando");
			loadTweets();
			tweetList.setAdapter(new TweetItemAdapter(contexto,
					R.layout.tweet_layout, tweets));
			mHandler.postDelayed(periodicTask, MINUTES);
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.d("SERVICE", "Creado");
		mHandler.postDelayed(periodicTask, 5000);
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacks(periodicTask);
	}

	public void loadTweets() {
		try {
			tweets = Tweet.getTweets("esiHD", 1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
