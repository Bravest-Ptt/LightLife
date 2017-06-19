package bravest.ptt.androidlib.net;

public class URLData {
    /**
     * 接口名
     */
    private String key;
    /**
     * 数据缓存时间
     */
    private long expires;
    /**
     * 请求方式 get或者post
     */
    private String netType;
    /**
     * 请求地址
     */
    private String url;

    private String mockClass;

    private String contentType;

    public URLData() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMockClass() {
        return mockClass;
    }

    public void setMockClass(String mockClass) {
        this.mockClass = mockClass;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "URLData{" +
                "key='" + key + '\'' +
                ", expires=" + expires +
                ", netType='" + netType + '\'' +
                ", url='" + url + '\'' +
                ", mockClass='" + mockClass + '\'' +
                '}';
    }
}
