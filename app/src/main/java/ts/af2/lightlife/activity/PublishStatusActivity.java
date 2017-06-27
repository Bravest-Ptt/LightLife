package ts.af2.lightlife.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import ts.af2.lightlife.R;
import ts.af2.lightlife.util.ImageUtil;

public class PublishStatusActivity extends BaseActivity {
    private static final String TAG = "PublishStatusActivity";
    public static final String IMAGE_UNSPECIFIED = "image/*";
    public static final int REQUEST_CODE_PICK_PICTURE = 1;
    public static final int REQUEST_CODE_TAKE_PHOTO = 2;
    private Button mDismissBtn;
    private Button mPublishBtn;
    private ImageButton mPictureBtn;
    private EditText mStatusText;
    private PopupWindow mPopupWindow;
    private LinearLayout mImageViewLayout;

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_publish_status);

        mDismissBtn = (Button) findViewById(R.id.status_dismiss);
        mDismissBtn.setOnClickListener(mClickListener);

        mPublishBtn = (Button) findViewById(R.id.status_publish);
        mPublishBtn.setOnClickListener(mClickListener);

        mPictureBtn = (ImageButton) findViewById(R.id.status_picture);
        mPictureBtn.setOnClickListener(mClickListener);

        mStatusText = (EditText) findViewById(R.id.status_text);
        mStatusText.addTextChangedListener(mTextWatcher);

        mImageViewLayout = (LinearLayout) findViewById(R.id.picked_pictures);
    }

    @Override
    protected void initData() {

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.status_dismiss:
//                    DialogUtils.showAlert(PublishStatusActivity.this, 0, "是否保存", "是否保存草稿", "保存",
//                            mConfirmOnClickListener, getResources().getString(R.string.dismiss),
//                            mDismissOnClickListener);
                    finish();
                    break;
                case R.id.status_publish:
                    // TODO: 17-6-15
                    break;
                case R.id.status_picture:
                    // TODO: 17-6-26 fix softkeyborad hide
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    showPopupWindow();
                    break;
                case R.id.text_take_photo:
                    mPopupWindow.dismiss();
                    intent.setAction("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
                    break;
                case R.id.text_pick_photo:
                    mPopupWindow.dismiss();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.setType(IMAGE_UNSPECIFIED);
                    startActivityForResult(intent, REQUEST_CODE_PICK_PICTURE);
                    break;
                case R.id.text_cancle:
                    mPopupWindow.dismiss();
                    break;
                default:

            }
        }
    };

    DialogInterface.OnClickListener mConfirmOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

        }
    };

    DialogInterface.OnClickListener mDismissOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            finish();
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "dlj 100 PublishStatusActivity onActivityResult : ");
        if (resultCode != RESULT_OK) {
            return;
        }
        Bitmap bitmap = null;
        switch (requestCode) {
            case REQUEST_CODE_PICK_PICTURE:
                if (Build.VERSION.SDK_INT >= 19) {
                    bitmap = ImageUtil.handleImageOnKitKat(this, data);
                } else {
                    bitmap = ImageUtil.handleImageBeforeKitKat(this, data);
                }
                Log.d(TAG, "dlj 113 PublishStatusActivity onActivityResult : bitmap = " + bitmap);
                break;
            case REQUEST_CODE_TAKE_PHOTO:
                if (data.getData() != null || data.getExtras() != null) { //防止没有返回结果
                    Uri uri = data.getData();
                    if (uri != null) {
                        bitmap = BitmapFactory.decodeFile(uri.getPath()); //拿到图片
                    }
                    if (bitmap == null) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            bitmap = (Bitmap) bundle.get("data");
                        } else {
                            Toast.makeText(getApplicationContext(), "找不到图片", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                Log.d(TAG, "dlj 138 PublishStatusActivity onActivityResult : bitmap = " + bitmap);
                break;
            default:

        }
        // TODO: 17-6-26 multi photo pick layout
        ImageView imageView = new ImageView(mActivity);
        imageView.setImageBitmap(bitmap);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(100, 100);
        imageView.setLayoutParams(param);
        mImageViewLayout.addView(imageView);
    }

    private void showPopupWindow() {
        //设置contentView
        final View contentView = LayoutInflater.from(this).inflate(R.layout.status_popup_window, null);
        mPopupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        //设置各个控件的点击响应
        TextView tv1 = (TextView) contentView.findViewById(R.id.text_take_photo);
        TextView tv2 = (TextView) contentView.findViewById(R.id.text_pick_photo);
        TextView tv3 = (TextView) contentView.findViewById(R.id.text_cancle);
        tv1.setOnClickListener(mClickListener);
        tv2.setOnClickListener(mClickListener);
        tv3.setOnClickListener(mClickListener);
        //显示PopupWindow
        final View rootView = LayoutInflater.from(this).inflate(R.layout.activity_publish_status, null);
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(mStatusText.getText())) {
                mPublishBtn.setEnabled(false);
            } else {
                mPublishBtn.setEnabled(true);
            }
        }
    };

}

