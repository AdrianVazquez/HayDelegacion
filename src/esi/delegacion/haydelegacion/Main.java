package esi.delegacion.haydelegacion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.ViewFlipper;

public class Main extends Activity {

	float init_x;

	Context context;

	ViewFlipper flipper;
	FrameLayout page1;
	ScrollView page2, page3;
	LinearLayout page4, page6, page5;
	ListView tweetlist;
	EditText mailBody;
	ImageView toTwitter, toFacebook, toTuenti;
	Button sendButton;
	Button updateButton;

	SocialThread socialThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = this;

		socialThread = new SocialThread(this);

		flipper = (ViewFlipper) findViewById(R.id.mainFlipper);
		page1 = (FrameLayout) findViewById(R.id.page1);
		page2 = (ScrollView) findViewById(R.id.page2);
		page3 = (ScrollView) findViewById(R.id.page3);
		page4 = (LinearLayout) findViewById(R.id.page4);
		page5 = (LinearLayout) findViewById(R.id.page5);
		page6 = (LinearLayout) findViewById(R.id.page6);

		tweetlist = (ListView) findViewById(R.id.TweetList);

		sendButton = (Button) findViewById(R.id.enviar);
		updateButton = (Button) findViewById(R.id.updatebuttontwitter);
		toTwitter = (ImageView) findViewById(R.id.totwitter);
		toFacebook = (ImageView) findViewById(R.id.toface);
		toTuenti = (ImageView) findViewById(R.id.toTuenti);

		mailBody = (EditText) findViewById(R.id.mailbody);

		page1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {

				flip(event);
				return false;
			}
		});

		page2.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {

				flip2(event);
				return false;
			}
		});

		page3.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {

				flip2(event);
				return false;
			}
		});

		page4.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View view, MotionEvent event) {

				flip(event);
				return false;
			}
		});

		page5.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				flip(event);
				return false;
			}
		});

		page6.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				flip(event);
				return false;
			}
		});

		tweetlist.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				flip2(event);
				return false;
			}
		});

		toTwitter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				goToTwitter();

			}
		});

		toFacebook.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToFacebook();

			}
		});

		toTuenti.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goToTuenti();

			}
		});

		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				sendEmail();

			}
		});

		updateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				socialThread.loadTweets();

			}
		});

	}

	public void goToFacebook() {
		Uri uri = Uri.parse("https://www.facebook.com/HayDelegacionEsiHd");
		Intent i = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(i);
	}

	public void goToTwitter() {
		Uri uri = Uri.parse("https://twitter.com/esiHD");
		Intent i = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(i);
	}

	public void goToTuenti() {
		Uri uri = Uri
				.parse("http://www.tuenti.com/#m=Page&func=index&page_key=1_2702_62148087");
		Intent i = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(i);
	}

	public void sendEmail() {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("plain/text");

		String emaillist[] = { "haydelegacion@gmail.com" };

		i.putExtra(Intent.EXTRA_EMAIL, emaillist);
		i.putExtra(Intent.EXTRA_SUBJECT, "Dudas y/o sugerencias");
		i.putExtra(Intent.EXTRA_TEXT, mailBody.getText().toString());

		startActivity(i);
	}

	public void flip(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			init_x = event.getX();
			break;

		case MotionEvent.ACTION_UP:
			float fin_x = event.getX();
			if (init_x - fin_x > 0) {
				Animation animationFlipIn = AnimationUtils.loadAnimation(this,
						R.anim.flipin2);
				Animation animationFlipOut = AnimationUtils.loadAnimation(this,
						R.anim.flipout2);
				flipper.setInAnimation(animationFlipIn);
				flipper.setOutAnimation(animationFlipOut);
				flipper.showNext();
			}
			if (init_x - fin_x < 0) {
				Animation animationFlipIn = AnimationUtils.loadAnimation(this,
						R.anim.flipin);
				Animation animationFlipOut = AnimationUtils.loadAnimation(this,
						R.anim.flipout);
				flipper.setInAnimation(animationFlipIn);
				flipper.setOutAnimation(animationFlipOut);
				flipper.showPrevious();
			}
		}
	}

	public void flip2(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			init_x = event.getX();
			break;

		case MotionEvent.ACTION_UP:
			float fin_x = event.getX();
			if (init_x - fin_x > 120) {
				Animation animationFlipIn = AnimationUtils.loadAnimation(this,
						R.anim.flipin2);
				Animation animationFlipOut = AnimationUtils.loadAnimation(this,
						R.anim.flipout2);
				flipper.setInAnimation(animationFlipIn);
				flipper.setOutAnimation(animationFlipOut);
				flipper.showNext();
			}
			if (init_x - fin_x < -120) {
				Animation animationFlipIn = AnimationUtils.loadAnimation(this,
						R.anim.flipin);
				Animation animationFlipOut = AnimationUtils.loadAnimation(this,
						R.anim.flipout);
				flipper.setInAnimation(animationFlipIn);
				flipper.setOutAnimation(animationFlipOut);
				flipper.showPrevious();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			socialThread.start();
		} catch (RuntimeException e) {
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
			socialThread.stop();
		} catch (RuntimeException e) {
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		boolean retry = true;
		while (retry) {
			try {
				socialThread.join();
				retry = false;
			} catch (Exception e) {
			}
		}
	}

}
