package ts.af2.lightlife.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import bravest.ptt.androidlib.utils.plog.PLog;
import ts.af2.lightlife.R;

/**
 * Created by root on 12/27/16.
 */

public class Utils {

    private static final String TAG = "Utils";

    public static int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static boolean isServiceExisted(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceInfoList = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        if (!(serviceInfoList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceInfoList.size(); i++) {
            ActivityManager.RunningServiceInfo serviceInfo = serviceInfoList.get(i);
            ComponentName serviceName = serviceInfo.service;
            if (TextUtils.equals(serviceName.getClassName(), className)) {
                return true;
            }
        }
        return false;
    }

    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return (int) context.getResources().getDimension(resourceId);
    }

    public static void popSoftInput(final Context context, final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();

        //Delay , otherwise the edit text not ready and the keyboard will not pop up;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(editText, 0);
            }
        }, 300);
    }

    public static void hideSoftInput(Context context, EditText editText) {
        if (!isKeyboardShowing(context)) {
            return;
        }
        editText.clearFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static boolean isKeyboardShowing(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return inputMethodManager.isActive();
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public static boolean requestPermissions(Activity activity, int requestCode, String[] permissions) {
        List<String> deniedPermissions = findDeniedPermissions(activity, permissions);
        if (deniedPermissions.size() > 0) {
            activity.requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            return false;
        }
        return true;
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> findDeniedPermissions(Activity activity, String... permissions) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permissions) {
            if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }

    private static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= 23;
    }

    //StatusBar
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int dp2px(Context context, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, context.getResources().getDisplayMetrics());
    }

    public static void getHexagonPoints(float topX, float topY, float length) {
        float x = topX;
        float y = topY;
        //根号3
        final float gen3 = 1.732f;
        PLog.log("左肩: x =  " + (x - (length / 2f) * gen3) + ", y = " + (y + length / 2f));
        PLog.log("右肩：x = " + (x + (length / 2f) * gen3) + ", y = " + (y + length / 2f));
        PLog.log("左跨：x = " + (x - (length / 2f) * gen3) + ", y = " + (y + (3f / 2f) * length));
        PLog.log("右跨：x = " + (x + (length / 2f) * gen3) + ", y = " + (y + (3f / 2f) * length));
        PLog.log("底点：x = " + (x) + ", y = " + (y + 2f * length));
        PLog.log("边长：= " + length);
    }

    public static Bitmap getScreenBitmap(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        int statusBarHeight = getStatusBarHeight(activity);

        view.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bmp;
    }

    public static Bitmap getScreenBitmapWithoutToolbar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        int statusBarHeight = getStatusBarHeight(activity);
        int actionBarHeight;
        if (activity.getActionBar() != null) {
            actionBarHeight = activity.getActionBar().getHeight();
        } else if (activity instanceof AppCompatActivity) {
            if (((AppCompatActivity) activity).getSupportActionBar() != null) {
                actionBarHeight = ((AppCompatActivity) activity).getSupportActionBar().getHeight();
            } else {
                actionBarHeight = 0;
            }
        } else {
            actionBarHeight = 0;
        }

        PLog.log(actionBarHeight);

        view.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeight + actionBarHeight, width, height - statusBarHeight - actionBarHeight);
        view.destroyDrawingCache();
        return bmp;
    }

    public static final Type getType(Class<?> subclass) {
        Type superClass = subclass.getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new RuntimeException("Missing type parameter");
        }
        return ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public static final int DRAW_OVERLAY_REQUEST_CODE = 0x1111;

    /**
     * 加载本地图片
     * http://bbs.3gstdy.com
     *
     * @param url
     * @return
     */
    public static Bitmap getLocalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "getLocalBitmap:  " + Log.getStackTraceString(e));
            return null;
        }
    }

    /**
     * 把 content 类型的uri 转换为 file 类型的 uri （其实，就是通过content类型的uri
     * 解释为bitmap，然后保存在sd卡中，通过保存路径来获得file类型额uri）
     *
     * @param uri
     * @return
     */
    public static Uri convertUri(Context context, Uri uri) {
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(context, bitmap);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把 Bitmap 保存在SD卡路径后，返回file 类型的 uri
     *
     * @param bm
     * @return
     */
    public static Uri saveBitmap(Context context, Bitmap bm) {
        File tmDir = new File(Environment.getExternalStorageDirectory()
                + "/" + context.getString(R.string.app_name));
        if (!tmDir.exists()) {
            tmDir.mkdir();
        }
        File img = new File(tmDir.getAbsolutePath() + "/tmp.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    public static ProgressDialog newFullScreenProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context, R.style.NoBorderProgressDialog);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        return dialog;
    }

    private void calculateMemoryToken(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            int rowBytes = bitmap.getRowBytes();
            int height = bitmap.getHeight();
            long memory = rowBytes * height;
            Log.d("maopei", "calculateMemoryToken: " + memory + "B");
        }
    }
}
