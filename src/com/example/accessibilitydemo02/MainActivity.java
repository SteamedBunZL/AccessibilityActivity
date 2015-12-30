package com.example.accessibilitydemo02;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private WindowManager windowManager;

	public WindowManager.LayoutParams mWindowLayoutParams;

	private Display mDisplay;
	
	private View mView;
	
	private LayoutInflater mInflater;
	
	private boolean mIsShow =false;
	
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		mDisplay = windowManager.getDefaultDisplay();
		mWindowLayoutParams = new WindowManager.LayoutParams();
		mWindowLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
		mWindowLayoutParams.format = PixelFormat.RGBA_8888;

		mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
		mWindowLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
		mWindowLayoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
		
		mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = mInflater.inflate(R.layout.view_window, null);
		
		button = (Button) mView.findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				windowManager.removeView(mView);
				mIsShow = false;
			}
		});
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				show();
				
				Intent intent = new Intent("/");
				ComponentName cm = new ComponentName("com.android.settings",
						"com.android.settings.applications.InstalledAppDetailsTop");
				intent.setComponent(cm);
				intent.setData(Uri.parse("com.netease.newsreader.activity"));
				intent.setAction("android.intent.action.VIEW");
				startActivityForResult(intent, 0);

				// Intent intent = new
				// Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
				// startActivity(intent);

			}
		});
	}
	
	private void show(){
		if (mIsShow) {
			windowManager.removeView(mView);
		}
		windowManager.addView(mView, mWindowLayoutParams);
		mIsShow = true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
