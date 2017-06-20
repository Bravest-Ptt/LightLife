package ts.af2.lightlife.net.bmob;

import android.content.Context;

import bravest.ptt.androidlib.net.AbstractOkHttpRequest;
import bravest.ptt.androidlib.net.AbstractRequestManager;
import bravest.ptt.androidlib.net.RequestCallback;
import bravest.ptt.androidlib.net.RequestParam;
import bravest.ptt.androidlib.net.URLData;

/**
 * Created by root on 5/25/17.
 */

public class BmobRequestManager extends AbstractRequestManager {

    public BmobRequestManager(Context context) {
        super(context);
    }

    @Override
    protected AbstractOkHttpRequest createRequest(
            URLData urlData, RequestCallback requestCallback) {
        return createRequest(urlData, null, requestCallback);
    }

    @Override
    protected AbstractOkHttpRequest createRequest(
            URLData urlData, RequestParam param, RequestCallback requestCallback) {
        final AbstractOkHttpRequest request = new BmobOkHttpRequest(
                context, urlData, param, requestCallback);
        addRequest(request);
        return request;
    }
}
