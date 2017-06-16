package ts.af2.lightlife.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by SHEN on 2015/10/27.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    public MyFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("hym","Fragment id = "+position+" fragment name = "+list.get(position));
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
