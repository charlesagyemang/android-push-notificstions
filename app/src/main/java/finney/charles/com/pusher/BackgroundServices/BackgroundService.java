package finney.charles.com.pusher.BackgroundServices;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import finney.charles.com.pusher.MainActivity;
import finney.charles.com.pusher.R;

public class BackgroundService extends Service {

    private boolean isRunning;
    private Context context;
    private Thread  backgroundThread;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        this.context = this;
        this.isRunning = false;
        this.backgroundThread = new Thread(myTask);
    }

    private Runnable myTask = new Runnable() {
        public void run() {
            // Do something here
            Log.e("==Running Task==", "It is running");

            stopSelf();
        }
    };

    @Override
    public void onDestroy() {
        this.isRunning = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!this.isRunning) {
            this.isRunning = true;
            this.backgroundThread.start();
            Log.e("==new stuff==", "yeper");

            //Check for the id and toast a message


            //Talk to devless here and make sure all the preconditions are set and send a notification
            // Make it return a boolean


            /*
            //Send notification
            intent = new Intent(BackgroundService.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(BackgroundService.this, 1410,
                    intent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notificationBuilder = new
                    NotificationCompat.Builder(BackgroundService.this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle("Message")
                    .setContentText("heyaaaa")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager)
                            getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(1410, notificationBuilder.build());

            */

        }
        return START_STICKY;
    }
}
