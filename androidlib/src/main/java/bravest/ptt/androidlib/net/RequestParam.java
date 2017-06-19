package bravest.ptt.androidlib.net;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class RequestParam implements java.io.Serializable {

    private static final long serialVersionUID = 1274906854152052510L;

    private String body;

    private String objectId;

//    private JSONObject param;

    public RequestParam(String objectId, String body) {
        this.objectId = objectId;
        this.body = body;
//        this.param = new JSONObject();
    }

    public RequestParam(String body) {
        checkParam(body);
        this.body = body;
    }


    public RequestParam() {
//        this.param = new JSONObject();
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "body='" + body + '\'' +
                ", objectId='" + objectId + '\'' +
                '}';
    }

//    public  RequestParam param(String key, String value) {
//        try {
//            this.put(key, value);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return this;
//        }
//        return this;
//    }

//    public boolean hasParam() {
//        if (keys() == null) {
//            return false;
//        }
//        return keys().hasNext();
//    }

//    public String stringParam() {
//        JSON.
//    }

    public String getBody() {
        return body;
    }

    public RequestParam setBody(String body) {
        checkParam(body);
        this.body = body;
        return this;
    }

    public String getObjectId() {
        return objectId;
    }

    public RequestParam setObjectId(String objectId) {
        checkParam(objectId);
        this.objectId = objectId;
        return this;
    }

    public boolean hasId() {
        return !TextUtils.isEmpty(objectId);
    }

    public boolean hasBody() {
        return !TextUtils.isEmpty(body);
    }

    private void checkParam(String... param) {
        for (String p:param) {
            if (TextUtils.isEmpty(p)) {
                throw new NullPointerException();
            }
        }
    }

    //----------------------------------------------------------------------------------------
//    public int compareTo(final Object another) {
//        int compared;
//        /**
//         * 值比较
//         */
//        final RequestParameter parameter = (RequestParameter) another;
//        compared = name.compareTo(parameter.name);
//        if (compared == 0) {
//            compared = value.compareTo(parameter.value);
//        }
//        return compared;
//    }
//
//    public boolean equals(final Object o) {
//        if (null == o) {
//            return false;
//        }
//
//        if (this == o) {
//            return true;
//        }
//
//        if (o instanceof RequestParameter) {
//            final RequestParameter parameter = (RequestParameter) o;
//            return name.equals(parameter.name) && value.equals(parameter.value);
//        }
//
//        return false;
//    }
}