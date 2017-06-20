package ts.af2.lightlife.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import bravest.ptt.androidlib.utils.ToastUtils;
import bravest.ptt.androidlib.utils.plog.PLog;
import ts.af2.lightlife.R;
import ts.af2.lightlife.util.Utils;
import ts.af2.lightlife.view.ClipView;

import static ts.af2.lightlife.activity.RegisterVerifyActivity.PROFILE_URL;

public class ClipImageActivity extends BaseActivity implements View.OnTouchListener {

    private static final String TAG = "ClipImageActivity";

    private ImageView mSrcImage;
    private View mConfirm;
    private ClipView mClipView;

    private Matrix mMatrix = new Matrix();
    private Matrix mSavedMatrix = new Matrix();

    /**
     * 动作标志：无
     */
    private static final int NONE = 0;
    /**
     * 动作标志：拖动
     */
    private static final int DRAG = 1;
    /**
     * 动作标志：缩放
     */
    private static final int ZOOM = 2;
    /**
     * 初始化动作标志
     */
    private int mMode = NONE;

    /**
     * 记录起始坐标
     */
    private PointF mStart = new PointF();
    /**
     * 记录缩放时两指中间点坐标
     */
    private PointF mMid = new PointF();

    private float mOldDist = 1f;

    private Bitmap mBitmap;

    private Toolbar mToolbar;

    private String mUrl;

    @Override
    protected void initVariables() {
        super.initVariables();
        Intent intent = getIntent();
        if (intent != null) {
            mUrl = intent.getStringExtra(PROFILE_URL);
        }
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_clip_image);

        //init toolbar confirm button
        mConfirm = this.findViewById(R.id.toolbar_confirm);
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SaveAsyncTask().execute();
            }
        });

        //init toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.verify_clip_profile));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mSrcImage = (ImageView) this.findViewById(R.id.src_image);
        mSrcImage.setOnTouchListener(this);

        ViewTreeObserver observer = mSrcImage.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            public void onGlobalLayout() {
                mSrcImage.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                initClipView(mSrcImage.getTop());
            }
        });
    }

    @Override
    protected void initData() {
    }

    /**
     * 初始化截图区域，并将源图按裁剪框比例缩放
     *
     * @param top
     */
    private void initClipView(int top) {
        Log.d(TAG, "initClipView: top = " + top);
        if (TextUtils.isEmpty(mUrl)) {
            Log.d(TAG, "initClipView: error happened url is null!");
            onBackPressed();
        }
        Log.d(TAG, "initClipView: mUrl = " + mUrl);
        Uri uri = Uri.parse(mUrl);
        mBitmap = Utils.getBitmapFromUri(mContext, uri);

        if (mBitmap == null) {
            Log.d(TAG, "initClipView: error happened : bitmap is null!");
            onBackPressed();
        }
        mClipView = new ClipView(ClipImageActivity.this);
        mClipView.setCustomTopBarHeight(top);
        mClipView.addOnDrawCompleteListener(new ClipView.OnDrawListenerComplete() {

            public void onDrawCompelete() {
                mClipView.removeOnDrawCompleteListener();
                int clipHeight = mClipView.getClipHeight();
                int clipWidth = mClipView.getClipWidth();
                int midX = mClipView.getClipLeftMargin() + (clipWidth / 2);
                int midY = mClipView.getClipTopMargin() + (clipHeight / 2);

                int imageWidth = mBitmap.getWidth();
                int imageHeight = mBitmap.getHeight();
                // 按裁剪框求缩放比例
                float scale = (clipWidth * 1.0f) / imageWidth;
                if (imageWidth > imageHeight) {
                    scale = (clipHeight * 1.0f) / imageHeight;
                }

                // 起始中心点
                float imageMidX = imageWidth * scale / 2;
                float imageMidY = mClipView.getCustomTopBarHeight()
                        + imageHeight * scale / 2;
                mSrcImage.setScaleType(ImageView.ScaleType.MATRIX);

                // 缩放
                mMatrix.postScale(scale, scale);
                // 平移
                mMatrix.postTranslate(midX - imageMidX, midY - imageMidY);

                mSrcImage.setImageMatrix(mMatrix);
                mSrcImage.setImageBitmap(mBitmap);
            }
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addContentView(mClipView, params);
    }

    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mSavedMatrix.set(mMatrix);
                // 设置开始点位置
                mStart.set(event.getX(), event.getY());
                mMode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mOldDist = spacing(event);
                if (mOldDist > 10f) {
                    mSavedMatrix.set(mMatrix);
                    midPoint(mMid, event);
                    mMode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mMode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mMode == DRAG) {
                    mMatrix.set(mSavedMatrix);
                    mMatrix.postTranslate(event.getX() - mStart.x, event.getY()
                            - mStart.y);
                } else if (mMode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        mMatrix.set(mSavedMatrix);
                        float scale = newDist / mOldDist;
                        mMatrix.postScale(scale, scale, mMid.x, mMid.y);
                    }
                }
                break;
        }
        view.setImageMatrix(mMatrix);
        return true;
    }

    /**
     * 多点触控时，计算最先放下的两指距离
     *
     * @param event
     * @return
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 多点触控时，计算最先放下的两指中心坐标
     *
     * @param point
     * @param event
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * 获取裁剪框内截图
     *
     * @return
     */
    private Bitmap getBitmap() {
        // 获取截屏
        View view = this.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        Bitmap finalBitmap = Bitmap.createBitmap(view.getDrawingCache(),
                mClipView.getClipLeftMargin(), mClipView.getClipTopMargin()
                        + statusBarHeight, mClipView.getClipWidth(),
                mClipView.getClipHeight());

        // 释放资源
        view.destroyDrawingCache();
        return finalBitmap;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        PLog.log("onBackPressed");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class SaveAsyncTask extends AsyncTask {

        private ProgressDialog dialog;

        private void saveFileFailed() {
            ToastUtils.showToast(mContext, getString(R.string.verify_clip_profile_failed));
            setResult(RESULT_CANCELED, new Intent());
            dialog.dismiss();
            finish();
        }

        private void saveFileSuccess(String file) {
            Intent data = new Intent();
            data.putExtra(PROFILE_URL, file);
            setResult(RESULT_OK, data);
            dialog.dismiss();
            finish();
        }

        public SaveAsyncTask() {
            dialog = Utils.newFullScreenProgressDialog(mContext);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Bitmap result = getBitmap();
            if (result == null) {
                PLog.d(TAG, "uri result in saveBitmap is null");
                onBackPressed();
                return null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            final Bitmap resultBitmap = (Bitmap) o;
            if (o == null) {
                saveFileFailed();
                return;
            }

            //Compress the image.
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance()
                    .source(resultBitmap)
                    .asFile()
                    .withOptions(options)
                    .compress(new FileCallback() {
                        @Override
                        public void callback(boolean isSuccess, String outfile) {
                            //return the compressed file path
                            Log.d(TAG, "callback: success = " + isSuccess);
                            Log.d(TAG, "callback: outFile = " + outfile);
                            if (isSuccess) {
                                saveFileSuccess(outfile);
                            } else {
                                saveFileFailed();
                            }
                        }
                    });
        }
    }
}
