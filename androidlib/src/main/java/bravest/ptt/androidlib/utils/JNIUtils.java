package bravest.ptt.androidlib.utils;

/**
 * Created by root on 5/11/17.
 */

public class JNIUtils {
    static{
        System.loadLibrary("androidLib");
    }

    public static native String getApplicationId();

    public static native String getRestAPIKey();
}
