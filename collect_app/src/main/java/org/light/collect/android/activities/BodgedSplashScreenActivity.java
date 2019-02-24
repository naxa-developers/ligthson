package org.light.collect.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.light.collect.android.R;
import org.light.collect.android.utilities.SharedPreferenceUtils;

public class BodgedSplashScreenActivity extends CollectAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bodged_splash_activity);

        new Handler().postDelayed(() -> {
            if (SharedPreferenceUtils.hasEmailSaved()) {
                startActivity(new Intent(this, MainMenuActivity.class));
            } else {
                startActivity(new Intent(this, EmailActivity.class));
            }

            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        },3000);




    }
}
