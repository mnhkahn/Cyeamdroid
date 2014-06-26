package com.cyeam.cyeamdroid.doodle;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.cyeam.cyeamdroid.app.R;

/**
 * Created by bryce on 14-6-24.
 */
public class DoodleWidget extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager
                .getInstance(context);
        this.onUpdate(context, appWidgetManager, appWidgetManager
                .getAppWidgetIds(new ComponentName(context,
                        DoodleWidget.class)));
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.widget_doodle);
            views.setBitmap(R.id.doodle_img, "setImageBitmap", Doodle.GetDoodle());
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        AppWidgetManager appWidgetManager = AppWidgetManager
                .getInstance(context);
        this.onUpdate(context, appWidgetManager, appWidgetManager
                .getAppWidgetIds(new ComponentName(context,
                        DoodleWidget.class)));
    }
}
