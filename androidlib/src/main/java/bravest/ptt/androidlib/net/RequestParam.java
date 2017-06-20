package bravest.ptt.androidlib.net;

import android.text.TextUtils;

public class RequestParam implements java.io.Serializable {

    private static final long serialVersionUID = 1274906854152052510L;

    private String body;

    private String objectId;

    /**
     *
     * @param objectId for url
     * @param body the body
     */
    public RequestParam(String objectId, String body) {
        this.objectId = objectId;
        this.body = body;
    }

    /**
     * @param body the body
     */
    public RequestParam(String body) {
        checkParam(body);
        this.body = body;
    }

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

    @Override
    public String toString() {
        return "RequestParam{" +
                "body='" + body + '\'' +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}