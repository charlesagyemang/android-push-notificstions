package finney.charles.com.pusher.BackgroundServices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import finney.charles.com.pusher.BackgroundServices.BackgroundService;

/**
 * Created by pianoafrik on 6/3/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, BackgroundService.class);
        context.startService(background);
    }
}
