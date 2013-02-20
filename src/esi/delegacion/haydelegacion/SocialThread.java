package esi.delegacion.haydelegacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ListView;
import android.widget.TextView;

public class SocialThread extends Thread {

	Context context;
	Activity activity;

	TextView updateTimeText;

	float period = 240000;
	float time = System.currentTimeMillis();

	boolean firstTime = true;
	
	ArrayList<Tweet> tweets = null;

	public SocialThread(Context context) {
		this.context = context;
		this.activity = (Activity) context;
		updateTimeText = (TextView) this.activity
				.findViewById(R.id.lastupdatetwitter);
	}

	@Override
	public void run() {
		if(firstTime){
			loadTweets();
			firstTime = false;
		}
		if (System.currentTimeMillis() - time >= period) {
			loadTweets();
			time = System.currentTimeMillis();
		}
	}

	
	@SuppressLint("SimpleDateFormat")
	public void loadTweets() {
		if (isOnline()) {
			try {
				tweets = Tweet.getTweets("from:esiHD", 1);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				DialogHelper.noConnectionDialog(context).show();
			}

			if (tweets != null) {
				ListView listView = (ListView) activity
						.findViewById(R.id.TweetList);
				listView.setScrollingCacheEnabled(false);
				listView.setAdapter(new TweetItemAdapter(activity,
						R.layout.tweet_layout, tweets));
			}
		} else
			DialogHelper.noConnectionDialog(context).show();

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = df.format(c.getTime());

		updateTimeText.setText("Última actualización: " + formattedDate);

	}

	public boolean isOnline() {

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;

	}
}
