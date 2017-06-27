package ts.af2.lightlife.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import bravest.ptt.androidlib.activity.toolbar.AbstractToolbarActivity;
import bravest.ptt.androidlib.utils.bmob.BmobConstants;
import ts.af2.lightlife.R;
import ts.af2.lightlife.entity.User;
import ts.af2.lightlife.view.SildingFinishLayout;

/**
 * Created by fengyou on 17-6-20.
 */

public class SettingsActivity extends AbstractToolbarActivity {
    private final static String FILENAME = BmobConstants.PREF_USER;
    private Button mSignOutButton;
    private SildingFinishLayout mSildingSettings;
    private LinearLayout mLinearLayout;
    private RelativeLayout mSettingsSafe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.settings);
        initViews();
        setOnListener();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {
        mSignOutButton = (Button) findViewById(R.id.sign_out);
        mSildingSettings = (SildingFinishLayout) findViewById(R.id.silding_settings);
        mLinearLayout = (LinearLayout) findViewById(R.id.settings_linearlayout);
        mSettingsSafe = (RelativeLayout) findViewById(R.id.settings_safe);
    }

    private void setOnListener() {
        mSignOutButton.setOnClickListener(mClickListener);
        mSildingSettings.setTouchView(mLinearLayout);
        mSildingSettings.setOnSildingFinishListener(
                new SildingFinishLayout.OnSildingFinishListener() {
                    @Override
                    public void onSildingFinish() {
                        finish();
                    }
                });
        mSettingsSafe.setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sign_out:
                    SharedPreferences sharedata = getSharedPreferences(FILENAME, 0);
                    SharedPreferences.Editor editor = sharedata.edit();
                    editor.clear();
                    editor.commit();
                    User.clearUser(mContext);
                    Intent splashIntent = new Intent(SettingsActivity.this, SplashActivity.class);
                    startActivity(splashIntent);
                    break;
                case R.id.settings_safe:
                    Intent safeIntent = new Intent(SettingsActivity.this,
                            SettingsSafeActivity.class);
                    startActivity(safeIntent);
                    break;
            }
        }
    };

}
