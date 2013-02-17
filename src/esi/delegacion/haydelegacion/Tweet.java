package esi.delegacion.haydelegacion;

import java.net.URL;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Tweet {
	public String username;
	public String message;
	public String image_url;

	public Tweet(String username, String message, String url) {
		this.username = username;
		this.message = message;
		this.image_url = url;
	}

	public static ArrayList<Tweet> getTweets(String searchTerm, int page) throws JSONException {
		String searchUrl = "http://search.twitter.com/search.json?q=@"
				+ searchTerm + "&rpp=100&page=" + page;

		ArrayList<Tweet> tweets = new ArrayList<Tweet>();

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(searchUrl);

		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		String responseBody = null;
		try {
			responseBody = client.execute(get, responseHandler);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject(responseBody);
		
		JSONArray arr = null;

		try {
			Object j = jsonObject.get("results");
			arr = (JSONArray) j;
		} catch (Exception ex) {
			Log.v("TEST", "Exception: " + ex.getMessage());
		}

		for ( int i=0 ; i<arr.length() ; i++ ) {
			Tweet tweet = new Tweet(arr.getJSONObject(i).get("from_user")
					.toString(), arr.getJSONObject(i).get("text").toString(),
					arr.getJSONObject(i).get("profile_image_url").toString());
			tweets.add(tweet);
		}

		return tweets;
	}

	public static Bitmap getBitmap(String bitmapUrl) {
		try {
			URL url = new URL(bitmapUrl);
			return BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());
		} catch (Exception ex) {
			return null;
		}
	}
}
