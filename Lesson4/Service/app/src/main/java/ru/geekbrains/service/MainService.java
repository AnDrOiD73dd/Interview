package ru.geekbrains.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

// Служба
public class MainService extends IntentService {

    public static final String KEY_PARAM = "key number";

    int messageId=0;

    public MainService() {
        super("MainService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        makeNote("onHandleIntent");
    }
    @Override
    public void onCreate() {
        makeNote("onCreate");
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        makeNote("onStartCommand");
        if (intent.hasExtra(KEY_PARAM)) {
            String number = intent.getStringExtra(KEY_PARAM);
            makeNote(number);
        }
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        makeNote("onDestroy");
        super.onDestroy();
    }
    // вывод уведомления в строке состояния
    private void makeNote(String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                                                .setSmallIcon(R.mipmap.ic_launcher)
                                                .setContentTitle("Main service notification")
                                                .setContentText(message);
        Intent resultIntent = new Intent(this, MainService.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }
}
