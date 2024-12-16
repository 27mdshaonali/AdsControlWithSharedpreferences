package com.example.sharedpreferences.monetize;

import android.content.Context;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdMob {

    public static void sdkInitialize(Context context) {

        if (!Constant.IS_AD_ENABLED) return;


        new Thread(() -> {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(context, initializationStatus -> {
            });
        }).start();

    }

    public static void setBannerAd(LinearLayout adContainerView, Context context) {
        if (!Constant.IS_AD_ENABLED) return;

        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(Constant.BANNER_Ad);
        adContainerView.addView(adView);

        // Replace ad container with new ad view.
        adContainerView.removeAllViews();
        adContainerView.addView(adView);

        // Start loading the ad in the background.
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


    }

}
