package bravest.ptt.androidlib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class PreferencesUtils {

    private PreferencesUtils() {
        throw new AssertionError();
    }

    /**
     * put string preferences
     *
     * @param context context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putString(Context context, String preference, String key, String value) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get string preferences
     *
     * @param context context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws ClassCastException if there is a preference with this
     * name that is not a string
     * @see #getString(Context, String, String, String)
     */
    public static String getString(Context context, String preference, String key) {
        checkParams(preference, key);
        return getString(context, preference, key, null);
    }

    /**
     * get string preferences
     *
     * @param context      context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a string
     */
    public static String getString(Context context, String preference,
                                   String key, String defaultValue) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * put int preferences
     *
     * @param context context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putInt(Context context, String preference, String key, int value) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get int preferences
     *
     * @param context context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a int
     * @see #getInt(Context, String, String, int)
     */
    public static int getInt(Context context, String preference, String key) {
        checkParams(preference, key);
        return getInt(context, preference, key, -1);
    }

    /**
     * get int preferences
     *
     * @param context      context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a int
     */
    public static int getInt(Context context, String preference, String key, int defaultValue) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * put long preferences
     *
     * @param context context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putLong(Context context, String preference, String key, long value) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * get long preferences
     *
     * @param context context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a long
     * @see #getLong(Context, String, String, long)
     */
    public static long getLong(Context context, String preference, String key) {
        checkParams(preference, key);
        return getLong(context, preference, key, -1);
    }

    /**
     * get long preferences
     *
     * @param context      context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a long
     */
    public static long getLong(Context context, String preference, String key, long defaultValue) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     *
     * @param context context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putFloat(Context context, String preference, String key, float value) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * get float preferences
     *
     * @param context context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     * name that is not a float
     * @see #getFloat(Context, String, String, float)
     */
    public static float getFloat(Context context,String preference, String key) {
        checkParams(preference, key);
        return getFloat(context, preference ,key, -1);
    }

    /**
     * get float preferences
     *
     * @param context      context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a float
     */
    public static float getFloat(Context context, String preference, String key, float defaultValue) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     *
     * @param context context
     * @param key     The name of the preference to modify
     * @param value   The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putBoolean(Context context, String preference, String key, boolean value) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean preferences, default is false
     *
     * @param context context
     * @param key     The name of the preference to retrieve
     * @return The preference value if it exists, or false. Throws ClassCastException if there is a preference with this
     * name that is not a boolean
     * @see #getBoolean(Context, String, String, boolean)
     */
    public static boolean getBoolean(Context context, String preference, String key) {
        checkParams(preference, key);
        return getBoolean(context, preference, key, false);
    }

    /**
     * get boolean preferences
     *
     * @param context      context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a boolean
     */
    public static boolean getBoolean(Context context, String preference, String key, boolean defaultValue) {
        checkParams(preference, key);
        SharedPreferences settings = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

    private static void checkParams(String... param) {
        for (String p : param) {
            if (TextUtils.isEmpty(p)) {
                throw new NullPointerException();
            }
        }
    }
}
