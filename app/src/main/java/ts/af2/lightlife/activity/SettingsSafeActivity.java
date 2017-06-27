package ts.af2.lightlife.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import ts.af2.lightlife.R;
import ts.af2.lightlife.entity.User;
import ts.af2.lightlife.view.SildingFinishLayout;

/**
 * Created by fengyou on 17-6-27.
 */
public class SettingsSafeActivity extends Activity {
    private SildingFinishLayout mSildingSettingsSafe;
    private LinearLayout mLinearLayoutSafe;
    private TextView mTeleNumber;
    private User mUser;
    private String TAG = "SettingsSafeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_settings);
        initviews();
        setOnClickListener();
        mUser = User.getInstance(this);
        Log.d(TAG, "user = " + mUser.toString());
        mTeleNumber.setText(mUser.getMobilePhoneNumber());
    }

    //    @Override
//    protected void initVariables() {
//
//    }
//
//    @Override
//    protected void initViews(@Nullable Bundle savedInstanceState) {
//        setContentView(R.layout.safe_settings);
//        initviews();
//        setOnClickListener();
//        mUser = User.getInstance(mContext);
//        Log.d(TAG, "user = " + mUser.toString());
//        mTeleNumber.setText(mUser.getMobilePhoneNumber());
//    }

    private void setOnClickListener() {
        mSildingSettingsSafe.setTouchView(mLinearLayoutSafe);
        mSildingSettingsSafe.setOnSildingFinishListener(
                new SildingFinishLayout.OnSildingFinishListener() {
                    @Override
                    public void onSildingFinish() {
                        finish();
                    }
                });
    }

    private void initviews() {
        mSildingSettingsSafe = (SildingFinishLayout) findViewById(R.id.silding_settings_safe);
        mLinearLayoutSafe = (LinearLayout) findViewById(R.id.settings_safe_linearlayout);
        mTeleNumber = (TextView) findViewById(R.id.tele_number);
    }
//
//    @Override
//    protected void initData() {
//    }
}
