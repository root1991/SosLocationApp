package com.example.SosLocationApp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

/**
 * Created by Andrey on 23.05.2014.
 */
public class SendLocationService extends Service{


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        GPSTracker gpsTracker = new GPSTracker(this);
        if (gpsTracker.canGetLocation()) {
            String stringLatitude = String.valueOf(gpsTracker.latitude);
            String stringLongitude = String.valueOf(gpsTracker.longitude);
            String country = gpsTracker.getCountryName(this);
            String city = gpsTracker.getLocality(this);
            String postalCode = gpsTracker.getPostalCode(this);
            String addressLine = gpsTracker.getAddressLine(this);
            String message = stringLatitude + " " + stringLongitude + " " + country + " " + city + " " + postalCode + " " + addressLine;
            Log.d("INFO LOC", message);
            SmsManager.getDefault().sendTextMessage("+380636977710", null, message, null, null);
        } else {
            gpsTracker.showSettingsAlert();
        }

        return START_STICKY;
    }
}
