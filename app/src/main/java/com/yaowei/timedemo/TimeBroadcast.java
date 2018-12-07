package com.yaowei.timedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by kouhengsheng on 2018/11/15.
 */
public class TimeBroadcast extends BroadcastReceiver {
	private OnTimeChangeListenner onTimeChangeListener;

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action != null && action.equals(Intent.ACTION_TIME_TICK)) {
			Bundle extras = intent.getExtras();

			onTimeChangeListener.onTimeChange();
		}
	}

	public void setOnTimeChangeListener(OnTimeChangeListenner onTimeChangeListener) {
		this.onTimeChangeListener = onTimeChangeListener;
	}
}
