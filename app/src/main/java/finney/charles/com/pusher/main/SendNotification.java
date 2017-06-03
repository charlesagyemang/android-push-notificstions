package finney.charles.com.pusher.main;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import finney.charles.com.pusher.Interfaces.APISERVICE;
import finney.charles.com.pusher.Interfaces.NotificationResponse;
import finney.charles.com.pusher.utilities.PostBuilder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pianoafrik on 6/3/17.
 */

public class SendNotification {


    private String FCMToken, smartPhoneToken;
    private Map<String, String> notification = new HashMap<>();

    public SendNotification(String FCMToken, String smartPhoneToken, Map<String, String> notification) {
        this.FCMToken = FCMToken;
        this.smartPhoneToken = smartPhoneToken;
        this.notification = notification;
    }

    public void execute (final NotificationResponse notificationResponse) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APISERVICE postapi = retrofit.create(APISERVICE.class);

        Call<ResponseBody> result = postapi.sendPosts("key=" + FCMToken,
                PostBuilder.formPushNotificationBody(smartPhoneToken, notification));

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                  notificationResponse.OnNotificationSuccess( response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                notificationResponse.OnNotificationFailure(t.toString());

            }
        });
    }

    public String getFCMToken() {
        return FCMToken;
    }

    public void setFCMToken(String FCMToken) {
        this.FCMToken = FCMToken;
    }

    public String getSmartPhoneToken() {
        return smartPhoneToken;
    }

    public void setSmartPhoneToken(String smartPhoneToken) {
        this.smartPhoneToken = smartPhoneToken;
    }

    public Map<String, String> getNotification() {
        return notification;
    }

    public void setNotification(Map<String, String> notification) {
        this.notification = notification;
    }
}
