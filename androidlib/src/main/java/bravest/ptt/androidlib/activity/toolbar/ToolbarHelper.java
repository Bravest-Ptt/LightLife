package bravest.ptt.androidlib.activity.toolbar;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import bravest.ptt.androidlib.R;


/**
 * Created by root on 6/15/17.
 */

public class ToolbarHelper {

    private Context mContext;

    private View mRootView;

    private FrameLayout mContentView;

    private View mUserView;

    private Toolbar mToolbar;

    private LayoutInflater mInflater;

    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };

    public ToolbarHelper(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);

        initRootView();
        initToolbar();
        initUserView(layoutId);
    }

    private void initRootView() {
        mRootView = mInflater.inflate(R.layout.toolbar, null, false);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        mContentView = (FrameLayout) mRootView.findViewById(R.id.content_view);
    }

    private void initUserView(int id) {
        mUserView = mInflater.inflate(id, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
//        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
//        boolean overlay = typedArray.getBoolean(0, false);
//        int toolbarSize = (int)typedArray.getDimension(1,
//                (int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
//        typedArray.recycle();

//        params.topMargin = overlay ? 0 : toolbarSize;
        mContentView.addView(mUserView, params);
    }

    public View getContentView() {
        return mRootView;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
