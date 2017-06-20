package bravest.ptt.androidlib.activity.toolbar;

import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import bravest.ptt.androidlib.activity.AbstractBaseActivity;

/**
 * Created by root on 6/15/17.
 */

public abstract class AbstractToolbarActivity extends AbstractBaseActivity {

    private ToolbarHelper mToolbarHelper;

    protected Toolbar mToolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mToolbarHelper = new ToolbarHelper(mContext, layoutResID);
        mToolbar = mToolbarHelper.getToolbar();
        setContentView(mToolbarHelper.getContentView());
        setSupportActionBar(mToolbar);
        onCreateCustomToolbar(mToolbar);
        enableBackArrow(true);
    }

    public void onCreateCustomToolbar(Toolbar toolbar) {
        //custom action
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected final void enableBackArrow(boolean enable) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(enable);
            actionBar.setHomeButtonEnabled(enable);
        }
    }
}
