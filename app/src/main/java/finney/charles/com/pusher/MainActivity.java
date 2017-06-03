package finney.charles.com.pusher;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;


import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import finney.charles.com.pusher.Interfaces.NotificationResponse;
import finney.charles.com.pusher.main.SendNotification;
import finney.charles.com.pusher.utilities.PostBuilder;


public class MainActivity extends AppCompatActivity {

   public  final String FCMTOKEN = "AAAAik3eBR8:APA91bF0lmoJrMCXSzG6HiHrdhLA-nCp2lyvJJ8-2OpG8QpvbKOXfZojwniBJWBMB7jQt0V6AefsMMa06n-_rDDCfqXmWNZ-NgVIUoXo1V2_c8AzauMFCyFm6HdEEzkqrU7jPp0Cf_mr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String tkn = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(MainActivity.this, "Current token ["+tkn+"]",
                Toast.LENGTH_LONG).show();
        Log.d("App", "Token ["+tkn+"]");

        Pusher pusher = new Pusher("dd63c9d25951d41b4ab2");

        Channel channel = pusher.subscribe("delivery");

        channel.bind("doughman", new SubscriptionEventListener() {
            @Override
            public void onEvent(String s, String s1, String s2) {

                //Use data from backend
                Log.e("===new===", s2 );

                try {
                    JSONObject JO = new JSONObject(s2);
                    String nestMetric  = JO.getString("message");
                    JSONObject JOO = new JSONObject(nestMetric);
                    String test = JOO.getString("result");
                    Log.e("===new===", test);

                    //Send Notification

                    //prepare body

                    final Map<String, String > notification = new HashMap<>();
                    notification.put("title", "Ne title");
                    notification.put("body", test);
                    Log.e("kkakla", String.valueOf(PostBuilder.formPushNotificationBody(tkn, notification)));


                    SendNotification notification1 = new SendNotification(FCMTOKEN, tkn, notification);
                    notification1.execute(new NotificationResponse() {
                        @Override
                        public void OnNotificationSuccess(String success) {
                            Log.e("===Success===", success);
                        }

                        @Override
                        public void OnNotificationFailure(String failure) {
                            Log.e("==Failure==", failure);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }







            }
        });

        pusher.connect();






        Button btn =  (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
