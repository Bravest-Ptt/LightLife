package ts.af2.lightlife.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ts.af2.lightlife.R;

public class PublishStatusActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mDismissBtn;
    private Button mPublishBtn;
    private ImageButton mPictureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_status);
        initView();
    }

    private void initView() {
        mDismissBtn = (Button) findViewById(R.id.status_dismiss);
        mDismissBtn.setOnClickListener(this);

        mPublishBtn = (Button) findViewById(R.id.status_publish);
        mPublishBtn.setOnClickListener(this);

        mPictureBtn = (ImageButton) findViewById(R.id.status_picture);
        mPictureBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.status_dismiss:
                finish();
                break;
            case R.id.status_publish:
                // TODO: 17-6-15
                break;
            case R.id.status_picture:

                break;
            default:

        }
    }
}
