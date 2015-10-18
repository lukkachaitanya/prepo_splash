package com.experiences.projects.booktable;


import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.experiences.projects.booktable.utils.Preference;
import com.parse.ui.ParseLoginActivity;


public class SplashScreenActivity extends BaseActivity {

    private LinearLayout llSplashScreen;
    private static final int PARSE_REQUEST_CODE = 100;

    @Override
    public void createActivity() {
        llSplashScreen = (LinearLayout) layoutInflater.inflate(R.layout.activity_splash_screen, null);
        llContent.addView(llSplashScreen, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        llHeader.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {

                boolean IS_APP_FIRSTTIME_LAUNCH = preferenceUtils.getbooleanFromPreference(Preference.IS_APP_FIRSTTIME_LAUNCH,true);
                if(IS_APP_FIRSTTIME_LAUNCH)
                    startActivityForResult(new Intent(SplashScreenActivity.this, ParseLoginActivity.class), PARSE_REQUEST_CODE);
                else {
                    startActivity(new Intent(SplashScreenActivity.this, HotelList.class));
                    finish();
                }
            }
        },2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PARSE_REQUEST_CODE && resultCode == RESULT_OK)
        {
            preferenceUtils.saveBooleanInPreference(Preference.IS_APP_FIRSTTIME_LAUNCH,false);
            preferenceUtils.commitPreference();
            startActivity(new Intent(SplashScreenActivity.this, HotelList.class));
            finish();
        }
    }
}
