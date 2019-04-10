package net.awesomekorean.hangul;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Calendar;

public class MainActivity extends Activity {

    final String url = "http://app.awesomekorean.net";
    public final static String CHANNEL_ID="akorean";
    public final static String LOG_PREFIX = "AKOREAN";
    public boolean clickedYes = false;

    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView1);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        // Here is where we setup the connection between javascript and android
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        webView.loadUrl(url);

        createNotificationChannel(this);

        // Example of setting an alarm 1 minute from now
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        // this a 24 hour clock
        // 2 in afternoon == 14
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        NotificationAlarm notificationAlarm = new NotificationAlarm();
        Notification notification = notificationAlarm.createNotification(this,
                "Awesome Korean Time",
                "Don't forget to study Korean!");

        notificationAlarm.scheduleNotification(this, notification, hour, min + 1 );

        Log.e(LOG_PREFIX, "Main Activity Started");
    }

    public void doOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final MainActivity thisActivity = this;
        builder.setMessage(R.string.exit_message)
                .setPositiveButton(R.string.exit_yes_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        thisActivity.doOnBackPressed();
                    }
                })
                .setNegativeButton(R.string.exit_no_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        clickedYes = false;
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }

    private void createNotificationChannel(Context context) {
        // Create a Notification Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(MainActivity.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
