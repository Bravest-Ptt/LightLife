package ts.af2.lightlife.net;

import android.content.Context;

import bravest.ptt.androidlib.net.RequestCallback;
import bravest.ptt.androidlib.utils.DialogUtils;
import ts.af2.lightlife.R;

/**
 * Created by pengtian on 2017/5/12.
 */

public abstract class AbstractRequestCallback implements RequestCallback {

    protected Context context;

    public AbstractRequestCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onSuccess(String content) {
    }

    @Override
    public void onFail(String errorMessage) {
        DialogUtils.showAlert(context
                , R.style.Dialog_Confirm_Blue
                , "Error"
                , errorMessage
                , "OK"
                , null
                , null
                , null
        );
    }

    @Override
    public void onCookieExpired() {
        DialogUtils.showPrompt(context, "请重新登录");
    }
}
