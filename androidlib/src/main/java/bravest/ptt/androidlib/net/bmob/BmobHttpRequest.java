package bravest.ptt.androidlib.net.bmob;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import bravest.ptt.androidlib.net.OkHttpRequest;
import bravest.ptt.androidlib.net.RequestCallback;
import bravest.ptt.androidlib.net.RequestParam;
import bravest.ptt.androidlib.net.URLData;
import bravest.ptt.androidlib.utils.BaseUtils;
import bravest.ptt.androidlib.utils.bmob.BmobConstants;
import bravest.ptt.androidlib.utils.JNIUtils;
import bravest.ptt.androidlib.utils.PreferencesUtils;
import bravest.ptt.androidlib.utils.plog.PLog;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by root on 5/11/17.
 */

public class BmobHttpRequest extends OkHttpRequest {

    private static final String TAG = "BmobHttpRequest";

    private Context mContext;

    public BmobHttpRequest(Context context, URLData data, RequestParam param, RequestCallback callBack) {
        super(context, data, param, callBack);
        this.mContext = context;
    }

    /**
     * TODO refer bmob api document
     * 此种拼接方式为 1 没有参数时 xxx.api  2有参数时 xxx.api?k1=v1&k2=v2
     *
     * @param url
     * @param param
     * @return
     */
    protected String getNewUrl(String url, String method, RequestParam param) {
        try {
            if (param == null) {
                return url;
            }

            // 添加参数
            final StringBuffer paramBuffer = new StringBuffer();
            paramBuffer.append(url);

            if (param.hasId()) {
                paramBuffer.append("/"+param.getObjectId());
            }

            if (param.hasBody()) {
                switch (method) {
                    case REQUEST_GET:
                        paramBuffer.append("?where="+URLEncoder.encode(param.getBody(), "utf-8"));
                        break;
                    case REQUEST_POST:
                        break;
                    case REQUEST_PUT:
                        break;
                    case REQUEST_DELETE:
                        break;
                    default:
                        break;
                }
            }

            url = paramBuffer.toString();
            PLog.log("new url = " + url);

//            if ((param != null) && (param.length() > 0)) {
//                //sortKeys();// 这里要对key进行排序
//                Iterator<String> keys = param.keys();
//                while (keys.hasNext()) {
//                    String key = keys.next();
//                    String value = param.get(key).toString();
//                    if (paramBuffer.length() == 0) {
//                        paramBuffer.append(key + "=" + URLEncoder.encode(value, "utf-8"));
//                    } else {
//                        paramBuffer.append("&" + key + "=" + BaseUtils.UrlEncodeUnicode(value));
//                    }
//                }
//                return url + "?" + paramBuffer.toString();
//            } else {
//                return url;
//            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return url;
        }
        return url;
    }

    @Override
    protected void setHttpHeaders(OkHttpClient.Builder okBuilder, final URLData data) {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder();

                if (data.getContentType() != null) {
                    Log.d(TAG, "intercept: type = " + data.getContentType());
                    requestBuilder.header(BmobConstants.X_BMOB_CONTENT_TYPE, data.getContentType());
                }

                String token = PreferencesUtils.getString(mContext, BmobConstants.BMOB_SESSION_KEY);
                if (!TextUtils.isEmpty(token)) {
                    Log.d(TAG, "intercept: token = " + token);
                    requestBuilder.header(BmobConstants.X_BMOB_SESSION_TOKEN, token);
                }

                requestBuilder.header(BmobConstants.X_BMOB_APP_ID, JNIUtils.getApplicationId())
                        .header(BmobConstants.X_BMOB_REST_API_KEY, JNIUtils.getRestAPIKey())
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        okBuilder.addInterceptor(headerInterceptor);
    }
}
