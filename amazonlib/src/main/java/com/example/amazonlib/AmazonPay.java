package com.example.amazonlib;

import android.content.Context;

public class AmazonPay {
    AmazonPay(Context context) {
        amazonpay.silentpay.AmazonPay.getAuthorizationIntent(context);
        System.out.println("Hello World!");
    }

}
