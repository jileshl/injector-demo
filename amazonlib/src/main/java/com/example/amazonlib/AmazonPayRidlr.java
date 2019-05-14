package com.example.amazonlib;

import android.content.Context;
import android.content.Intent;
import android.support.customtabs.CustomTabsIntent;

import amazonpay.silentpay.AmazonPay;

public class AmazonPayRidlr {
    Context context;
    public AmazonPayRidlr(Context context) {
        this.context = context;
    }

    public Intent getAuthIntent(CustomTabsIntent customTabsIntent){
        return AmazonPay.getAuthorizationIntent(context, customTabsIntent);
    }

}
