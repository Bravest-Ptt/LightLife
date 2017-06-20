package ts.af2.lightlife;

import android.app.Application;

import com.zxy.tiny.Tiny;

/**
 * Created by root on 3/2/17.
 */

public class App extends Application {

    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        initTiny();
    }


    /**
     *  For Image compression
     */
    private void initTiny() {
        Tiny.getInstance().init(this);
    }
}
