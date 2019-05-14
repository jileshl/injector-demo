package com.example.amazonlib;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;


public class AmazonPayRidlr {
    Context context;
    public AmazonPayRidlr(Context context) {
        this.context = context;
    }

    public CustomTabsIntent getAuthIntent(){
        String url = "https://ridlr.in";
        CustomTabsIntent.Builder builderCustomTabs = new CustomTabsIntent.Builder();
        CustomTabsIntent intentCustomTabs = builderCustomTabs.build();
        intentCustomTabs.intent.setPackage("com.android.chrome");
        builderCustomTabs.setShowTitle(true);
        builderCustomTabs.setToolbarColor(ContextCompat.getColor(context, R.color.col_brand));
        builderCustomTabs.enableUrlBarHiding();
        intentCustomTabs.launchUrl(context, Uri.parse(url));
        return intentCustomTabs;
    }

}
