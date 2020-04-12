package de.martinez.creatinwidgetapp

import android.app.IntentService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi


class UpdateWidgetService : IntentService("blu") {


    private var brScreenOffReceiver: DayChangedBroadcastReceiver? = null


//    override fun onCreate() {
//        registerScreenOffReceiver()
//        this.registerReceiver(
//            dayChangedBroadcastReceiver,
//            DayChangedBroadcastReceiver.getIntentFilter()
//        )
//    }

//    override fun onTaskRemoved(rootIntent: Intent?) {
//        val restartServiceIntent = Intent(applicationContext, this.javaClass)
//        restartServiceIntent.setPackage(packageName)
//        val restartServicePendingIntent = PendingIntent.getService(
//            applicationContext,
//            1,
//            restartServiceIntent,
//            PendingIntent.FLAG_ONE_SHOT
//        )
//        val alarmService =
//            applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        alarmService[AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000] =
//            restartServicePendingIntent
//        super.onTaskRemoved(rootIntent)
//    }
override fun onTaskRemoved(rootIntent: Intent?) {
    Toast.makeText(baseContext, "New kkkklklk...", Toast.LENGTH_SHORT).show()
}

    val dayChangedBroadcastReceiver = object : DayChangedBroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onDayChanged(context: Context) {
            SimpleWidgetProvider.haveIDrunk = false
            SimpleWidgetProvider.timeupdate = true
            Toast.makeText(baseContext, "New Day...", Toast.LENGTH_SHORT).show()
            //TODO ADD HERE UPDATE TRIGGER

            val ids = AppWidgetManager.getInstance(application).getAppWidgetIds(
                ComponentName(
                    application,
                    SimpleWidgetProvider::class.java
                )
            )

            val clickIntent = Intent(
                baseContext,
                SimpleWidgetProvider::class.java
            )
            clickIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            clickIntent.putExtra(
                AppWidgetManager.EXTRA_APPWIDGET_IDS,
                ids
            )

            val intent = Intent(baseContext, SimpleWidgetProvider::class.java)
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE


            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {}

    private fun registerScreenOffReceiver() {
        brScreenOffReceiver = object : DayChangedBroadcastReceiver() {
//            fun onReceive(context: Context?, intent: Intent) {
//                if (intent.action == Intent.ACTION_HEADSET_PLUG) {
//                    val state = intent.getIntExtra("state", -1)
//                    when (state) {
//                        0 -> Log.e("AAAAAAAAAA", "Headset is unplugged")
//                        1 -> Log.e("AAAAAAAAA", "Headset is plugged")
//                        else -> Log.e("AAAAAAAAAAAA", "I have no idea what the headset state is")
//                    }
//                }
//            }

            override fun onDayChanged(context: Context) {
                Toast.makeText(context, "HUH3333...", Toast.LENGTH_SHORT).show()
            }
        }
        val filter = IntentFilter(Intent.ACTION_TIME_CHANGED)
        registerReceiver(brScreenOffReceiver, filter)
    }


//    override fun onStart(intent: Intent, startId: Int) {
//        val appWidgetManager = AppWidgetManager.getInstance(
//            this
//                .applicationContext
//        )
//
//        val dayChangedBroadcastReceiver = object : DayChangedBroadcastReceiver() {
//            @RequiresApi(Build.VERSION_CODES.O)
//            override fun onDayChanged() {
//
//                Toast.makeText(baseContext, "HUH...", Toast.LENGTH_SHORT).show()
//            }
//        }
//        this.registerReceiver(
//            dayChangedBroadcastReceiver,
//            DayChangedBroadcastReceiver.getIntentFilter()
//        )
//
//
////        val allWidgetIds = 0
//        val allWidgetIds = AppWidgetManager.getInstance(application).getAppWidgetIds(
//            ComponentName(
//                application,
//                SimpleWidgetProvider::class.java
//            )
//        )
//        //      ComponentName thisWidget = new ComponentName(getApplicationContext(),
////              MyWidgetProvider.class);
////      int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
////        for (widgetId in allWidgetIds) { // create some random data
//
//        val remoteViews = RemoteViews(
//            this
//                .applicationContext.packageName,
//            R.layout.widgetlayoutnew
//        )
////        Log.w("WidgetExample", number.toString())
//        // Set the text
////            remoteViews.setTextViewText(
////                R.id.update,
////                "Random: $number"
////            )
//        // Register an onClickListener
//        val clickIntent = Intent(
//            this.applicationContext,
//            SimpleWidgetProvider::class.java
//        )
//        clickIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
//        clickIntent.putExtra(
//            AppWidgetManager.EXTRA_APPWIDGET_IDS,
//            allWidgetIds
//        )
////            val pendingIntent = PendingIntent.getBroadcast(
////                applicationContext, 0, clickIntent,
////                PendingIntent.FLAG_UPDATE_CURRENT
////            )
////            remoteViews.setOnClickPendingIntent(R.id.imageView, pendingIntent)
//        appWidgetManager.updateAppWidget(0, remoteViews)
//
//        stopSelf()
//        super.onStart(intent, startId)
//        Toast.makeText(this, "ServiceCalled ", Toast.LENGTH_SHORT).show();
//    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onHandleIntent(p0: Intent?) {
//        Toast.makeText(baseContext, "New WAHHH...", Toast.LENGTH_SHORT).show()
    }
}