package bravest.ptt.androidlib.net;

import android.content.Context;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractRequestManager {
    private List<AbstractOkHttpRequest> requestList = null;
    protected Context context;


    public AbstractRequestManager(final Context context) {
        this.context = context;
        // 异步请求列表
        requestList = new CopyOnWriteArrayList<>();
    }

    /**
     * 添加Request到列表
     */
    protected void addRequest(final AbstractOkHttpRequest request) {
        requestList.add(request);
    }

    /**
     * 取消网络请求
     */
    public void cancelRequest() {
        if ((requestList != null) && (requestList.size() > 0)) {
            for (final AbstractOkHttpRequest request : requestList) {
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
    protected abstract AbstractOkHttpRequest createRequest(final URLData urlData,
                                                           final RequestCallback requestCallback);

    /**
     * 有参数调用
     */
    protected abstract AbstractOkHttpRequest createRequest(final URLData urlData,
                                                           final RequestParam param,
                                                           final RequestCallback requestCallback);

}