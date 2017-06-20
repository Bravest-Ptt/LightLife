package ts.af2.lightlife.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by root on 5/23/17.
 */

public class ProfileEntity implements Serializable{
    private String url;

    private String filename;

    private String cdn;

    @JSONField(name="__type")
    private String type = "File";

    private String group;

    @Override
    public String toString() {
        return "ProfileEntity{" +
                "url='" + url + '\'' +
                ", filename='" + filename + '\'' +
                ", cdn='" + cdn + '\'' +
                ", __type='" + type + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCdn() {
        return cdn;
    }

    public void setCdn(String cdn) {
        this.cdn = cdn;
        this.group = cdn;
        this.cdn = null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
