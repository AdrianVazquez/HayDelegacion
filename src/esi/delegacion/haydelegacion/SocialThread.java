package esi.delegacion.haydelegacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SocialThread extends Thread {

	Context context;
	Activity activity;

	TextView updateTimeText;

	float period = 240000;
	float time = System.currentTimeMillis();

	ArrayList<Tweet> tweets = null;

	public SocialThread(Context context) {
		this.context = context;
		this.activity = (Activity) context;
		updateTimeText = (TextView) this.activity.findViewById(R.id.lastupdatetwitter);
		loadTweets();

	}

	@Override
	public void run() {
		if (System.currentTimeMillis() - time >= period) {
			loadTweets();
			time = System.currentTimeMillis();
		}
	}

	public class TweetItemAdapter extends ArrayAdapter<Tweet> {
		private ArrayList<Tweet> tweets;

		public TweetItemAdapter(Context context, int textViewResourceId,
				ArrayList<Tweet> tweets) {
			super(context, textViewResourceId, tweets);
			this.tweets = tweets;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.tweet_layout, null);
			}

			Tweet tweet = tweets.get(position);
			if (tweet != null) {
				TextView username = (TextView) v.findViewById(R.id.username);
				TextView message = (TextView) v.findViewById(R.id.message);
				ImageView image = (ImageView) v.findViewById(R.id.avatar);

				if (username != null) {
					username.setText(tweet.username);
				}

				if (message != null) {
					message.setText(tweet.message);
				}

				if (image != null) {
					image.setImageBitmap(Tweet.getBitmap(tweet.image_url));
				}
			}
			return v;
		}
	}

	@SuppressLint("SimpleDateFormat")
	public void loadTweets() {
		try {
			tweets = Tweet.getTweets("esiHD", 1);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		ListView listView = (ListView) activity.findViewById(R.id.TweetList);
		listView.setAdapter(new TweetItemAdapter(activity,
				R.layout.tweet_layout, tweets));

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = df.format(c.getTime());

		updateTimeText.setText("Última actualización: " + formattedDate);
	}

}
