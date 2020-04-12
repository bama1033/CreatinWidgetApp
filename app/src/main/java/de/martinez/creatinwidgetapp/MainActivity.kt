package de.martinez.creatinwidgetapp

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import de.martinez.creatinwidgetapp.SimpleWidgetProvider.Red.haveIDrunk
import de.martinez.creatinwidgetapp.SimpleWidgetProvider.Red.timeupdate


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onResumee()

        val intent = Intent(this@MainActivity, UpdateWidgetService::class.java)
        startService(intent)
//        finish()
//        this.finishAffinity()
//        finishAndRemoveTask()
    }

//    fun onTaskRemoved(rootIntent: Intent?) {
//        Toast.makeText(baseContext, "New Day...", Toast.LENGTH_SHORT).show()
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
//    }

    private fun onResumee() {
        super.onResume()
        this.registerReceiver(
            dayChangedBroadcastReceiver,
            DayChangedBroadcastReceiver.getIntentFilter()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        onResumee()
    }

    private val dayChangedBroadcastReceiver = object : DayChangedBroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onDayChanged(context: Context) {
            haveIDrunk = false
            timeupdate = true
            Toast.makeText(baseContext, "New Day...", Toast.LENGTH_SHORT).show()

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
}
