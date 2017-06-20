package ts.af2.lightlife.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

import bravest.ptt.androidlib.net.RequestParam;
import bravest.ptt.androidlib.utils.ToastUtils;
import ts.af2.lightlife.R;
import ts.af2.lightlife.entity.User;
import ts.af2.lightlife.util.API;
import ts.af2.lightlife.util.UserUtils;
import ts.af2.lightlife.util.Utils;

import static ts.af2.lightlife.activity.RegisterActivity.LENGTH_PASSWORD;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    public static final String REGISTER_SUCCESS_PROFILE_FAILED
            = "register_success_profile_failed";

    private EditText mAccountEditor;

    private EditText mPasswordEditor;

    private Button mLoginButton;

    private ProgressDialog mDialog;

    @Override
    protected void initVariables() {
        super.initVariables();
        Intent intent = getIntent();
        if (intent != null
                && TextUtils.equals(intent.getAction(), REGISTER_SUCCESS_PROFILE_FAILED)) {
            User user = User.getInstance(mContext);
            Log.d(TAG, "initVariables: user = " + user);
        } else {
            Log.d(TAG, "initVariables:  user is null!");
        }
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        mAccountEditor = (EditText) findViewById(R.id.accountEditor);
        mPasswordEditor = (EditText) findViewById(R.id.passWordEditor);
        mLoginButton = (Button) findViewById(R.id.login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLoginClick();
            }
        });

        mDialog = Utils.newFullScreenProgressDialog(mContext);
    }

    @Override
    protected void initData() {
    }

    private void handleLoginClick() {
        //第一步，验证验证码，用户名，头像是否选择
        final String account = mAccountEditor.getText().toString();
        final String password = mPasswordEditor.getText().toString();
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showToast(mContext, getString(R.string.login_please_input_account));
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < LENGTH_PASSWORD) {
            ToastUtils.showToast(mContext, getString(R.string.register_password_hint));
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put(User.USERNAME, account);
        map.put(User.PASSWORD, password);
        String urlBody = UserUtils.convertToUrlParams(map);
        Log.d(TAG, "handleLoginClick: urlBody = " + urlBody);

        mDialog.show();
        RequestParam param = new RequestParam(null, urlBody);
        _NET(API.LOGIN, param, new InnerRequestCallback() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Log.d(TAG, "onSuccess: content = " + content);

                loginSuccess(content);
            }

            @Override
            public void onFail(String errorMessage) {
                Log.d(TAG, "onFail: errorMessage = " + errorMessage);
                loginFailed(errorMessage);
            }
        });
    }

    private void loginSuccess(String content) {
        mDialog.dismiss();

        ToastUtils.showToast(mContext, getString(R.string.login_successful));

        User user = JSON.parseObject(content, User.class);
        storeUserInfo(user);

        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        setResult(RESULT_OK);
        finish();
    }

    private void loginFailed(String errorMessage) {
        mDialog.dismiss();

        ToastUtils.showToast(mContext, getString(R.string.login_failed));
    }

    private void storeUserInfo(User user) {
        User.saveUserLocal(mContext, user);
    }
}
