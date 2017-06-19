package ts.af2.lightlife.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import ts.af2.lightlife.adapter.MyFragmentPagerAdapter;
import ts.af2.lightlife.fragment.GuideFragment;
import ts.af2.lightlife.fragment.CommunityFragment;
import ts.af2.lightlife.fragment.MineFragment;
import ts.af2.lightlife.fragment.HomeFragment;
import ts.af2.lightlife.R;


public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private TextView tvMessageNormal,tvMessagePress,tvContactsNormal,tvContactsPress;
    private TextView tvDiscoveryNormal,tvDiscoveryPress,tvMeNormal,tvMePress;
    private TextView tvMessageTextNormal,tvMessageTextPress,tvContactsTextNormal,tvContactsTextPress;
    private TextView tvDiscoveryTextNormal,tvDiscoveryTextPress,tvMeTextNormal,tvMeTextPress;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        tvMessageNormal=(TextView) findViewById(R.id.tv_message_normal);
        tvMessagePress=(TextView) findViewById(R.id.tv_message_press);
        tvContactsNormal=(TextView) findViewById(R.id.tv_contacts_normal);
        tvContactsPress=(TextView) findViewById(R.id.tv_contacts_press);
        tvDiscoveryNormal=(TextView) findViewById(R.id.tv_discovery_normal);
        tvDiscoveryPress=(TextView) findViewById(R.id.tv_discovery_press);
        tvMeNormal=(TextView) findViewById(R.id.tv_me_normal);
        tvMePress=(TextView) findViewById(R.id.tv_me_press);
        tvMessageTextNormal=(TextView) findViewById(R.id.tv_message_text_normal);
        tvMessageTextPress=(TextView) findViewById(R.id.tv_message_text_press);
        tvContactsTextNormal=(TextView) findViewById(R.id.tv_contacts_text_normal);
        tvContactsTextPress=(TextView) findViewById(R.id.tv_contacts_text_press);
        tvDiscoveryTextNormal=(TextView) findViewById(R.id.tv_discovery_text_normal);
        tvDiscoveryTextPress=(TextView) findViewById(R.id.tv_discovery_text_press);
        tvMeTextNormal=(TextView) findViewById(R.id.tv_me_text_normal);
        tvMeTextPress=(TextView) findViewById(R.id.tv_me_text_press);

        findViewById(R.id.ll_message).setOnClickListener(this);
        findViewById(R.id.ll_contacts).setOnClickListener(this);
        findViewById(R.id.ll_discovery).setOnClickListener(this);
        findViewById(R.id.ll_me).setOnClickListener(this);

        //默认选中第一个
        setTransparency();
        tvMessagePress.getBackground().setAlpha(255);
        tvMessageTextPress.setTextColor(Color.argb(255, 216, 30, 6));

        /**ViewPager**/
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        HomeFragment weChatFragment =new HomeFragment();
        GuideFragment guideFragment =new GuideFragment();
        CommunityFragment communityFragment =new CommunityFragment();
        MineFragment mineFragment =new MineFragment();
        ArrayList<Fragment> fragmentList=new ArrayList<Fragment>();
        fragmentList.add(weChatFragment);
        fragmentList.add(guideFragment);
        fragmentList.add(communityFragment);
        fragmentList.add(mineFragment);
        //ViewPager设置适配器
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(0);
        //ViewPager页面切换监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //根据ViewPager滑动位置更改透明度
                int diaphaneity_one=(int)(255 * positionOffset);
                int diaphaneity_two=(int)(255 * (1 - positionOffset));
                switch (position){
                    case 0:
                        tvMessageNormal.getBackground().setAlpha(diaphaneity_one);
                        tvMessagePress.getBackground().setAlpha(diaphaneity_two);
                        tvContactsNormal.getBackground().setAlpha(diaphaneity_two);
                        tvContactsPress.getBackground().setAlpha(diaphaneity_one);
                        tvMessageTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvMessageTextPress.setTextColor(Color.argb(diaphaneity_two, 216, 30, 6));
                        tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_two,153,153,153));
                        tvContactsTextPress.setTextColor(Color.argb(diaphaneity_one,216, 30, 6));
                        break;
                    case 1:
                        tvContactsNormal.getBackground().setAlpha(diaphaneity_one);
                        tvContactsPress.getBackground().setAlpha(diaphaneity_two);
                        tvDiscoveryNormal.getBackground().setAlpha(diaphaneity_two);
                        tvDiscoveryPress.getBackground().setAlpha(diaphaneity_one);
                        tvContactsTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvContactsTextPress.setTextColor(Color.argb(diaphaneity_two, 216, 30, 6));
                        tvDiscoveryTextNormal.setTextColor(Color.argb(diaphaneity_two,153,153,153));
                        tvDiscoveryTextPress.setTextColor(Color.argb(diaphaneity_one,216, 30, 6));
                        break;
                    case 2:
                        tvDiscoveryNormal.getBackground().setAlpha(diaphaneity_one);
                        tvDiscoveryPress.getBackground().setAlpha(diaphaneity_two);
                        tvMeNormal.getBackground().setAlpha(diaphaneity_two);
                        tvMePress.getBackground().setAlpha(diaphaneity_one);
                        tvDiscoveryTextNormal.setTextColor(Color.argb(diaphaneity_one, 153, 153, 153));
                        tvDiscoveryTextPress.setTextColor(Color.argb(diaphaneity_two, 216, 30, 6));
                        tvMeTextNormal.setTextColor(Color.argb(diaphaneity_two,153,153,153));
                        tvMeTextPress.setTextColor(Color.argb(diaphaneity_one,216, 30, 6));
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 设置透明度
     * 把press图片、文字全部隐藏
     */
    private void setTransparency(){
        tvMessageNormal.getBackground().setAlpha(255);
        tvContactsNormal.getBackground().setAlpha(255);
        tvDiscoveryNormal.getBackground().setAlpha(255);
        tvMeNormal.getBackground().setAlpha(255);
        tvMessagePress.getBackground().setAlpha(1);
        tvContactsPress.getBackground().setAlpha(1);
        tvDiscoveryPress.getBackground().setAlpha(1);
        tvMePress.getBackground().setAlpha(1);
        tvMessageTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvContactsTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvDiscoveryTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvMeTextNormal.setTextColor(Color.argb(255, 153, 153, 153));
        tvMessageTextPress.setTextColor(Color.argb(0, 216, 30, 6));
        tvContactsTextPress.setTextColor(Color.argb(0, 216, 30, 6));
        tvDiscoveryTextPress.setTextColor(Color.argb(0, 216, 30, 6));
        tvMeTextPress.setTextColor(Color.argb(0, 216, 30, 6));
    }

    @Override
    public void onClick(View v) {
        setTransparency();
        tvDiscoveryNormal.getBackground().setAlpha(255);
        switch (v.getId()){
            case R.id.ll_message:
                viewPager.setCurrentItem(0, false);
                tvMessagePress.getBackground().setAlpha(255);
                tvMessageTextPress.setTextColor(Color.argb(255, 216, 30, 6));
                break;
            case R.id.ll_contacts:
                viewPager.setCurrentItem(1, false);
                tvContactsPress.getBackground().setAlpha(255);
                tvContactsTextPress.setTextColor(Color.argb(255, 216, 30, 6));
                break;
            case R.id.ll_discovery:
                viewPager.setCurrentItem(2,false);
                tvDiscoveryNormal.getBackground().setAlpha(0);
                tvDiscoveryPress.getBackground().setAlpha(255);
                tvDiscoveryTextPress.setTextColor(Color.argb(255, 216, 30, 6));
                break;
            case R.id.ll_me:
                viewPager.setCurrentItem(3,false);
                tvMePress.getBackground().setAlpha(255);
                tvMeTextPress.setTextColor(Color.argb(255, 216, 30, 6));
                break;
        }
    }
}
