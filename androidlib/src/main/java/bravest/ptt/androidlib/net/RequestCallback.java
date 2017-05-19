package bravest.ptt.androidlib.net;

/**
 * Created by root on 5/9/17.
 */

public interface RequestCallback {
    void onSuccess(String content);

    void onFail(String errorMessage);

    void onCookieExpired();
}
