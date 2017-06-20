package ts.af2.lightlife.entity;

import java.io.Serializable;

/**
 * Created by pengtian on 2017/5/14.
 */

public class SmsCodeEntity implements Serializable {

    public static final String SMS_STATE_SENDING = "SENDING";

    public static final String SMS_STATE_SUCCESS = "SUCCESS";

    public static final String SMS_STATE_FAIL = "FAIL";

    //（可用于后面使用查询短信状态接口来查询该条短信是否发送成功）
    private String smsId;

    private String mobilePhoneNumber;

    private String template;

    private String msg;

    //sms_state是发送状态，有值: SENDING-发送中，FAIL-发送失败 SUCCESS-发送成功
    private String sms_state;

    //verify_state是验证码是否验证状态， 有值: true-已验证 false-未验证
    private Boolean verify_state;

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSms_state() {
        return sms_state;
    }

    public void setSms_state(String sms_state) {
        this.sms_state = sms_state;
    }

    public Boolean getVerify_state() {
        return verify_state;
    }

    public void setVerify_state(Boolean verify_state) {
        this.verify_state = verify_state;
    }

    public static String getName() {
        return "SmsCodeEntity";
    }

    @Override
    public String toString() {
        return "SmsCodeEntity{" +
                "smsId='" + smsId + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", template='" + template + '\'' +
                ", msg='" + msg + '\'' +
                ", sms_state='" + sms_state + '\'' +
                ", verify_state='" + verify_state + '\'' +
                '}';
    }
}
