package ru.geekbrains.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


public class MyWidget extends AppWidgetProvider {

    private static final String BUTTON_CLICKED_ACTION = "BUTTON_CLICKED_ACTION";

    public static int COUNTER;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        // Здесь обновим текст, будем показывать номер виджета
        views.setTextViewText(R.id.appwidget_text, String.format("%s - %d", widgetText, appWidgetId));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }
        ComponentName componentName = new ComponentName(context, MyWidget.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        remoteViews.setOnClickPendingIntent(R.id.appwidget_button, getPendingSelfIntent(context, BUTTON_CLICKED_ACTION));
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (BUTTON_CLICKED_ACTION.equals(intent.getAction())) {
            Log.d(getClass().getSimpleName(), "OnButtonClicked");
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            ComponentName watchWidget = new ComponentName(context, MyWidget.class);

            COUNTER++;
            remoteViews.setTextViewText(R.id.tv_counter, String.valueOf(COUNTER));
            appWidgetManager.updateAppWidget(watchWidget, remoteViews);
        }
    }
}
