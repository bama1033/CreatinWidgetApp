package de.martinez.creatinwidgetapp

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews


lateinit var remoteViews: RemoteViews
//lateinit var widgetId: Integer

class SimpleWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val count = appWidgetIds.size
        for (i in 0 until count) {
            val widgetId = appWidgetIds[i]
            remoteViews = RemoteViews(
                context.packageName,
                R.layout.widgetlayoutnew
            )


//            if (firsttime) {
//                firsttime = false
//                Toast.makeText(context, "first time", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(context, "else ", Toast.LENGTH_SHORT).show()
            if (timeupdate) {
                firsttime = true
                timeupdate = false
                haveIDrunk = false
                remoteViews.setTextViewText(R.id.textView, haveIDrunk.toString())
                remoteViews.setImageViewResource(R.id.imageView, R.drawable.gruener_droid)

            }
            if (firsttime) {
//                Toast.makeText(context, "first time", Toast.LENGTH_SHORT).show()
                firsttime = false

            } else {
//                                Toast.makeText(context, "else ", Toast.LENGTH_SHORT).show()
                haveIDrunk = true

            }
            if (haveIDrunk) {
//                Toast.makeText(context, "i drunk true", Toast.LENGTH_SHORT).show()

                remoteViews.setTextViewText(R.id.textView, haveIDrunk.toString())
                remoteViews.setImageViewResource(R.id.imageView, R.drawable.roter_droid)
            }


            val intent = Intent(context, SimpleWidgetProvider::class.java)
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT
            )

            remoteViews.setOnClickPendingIntent(
                R.id.actionButton,
                pendingIntent
            )

            updateWidget(appWidgetManager, widgetId)
        }
        val intent = Intent(
            context.applicationContext,
            UpdateWidgetService::class.java
        )
        updateWidget(appWidgetManager, 0)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, count)

        // Update the widgets via the service
//        context.startService(intent)

    }

    private fun updateWidget(
        appWidgetManager: AppWidgetManager,
        widgetId: Int
    ) {
        appWidgetManager.updateAppWidget(widgetId, remoteViews)
    }

    companion object Red {
        var haveIDrunk = false
        var firsttime = true
        var timeupdate = false
    }
}

