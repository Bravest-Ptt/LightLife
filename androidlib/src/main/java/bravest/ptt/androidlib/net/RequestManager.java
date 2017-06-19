package bravest.ptt.androidlib.net;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import bravest.ptt.androidlib.net.bmob.BmobHttpRequest;

public class RequestManager {
    private List<OkHttpRequest> requestList = null;
    private Context context;


    public RequestManager(final Context context) {
        this.context = context;
        // 异步请求列表
        requestList = new CopyOnWriteArrayList<>();
    }

    /**
     * 添加Request到列表
     */
    public void addRequest(final OkHttpRequest request) {
        requestList.add(request);
    }

    /**
     * 取消网络请求
     */
    public void cancelRequest() {
        if ((requestList != null) && (requestList.size() > 0)) {
            for (final OkHttpRequest request : requestList) {
                if (request.getCall() != null) {
                    try {
                        request.getCall().cancel();
                        requestList.remove(request);
                    } catch (final UnsupportedOperationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //TODO Add request type for different request

    /**
     * 无参数调用
     */
    public OkHttpRequest createRequest(final URLData urlData, final RequestCallback requestCallback) {
        final OkHttpRequest request = new BmobHttpRequest(context, urlData, null, requestCallback);
        addRequest(request);
        return request;
    }

    /**
     * 有参数调用
     */
    public OkHttpRequest createRequest(final URLData urlData, final RequestParam param, final RequestCallback requestCallback) {
        final OkHttpRequest request = new BmobHttpRequest(context, urlData, param, requestCallback);
        addRequest(request);
        return request;
    }

}