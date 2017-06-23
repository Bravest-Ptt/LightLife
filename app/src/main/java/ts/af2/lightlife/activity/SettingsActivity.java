package ts.af2.lightlife.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import bravest.ptt.androidlib.activity.toolbar.AbstractToolbarActivity;
import ts.af2.lightlife.R;

/**
 * Created by fengyou on 17-6-20.
 */

public class SettingsActivity extends AbstractToolbarActivity {

    private Button mSignOutButton;

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
    }

    private void setOnListener() {
        mSignOutButton.setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sign_out:
                    Intent splashIntent = new Intent(SettingsActivity.this, SplashActivity.class);
                    startActivity(splashIntent);
                    break;
            }
        }
    };

}
