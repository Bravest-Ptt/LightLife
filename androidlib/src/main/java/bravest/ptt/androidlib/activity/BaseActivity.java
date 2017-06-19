package bravest.ptt.androidlib.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import bravest.ptt.androidlib.net.RequestManager;
import bravest.ptt.androidlib.utils.plog.PLog;

/**
 * Created by pengtian on 2017/5/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 请求列表管理器
     */
    protected RequestManager requestManager = null;

    protected Context mContext;

    protected BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        requestManager = new RequestManager(this);
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
        if (requestManager != null) {
            PLog.d("dcancle", "cancle");
            requestManager.cancelRequest();
        }
        super.onDestroy();
    }

    protected void onPause() {
        /**
         * 在activity停止的时候同时设置停止请求，停止线程请求回调
         */
        if (requestManager != null) {
            PLog.d("pcancle", "cancle");
            requestManager.cancelRequest();
        }
        super.onPause();
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }
}
