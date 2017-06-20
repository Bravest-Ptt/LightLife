package ts.af2.lightlife.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import bravest.ptt.androidlib.net.RequestParam;
import bravest.ptt.androidlib.utils.ToastUtils;
import bravest.ptt.androidlib.utils.plog.PLog;
import ts.af2.lightlife.R;
import ts.af2.lightlife.entity.User;
import ts.af2.lightlife.util.API;
import ts.af2.lightlife.util.Utils;

public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";

    public static final int REQUEST_CODE = 100;

    private View mLoginPanel;

    private View mProgressBarPanel;

    @Override
    protected void initVariables() {
        super.initVariables();
        checkPermissions();
        setHasTransitionAnimation(false);
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        View login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        View register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegister();
            }
        });

        mLoginPanel = findViewById(R.id.login_panel);
        mProgressBarPanel = findViewById(R.id.init_panel);
    }


    @Override
    protected void initData() {
        verifyIsExpired();
    }

    private void onInitFinished() {
        mLoginPanel.setVisibility(View.VISIBLE);
        mProgressBarPanel.setVisibility(View.GONE);
    }

    private void verifyIsExpired() {
        User user = User.getInstance(mContext);
        if (user == null) {
            onInitFinished();
            return;
        }
        final String objectId = user.getObjectId();
        final String token = user.getSessionToken();
        if (TextUtils.isEmpty(objectId) || TextUtils.isEmpty(token)) {
            ToastUtils.showToast(mContext, "Please Login again");
            return;
        }

        User tmp = new User();
        tmp.setCounter(1);
        String jsonBody = JSON.toJSONString(tmp);
        RequestParam param = new RequestParam(objectId, jsonBody);

        _NET(API.UPDATE_USER_INFO, param, new InnerRequestCallback() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Log.d(TAG, "onSuccess: content = " + content);
                goHome();
            }

            @Override
            public void onFail(String errorMessage) {
                super.onFail(errorMessage);
                Log.d(TAG, "onFail:  = " + errorMessage);
                ToastUtils.showToast(mContext, errorMessage);
                onInitFinished();
            }
        });
    }

    private void goHome() {
        Intent home = new Intent(mContext, MainActivity.class);
        startActivity(home);
        finish();
    }

    private void handleLogin() {
        PLog.d(TAG, "handle login");
        startActivityForResult(new Intent(this, LoginActivity.class), REQUEST_CODE);
    }

    private void handleRegister() {
        PLog.d(TAG, "handle register");
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private boolean checkPermissions() {
        Log.d(TAG, "checkPermissions: ");
        if (Build.VERSION.SDK_INT >= 23) {
            Log.d(TAG, "checkPermissions: ");
            boolean allGranted = Utils.requestPermissions(this, REQUEST_CODE, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            });

            if (allGranted) {
            }
            return allGranted;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 100) return;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                continue;
            }
            if (TextUtils.equals(permissions[i], Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    || TextUtils.equals(permissions[i], Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }
}
