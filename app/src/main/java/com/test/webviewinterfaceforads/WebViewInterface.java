package com.test.webviewinterfaceforads;
import android.app.Activity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class WebViewInterface {
    private String gameUrl;
    private String admobApId = "ca-app-pub-3940256099942544~3347511713";
    private String interstitialAdUnitId = "ca-app-pub-3940256099942544/1033173712";
    private String rewardedVideoAdInitId = "ca-app-pub-3940256099942544/5224354917";
    private Activity activity;
    private WebView webView;
    private InterstitialAd interstitialAd;
    private RewardedVideoAd rewardedVideoAd;
    private boolean isInitializing;
    public WebViewInterface(Activity activity, WebView webView, String gameUrl) {
        isInitializing = true;
        this.activity = activity;
        this.webView = webView;
        this.gameUrl = gameUrl;
        MobileAds.initialize(activity, admobApId);

        interstitialAd = new InterstitialAd(activity);
        interstitialAd.setAdUnitId(interstitialAdUnitId);
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(MyInterstitialAdListener());

        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
        rewardedVideoAd.setRewardedVideoAdListener(MyRewardedVideoAdListener());
        loadRewardedVideoAd();
    }

    void loadGame(){ // wait for interstitial ads to be loaded
        webView.loadUrl("file:///android_asset/ninja-ranmaru/index.html"); // load game from local
        //webView.loadUrl("https://html5.gamedistribution.com/405c00612981466cbc5d9dcef4214811/"); // sample game
        //webView.loadUrl("http://10.0.2.2/index.html"); // for run on emulator emulator ip should be 10.0.2.2
    }

    void loadRewardedVideoAd(){
        rewardedVideoAd.loadAd(rewardedVideoAdInitId,
                new AdRequest.Builder().build());
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void ShowInterstitialAd() {
        activity.runOnUiThread(new Runnable() {
            @Override public void run() {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
                else{
                    webView.loadUrl("javascript:OnInterstitialAdClosed()");
                    //webview.loadUrl("javascript:init('" + theArgumentYouWantToPass + "')");
                }
            }
        });
    }

    @JavascriptInterface
    public void ShowRewardedVideoAd() {
        activity.runOnUiThread(new Runnable() {
            @Override public void run() {
                if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                }
                else{
                    webView.loadUrl("javascript:OnRewardedVideoAdComplated()");
                    //webview.loadUrl("javascript:init('" + theArgumentYouWantToPass + "')");
                }
            }
        });
    }

    AdListener MyInterstitialAdListener(){
        return new AdListener() {
            @Override
            public void onAdLoaded() {
                if(isInitializing){
                    loadGame();
                    isInitializing = false;
                }
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                if(isInitializing){
                    loadGame();
                    isInitializing = false;
                }
                // Code to be executed when an ad request fails.
                Log.d("InterstitialAd Failed", String.valueOf(errorCode));
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                //load next add
                interstitialAd.loadAd(new AdRequest.Builder().build());
                webView.loadUrl("javascript:OnInterstitialAdClosed()");
            }
        };
    }

    RewardedVideoAdListener MyRewardedVideoAdListener(){
        return new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                loadRewardedVideoAd();
                webView.loadUrl("javascript:OnRewardedVideoAdComplated()");
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
                Log.d("VideoAd Failed", String.valueOf(i));
            }

            @Override
            public void onRewardedVideoCompleted() {
                webView.loadUrl("javascript:OnRewardedVideoAdComplated()");
            }
        };
    }

    public void onActivityResume() {
        rewardedVideoAd.resume(activity);
    }

    public void onActivityPause() {
        rewardedVideoAd.pause(activity);
    }

    public void onActivityDestroy() {
        rewardedVideoAd.destroy(activity);
    }
}
