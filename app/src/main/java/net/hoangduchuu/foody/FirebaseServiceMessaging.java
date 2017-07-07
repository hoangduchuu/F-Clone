package net.hoangduchuu.foody;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by hoang on 7/7/17.
 */

public class FirebaseServiceMessaging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("kiemtra", remoteMessage.getFrom() + "---" + remoteMessage.getData()

                + "---" + remoteMessage.getNotification().getBody());
    }
}
