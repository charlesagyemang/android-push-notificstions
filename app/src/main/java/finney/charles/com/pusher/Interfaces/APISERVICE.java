package finney.charles.com.pusher.Interfaces;

/**
 * Created by pianoafrik on 6/3/17.
 */
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APISERVICE {

    @POST("fcm/send")
    Call<ResponseBody> sendPosts(
            @Header("authorization") String token,
            @Body Map<String, Object> field

    );
}
