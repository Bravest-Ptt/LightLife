package ts.af2.lightlife.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ts.af2.lightlife.R;

public class ArticleDetailsActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

    private final String mUri = "http://mini.eastday.com/mobile/170105110355287.html?qid=juheshuju";
    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        init();
        setListener();
    }

    public void setListener() {
        mWebView.setWebViewClient(new WebViewClient() {
            //复写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d(TAG, "dlj 45 ArticleDetailsActivity onProgressChanged : newProgress = " + newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == mProgressBar.getMax()) {
                    mProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                }

            }
        });
    }

    public void init() {
        setContentView(R.layout.activity_article_details);
        mWebView = (WebView) findViewById(R.id.webView);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        initWebSetting();
        mWebView.loadUrl(mUri);
    }

    private void initWebSetting() {
        WebSettings webSettings = mWebView.getSettings();
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
    }
}
