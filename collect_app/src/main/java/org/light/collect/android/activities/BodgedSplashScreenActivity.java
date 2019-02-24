package org.light.collect.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import org.light.collect.android.R;
import org.light.collect.android.application.Collect;
import org.light.collect.android.listeners.PermissionListener;
import org.light.collect.android.utilities.DialogUtils;
import org.light.collect.android.utilities.PermissionUtils;
import org.light.collect.android.utilities.SharedPreferenceUtils;

public class BodgedSplashScreenActivity extends Activity {

    private static final int SPLASH_TIMEOUT = 3000; // milliseconds
    private static final boolean EXIT = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bodged_splash_activity);

        new PermissionUtils(this).requestStoragePermissions(new PermissionListener() {
            @Override
            public void granted() {
                // must be at the beginning of any activity that can be called from an external intent
                try {
                    Collect.createODKDirs();
                } catch (RuntimeException e) {
                    DialogUtils.showDialog(DialogUtils.createErrorDialog(BodgedSplashScreenActivity.this,
                            e.getMessage(), EXIT), BodgedSplashScreenActivity.this);
                    return;
                }

                init();
            }

            @Override
            public void denied() {

                finish();
            }
        });


    }

    private void init() {


        new Handler().postDelayed(() -> {
            if (SharedPreferenceUtils.hasEmailSaved()) {
                startActivity(new Intent(this, MainMenuActivity.class));
            } else {
                startActivity(new Intent(this, EmailActivity.class));
            }

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, SPLASH_TIMEOUT);
    }
}
