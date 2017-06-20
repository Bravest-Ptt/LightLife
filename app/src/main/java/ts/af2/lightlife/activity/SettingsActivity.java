package ts.af2.lightlife.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import ts.af2.lightlife.R;

/**
 * Created by fengyou on 17-6-20.
 */

public class SettingsActivity extends Activity implements View.OnClickListener {

    private LinearLayout mBackLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings);
        findViewById();
        initViews();
        setOnListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void findViewById() {
        mBackLinearLayout = (LinearLayout) findViewById(R.id.settings_back);
    }

    private void initViews() {

    }

    private void setOnListener() {

        mBackLinearLayout.setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.settings_back:
                    finish();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
