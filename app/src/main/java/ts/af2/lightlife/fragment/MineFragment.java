package ts.af2.lightlife.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import ts.af2.lightlife.R;
import ts.af2.lightlife.activity.SettingsActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    private View mHost;


    public MineFragment() {
        // Required empty public constructor
    }

    private View.OnClickListener mClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.settings:
                    Intent intent = new Intent(getActivity(), SettingsActivity.class);
                    startActivity(intent);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mHost = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        // Inflate the layout for this fragment
        return mHost;
    }

    private void initView() {
        RelativeLayout settingsLayout = (RelativeLayout) mHost.findViewById(R.id.settings);
        settingsLayout.setOnClickListener(mClickListener);
    }

}
