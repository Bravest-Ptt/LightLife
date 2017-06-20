package ts.af2.lightlife.util;

/**
 * Created by pengtian on 2017/5/10.
 */

public interface API {
    /**
     * API key must same as url.xml
     */

    String REGISTER = "register";

    String LOGIN = "login";

    String UPDATE_USER_INFO = "updateUserInfo";

    /**
     *  获取用户信息
     */
    String GET_USER_INFO = "getUserInfo";

    /**
     * 请求验证码
     */
    String REQUEST_SMS_CODE = "requestSmsCode";

    /**
     * 查询验证码状态
     */
    String QUERY_SMS_STATE = "querySmsState";

    /**
     *  是否手机号已经被注册
     */
    String IS_MOBILE_USED = "isMobileUsed";

    /**
     *  验证用户输入验证码是否正确
     */
    String VERIFY_SMS_CODE = "verifySmsCode";

    /**
     *  判断此用户名是否已经被使用
     */
    String IS_USER_NAME_USED = "isUserNameUsed";

    /**
     * 上传JPG格式图片
     */
    String UPLOAD_JPG_IMAGE = "uploadJPGImage";
}
