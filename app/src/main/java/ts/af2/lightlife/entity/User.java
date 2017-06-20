package ts.af2.lightlife.entity;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.Date;

import ts.af2.lightlife.util.UserUtils;


public class User implements Serializable{

    private static final long serialVersionUID = -2083503801443301445L;

    private static final String TAG = "User";

    public static final String OBJECT_ID = "objectId";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String EMAIL = "email";

    public static final String EMAIL_VERIFIED = "emailVerified";

    public static final String SESSION_TOKEN = "sessionToken";

    public static final String MOBILE_PHONE_NUMBER = "mobilePhoneNumber";

    public static final String MOBILE_PHONE_NUMBER_VERIFIED = "mobilePhoneNumberVerified";

    public static final String AGE = "age";

    public static final String CREATED_AT = "createdAt";

    public static final String UPDATED_AT = "email";

    public static final String COUNTER = "counter";

    private static User sUser = null;

    private String objectId;

    private String username;

    private String password;

    private String email;

    private Boolean emailVerified;

    private String sessionToken;

    private String mobilePhoneNumber;

    private Boolean mobilePhoneNumberVerified;

    private Number age;

    private Date createdAt;

    private Date updatedAt;

    private Number counter;

    private String smsCode;

    private ProfileEntity profile;

    private Boolean sex;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    /**
     *  read User from memory or local shared preference
     * @param context
     * @return
     */
    public static User getInstance(Context context) {
        Log.d(TAG, "getInstance: user = " + sUser);
        if (sUser == null) {
            sUser = (User) UserUtils.readObject(context);
        }
        return sUser;
    }

    public static void saveUserLocal(Context context, User user) {
        UserUtils.saveObject(context, user);
        sUser = (User) UserUtils.readObject(context);
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public Boolean getMobilePhoneNumberVerified() {
        return mobilePhoneNumberVerified;
    }

    public void setMobilePhoneNumberVerified(Boolean mobilePhoneNumberVerified) {
        this.mobilePhoneNumberVerified = mobilePhoneNumberVerified;
    }

    public Number getAge() {
        return age;
    }

    public void setAge(Number age) {
        this.age = age;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Number getCounter() {
        return counter;
    }

    public void setCounter(Number counter) {
        this.counter = counter;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "objectId='" + objectId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", emailVerified=" + emailVerified +
                ", sessionToken='" + sessionToken + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", mobilePhoneNumberVerified=" + mobilePhoneNumberVerified +
                ", age=" + age +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", counter=" + counter +
                ", smsCode='" + smsCode + '\'' +
                ", profile=" + profile +
                ", sex=" + sex +
                '}';
    }
}