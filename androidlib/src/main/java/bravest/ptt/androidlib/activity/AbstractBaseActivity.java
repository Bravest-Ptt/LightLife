package bravest.ptt.androidlib.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import bravest.ptt.androidlib.R;
import bravest.ptt.androidlib.net.AbstractRequestManager;
import bravest.ptt.androidlib.utils.plog.PLog;

/**
 * Created by pengtian on 2017/5/8.
 */

public abstract class AbstractBaseActivity extends AppCompatActivity {
    /**
     * 请求列表管理器
     */
    protected AbstractRequestManager mAbstractRequestManager = null;

    protected Context mContext;

    protected AbstractBaseActivity mActivity;

    private boolean mHasTransitionAnimation = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        initVariables();
        initViews(savedInstanceState);
        initData();
    }

    protected abstract void initVariables();

    protected abstract void initViews(@Nullable Bundle savedInstanceState);

    protected abstract void initData();

    protected void onDestroy() {
        /**
         * 在activity销毁的时候同时设置停止请求，停止线程请求回调
         */
        if (mAbstractRequestManager != null) {
            PLog.d("dcancle", "cancle");
            mAbstractRequestManager.cancelRequest();
        }
        super.onDestroy();
    }

    protected void onPause() {
        /**
         * 在activity停止的时候同时设置停止请求，停止线程请求回调
         */
        if (mAbstractRequestManager != null) {
            PLog.d("pcancle", "cancle");
            mAbstractRequestManager.cancelRequest();
        }
        super.onPause();
    }

    public AbstractRequestManager getAbstractRequestManager() {
        return mAbstractRequestManager;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (mHasTransitionAnimation) {
            overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (mHasTransitionAnimation) {
            overridePendingTransition(R.anim.activity_left_in, R.anim.activity_right_out);
        }
    }

    public void setHasTransitionAnimation(boolean hasTransitionAnimation) {
        this.mHasTransitionAnimation = hasTransitionAnimation;
    }
}
