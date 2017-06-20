package bravest.ptt.androidlib.net;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import bravest.ptt.androidlib.activity.AbstractBaseActivity;
import bravest.ptt.androidlib.mockdata.MockService;

public class RemoteService {
    private static RemoteService service = null;

    private RemoteService() {

    }

    public static synchronized RemoteService getInstance() {
        if (RemoteService.service == null) {
            RemoteService.service = new RemoteService();
        }
        return RemoteService.service;
    }

    public void invoke(final AbstractBaseActivity activity,
                       final String apiKey,
                       final RequestParam param,
                       final RequestCallback callBack) {

        final URLData urlData = UrlConfigManager.findURL(activity, apiKey);
        if (urlData == null) {
            throw new NullPointerException();
        }
        Log.i("urlData", urlData.toString());
        if (urlData.getMockClass() != null) {
            Log.i("MockService", "MockService");
            try {
                MockService mockService = (MockService) Class.forName(
                        urlData.getMockClass()).newInstance();
                String strResponse = mockService.getJsonData();

                final DataResponse dataResponseInJson = JSON.parseObject(strResponse,
                        DataResponse.class);
                if (callBack != null) {
                    if (dataResponseInJson.hasError()) {
                        callBack.onFail(dataResponseInJson.getErrorMessage());
                    } else {
                        callBack.onSuccess(dataResponseInJson.getResult());
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            if (param != null) {
                Log.i("httpParams", "httpParams");
                  AbstractOkHttpRequest request = activity.
                          getAbstractRequestManager().createRequest(urlData, param, callBack);
                  DefaultThreadPool.getInstance().execute(request);
            } else {
                Log.i("http", "http");
                AbstractOkHttpRequest request = activity.
                        getAbstractRequestManager().createRequest(urlData,callBack);
                DefaultThreadPool.getInstance().execute(request);
            }

        }
    }
}