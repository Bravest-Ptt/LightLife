package ts.af2.lightlife.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import ts.af2.lightlife.R;
import ts.af2.lightlife.util.Util;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewFlipper mViewFlipper;

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
        mViewFlipper = (ViewFlipper) getView().findViewById(R.id.home_page_flipper);
        for (int i = 0; i < Util.flipperImage.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(Util.flipperImage[i]);
            mViewFlipper.addView(imageView);
        }
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(2000);
    }
}
