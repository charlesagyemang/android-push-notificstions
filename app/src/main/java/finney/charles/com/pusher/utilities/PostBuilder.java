package finney.charles.com.pusher.utilities;

import android.test.mock.MockApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pianoafrik on 6/3/17.
 */

public class PostBuilder {

    public static Map<String, Object> formPushNotificationBody (String smartPhoneToken, Map<String, String> notification) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("to", smartPhoneToken);
        map.put("notification", notification);

        return map;
    }

}
