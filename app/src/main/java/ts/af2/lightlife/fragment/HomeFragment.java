package ts.af2.lightlife.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ts.af2.lightlife.R;
import ts.af2.lightlife.util.Util;
import ts.af2.lightlife.view.CycleViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private CycleViewPager cycleViewPager;
    private List<ImageView> views = new ArrayList<ImageView>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        cycleViewPager = (CycleViewPager) getChildFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);
        // 将最后一个ImageView添加进来
        ImageView lastImage = new ImageView(getActivity());
        lastImage.setImageResource(Util.flipperImage[Util.flipperImage.length - 1]);
        views.add(lastImage);
        for (int i = 0; i < Util.flipperImage.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(Util.flipperImage[i]);
            views.add(imageView);
        }
        // 将第一个ImageView添加进来
        ImageView firstImage = new ImageView(getActivity());
        firstImage.setImageResource(Util.flipperImage[0]);
        views.add(firstImage);
        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, null, null);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }
}
