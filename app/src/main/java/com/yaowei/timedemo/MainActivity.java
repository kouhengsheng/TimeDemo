package com.yaowei.timedemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

	private TimeBroadcast mTimeBroadcast;
	private TextView      mtvTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mtvTest = (TextView) findViewById(R.id.tvTest);
		//注册系统每分钟发出的时间广播
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_TIME_TICK);
		mTimeBroadcast = new TimeBroadcast();
		registerReceiver(mTimeBroadcast, intentFilter);

		//系统每分钟发出的广播的回调
		mTimeBroadcast.setOnTimeChangeListener(new OnTimeChangeListenner() {
			@Override
			public void onTimeChange() {
				updateTime();
			}
		});
	}

	//每分钟更新时间
	private void updateTime() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		String date = year + "年" + month + "月" + day + "日" + hour + "点" + minute + "分";
		mtvTest.setText(date);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//注销系统每分钟发出的广播
		unregisterReceiver(mTimeBroadcast);
	}
}
