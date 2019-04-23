package net.awesomekorean.hangul;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WebAppInterface {
    Context mContext;
    NotificationAlarm notificationAlarm = new NotificationAlarm();

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void showNotification(String notificationTitle, String notificationContent) {

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        // Create the actual notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, MainActivity.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_compass)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // notificationId is a unique int for each notification that you must define
        Integer notificationId = 0;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(notificationId, mBuilder.build());
    }

    @JavascriptInterface
    public void scheduleNotification(String hour, String minute) {

        Log.d(MainActivity.LOG_PREFIX, "scheduleNotification called from web. Hour: " + hour + ", Minute: " + minute);

        // Example of setting an alarm 1 minute from now
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        // this a 24 hour clock
        // 2 in afternoon == 14
        //calendar.set(Calendar.HOUR, hour);
        //calendar.set(Calendar.MINUTE, minute);

        Notification notification = notificationAlarm.createNotification(mContext,
                "Awesome Korean Time",
                "Don't forget to study Korean!");

        Integer h = Integer.parseInt(hour);
        Integer m = Integer.parseInt(minute);

        notificationAlarm.scheduleNotification(mContext, notification, h, m);

    }

    @JavascriptInterface
    public void cancelNotification() {
        Log.d(MainActivity.LOG_PREFIX, "Cancelling notification from web");
        notificationAlarm.cancelNotification(mContext);
    }

    @JavascriptInterface
    public void makePurchase(final String skuId) {

        Log.d(MainActivity.LOG_PREFIX, "Attempting to make purchase");

        BillingClient billingClient;

        billingClient = BillingClient.newBuilder(mContext).setListener(

                new PurchasesUpdatedListener() {
                    @Override
                    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
                        Log.d(MainActivity.LOG_PREFIX, "Inside onPurchasesUpdated!");
                        for(Purchase purchase : purchases) {
                            Log.d(MainActivity.LOG_PREFIX, purchase.toString());
                        }
                    }
                }

        ).build();

        // Sku == Puchase Item
        final List<SkuDetails> skuDetails = new ArrayList<>();

        // Look up the details for the sku, for example: "a.korean.hangul.donation.1"
        List<String> skuList = new ArrayList<>();
        skuList.add(skuId);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
                        if (responseCode == BillingClient.BillingResponse.OK
                                && skuDetailsList != null) {
                            for (SkuDetails nextSkuDetail : skuDetailsList) {
                                String sku = nextSkuDetail.getSku();
                                String price = nextSkuDetail.getPrice();
                                String desc = nextSkuDetail.getDescription();
                                skuDetails.add(nextSkuDetail);
                                if (skuId.equals(sku)) {
                                    Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                });

        // query for all items
        /*
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    // The BillingClient is ready. You can query purchases here.
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });
        */


        // Actually purchase the product
        /*
        BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetails)
                .build();
        int responseCode = billingClient.launchBillingFlow(flowParams);
        */

        // Retrieve a list of purchased products
        /*
        billingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.INAPP,
                new PurchaseHistoryResponseListener() {
                    @Override
                    public void onPurchaseHistoryResponse(@BillingClient.BillingResponse int responseCode,
                                                          List<Purchase> purchasesList) {
                        if (responseCode == BillingClient.BillingResponse.OK
                                && purchasesList != null) {
                            for (Purchase purchase : purchasesList) {
                                // Process the result.
                            }
                        }
                    }
                });
                */
    }
}
