package ts.af2.lightlife.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ts.af2.lightlife.R;
import ts.af2.lightlife.activity.SettingsActivity;
import ts.af2.lightlife.activity.SplashActivity;
import ts.af2.lightlife.entity.User;
import ts.af2.lightlife.util.UserUtils;
import ts.af2.lightlife.view.CircleImage;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    private View mHost;
    private CircleImage mUserProfile;
    private RelativeLayout mSettingsLayout;
    private TextView mUserName;
    private static String TAG = "MineFragment";

    public MineFragment() {
        // Required empty public constructor
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.settings:
                    Intent settingsIntent = new Intent(getActivity(), SettingsActivity.class);
                    startActivity(settingsIntent);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mHost = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        setClickListener();
        initData();
        // Inflate the layout for this fragment
        return mHost;
    }

    private void initData() {
        User user = User.getInstance(getActivity());
        Log.d(TAG, "url = " + user.toString());
        mUserName.setText(user.getUsername());
    }

    private void setClickListener() {
        mSettingsLayout.setOnClickListener(mClickListener);
    }

    private void initView() {
        mSettingsLayout = (RelativeLayout) mHost.findViewById(R.id.settings);
        mUserProfile = (CircleImage) mHost.findViewById(R.id.user_profile);
        mUserName = (TextView) mHost.findViewById(R.id.user_name);
    }

}
