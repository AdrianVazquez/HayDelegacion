package esi.delegacion.haydelegacion;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetItemAdapter extends ArrayAdapter<Tweet> {
	private ArrayList<Tweet> tweets;

	Context context;
	
	public TweetItemAdapter(Context context, int textViewResourceId,
			ArrayList<Tweet> tweets) {
		super(context, textViewResourceId, tweets);
		this.tweets = tweets;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			
			Activity ac = (Activity) context;
			
			LayoutInflater vi = (LayoutInflater) ac.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
